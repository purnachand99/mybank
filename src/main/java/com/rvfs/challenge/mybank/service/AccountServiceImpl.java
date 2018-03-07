package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.service.dto.AccountDTO;
import com.rvfs.challenge.mybank.service.dto.TransactionDTO;
import com.rvfs.challenge.mybank.exception.MyBankException;
import com.rvfs.challenge.mybank.model.Account;
import com.rvfs.challenge.mybank.model.Transaction;
import com.rvfs.challenge.mybank.repository.AccountRepository;
import com.rvfs.challenge.mybank.repository.TransactionRepository;
import com.rvfs.challenge.mybank.util.AccountNumberGenerator;
import com.rvfs.challenge.mybank.util.ObjectParserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

/**
 * Account service implementation.
 */
@Service
public class AccountServiceImpl implements AccountService {

    /**
     * Logger definition.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    MessageSource messageSource;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public AccountDTO create(Account account) {
        account.setAccountNumber(AccountNumberGenerator.getNextNumber());
        LOGGER.debug("create {}", ObjectParserUtil.getInstance().toString(account));

        account = accountRepository.save(account);
        AccountDTO savedAccount = new AccountDTO(account.getAccountNumber(), account.getBalance(), account.getUpdatedAt());

        LOGGER.debug("create result {}", ObjectParserUtil.getInstance().toString(savedAccount));
        return savedAccount;
    }

    @Override
    public AccountDTO find(Long id) throws MyBankException {
        LOGGER.debug("find {}", id);

        Account account = accountRepository.findOne(id);

        AccountDTO foundAccount = new AccountDTO(account.getAccountNumber(), account.getBalance(), account.getUpdatedAt());

        if (account == null) {
            throw new MyBankException(messageSource.getMessage("error.business.account.not.found", new Object[]{id}, Locale.getDefault()));
        }

        LOGGER.debug("find result {}", ObjectParserUtil.getInstance().toString(foundAccount));
        return foundAccount;
    }

    @Override
    public AccountDTO findByNumber(Long accountNumber) throws MyBankException {
        LOGGER.debug("findByNumber {}", accountNumber);

        Account account = accountRepository.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new MyBankException(messageSource.getMessage("error.business.account.not.found", new Object[]{accountNumber}, Locale.getDefault()));
        }

        AccountDTO foundAccount = new AccountDTO(account.getAccountNumber(), account.getBalance(), account.getUpdatedAt());

        LOGGER.debug("findByNumber result {}", ObjectParserUtil.getInstance().toString(foundAccount));
        return foundAccount;

    }

    @Override
    public AccountDTO withdraw(TransactionDTO transaction) throws MyBankException {
        AccountDTO updatedAccount = new AccountDTO();

        if (transaction != null) {

            if (transaction.getAccountNumber() == null) {
                throw new MyBankException(messageSource.getMessage("error.business.account.null.or.empty", null, Locale.getDefault()));

            } else if (transaction.getAmount() == null ||
                    (transaction.getAmount() != null && transaction.getAmount().equals(BigDecimal.ZERO))) {
                throw new MyBankException(messageSource.getMessage("error.business.transaction.amount.null.or.empty", null, Locale.getDefault()));

            } else if (
                    (transaction.getAmount() != null && transaction.getAmount().compareTo(BigDecimal.ZERO) < 0)) {
                throw new MyBankException(messageSource.getMessage("error.business.transaction.amount.negative", null, Locale.getDefault()));

            } else {
                Account currentAccount = accountRepository.findByAccountNumber(transaction.getAccountNumber());

                if (currentAccount != null) {
                    BigDecimal currentBalance = currentAccount.getBalance();

                    if (currentBalance != null) {
                        currentBalance = currentBalance.subtract(transaction.getAmount());
                    }

                    Transaction withdrawTransaction = new Transaction();
                    withdrawTransaction.setAccount(currentAccount);
                    withdrawTransaction.setAmount(transaction.getAmount());
                    withdrawTransaction.setBalance(currentBalance);
                    withdrawTransaction.setDescription(transaction.getDescription());
                    withdrawTransaction.setType(Transaction.Type.WITHDRAW.getCode());
                    withdrawTransaction = transactionRepository.save(withdrawTransaction);

                    currentAccount.setBalance(currentBalance);
                    accountRepository.save(currentAccount);

                    updatedAccount.setCurrentBalance(currentBalance);
                    updatedAccount.setAccountNumber(currentAccount.getAccountNumber());
                    updatedAccount.setUpdateAt(currentAccount.getUpdatedAt());
                } else {
                    throw new MyBankException(messageSource.getMessage("error.business.account.not.found", new Object[]{transaction.getAccountNumber()}, Locale.getDefault()));
                }

            }
        }
        return updatedAccount;
    }

    @Override
    @Transactional
    public AccountDTO deposit(TransactionDTO transaction) throws MyBankException {

        AccountDTO updatedAccount = new AccountDTO();

        if (transaction != null) {

            if (transaction.getAccountNumber() == null) {
                throw new MyBankException(messageSource.getMessage("error.business.account.null.or.empty", null, Locale.getDefault()));

            } else if (transaction.getAmount() == null ||
                    (transaction.getAmount() != null && transaction.getAmount().equals(BigDecimal.ZERO))) {
                throw new MyBankException(messageSource.getMessage("error.business.transaction.amount.null.or.empty", null, Locale.getDefault()));

            } else if (
                    (transaction.getAmount() != null && transaction.getAmount().compareTo(BigDecimal.ZERO) < 0)) {
                throw new MyBankException(messageSource.getMessage("error.business.transaction.amount.negative", null, Locale.getDefault()));

            } else {
                Account currentAccount = accountRepository.findByAccountNumber(transaction.getAccountNumber());

                if (currentAccount != null) {

                    BigDecimal currentBalance = currentAccount.getBalance();

                    if (currentBalance != null) {
                        currentBalance = currentBalance.add(transaction.getAmount());
                    }

                    Transaction withdrawTransaction = new Transaction();
                    withdrawTransaction.setAccount(currentAccount);
                    withdrawTransaction.setAmount(transaction.getAmount());
                    withdrawTransaction.setBalance(currentBalance);
                    withdrawTransaction.setDescription(transaction.getDescription());
                    withdrawTransaction.setType(Transaction.Type.DEPOSIT.getCode());
                    LOGGER.debug("deposit {} ", ObjectParserUtil.getInstance().toString(withdrawTransaction));

                    withdrawTransaction = transactionRepository.save(withdrawTransaction);

                    currentAccount.setBalance(currentBalance);
                    accountRepository.save(currentAccount);

                    updatedAccount.setCurrentBalance(currentBalance);
                    updatedAccount.setAccountNumber(currentAccount.getAccountNumber());
                    updatedAccount.setUpdateAt(currentAccount.getUpdatedAt());
                } else {
                    throw new MyBankException(messageSource.getMessage("error.business.account.not.found", new Object[]{transaction.getAccountNumber()}, Locale.getDefault()));
                }
            }
        }
        return updatedAccount;
    }

    @Override
    public AccountDTO getBalanceAndStatement(AccountDTO account) throws MyBankException {

        if (account == null) {
            throw new MyBankException(messageSource.getMessage("error.business.account.null.or.empty", null, Locale.getDefault()));
        }

        Account foundAccount = accountRepository.findByAccountNumber(account.getAccountNumber());

        if (foundAccount == null) {
            throw new MyBankException(messageSource.getMessage("error.business.account.not.found", new Object[]{account.getAccountNumber()}, Locale.getDefault()));
        }

        AccountDTO resultAccount = new AccountDTO(foundAccount.getAccountNumber(), foundAccount.getBalance(), foundAccount.getUpdatedAt());

        List<Transaction> transactions = transactionRepository.findByAccountAccountNumber(foundAccount.getAccountNumber());
        if (transactions != null) {
            for (Transaction transaction : transactions) {
                resultAccount.addTransaction(new TransactionDTO(transaction.getAccount().getAccountNumber(),
                        transaction.getAmount(), transaction.getBalance(),
                        transaction.getDescription(), transaction.getType(),
                        transaction.getCreatedAt()));
            }
        }
        LOGGER.info("getStatement {} ", ObjectParserUtil.getInstance().toString(resultAccount));

        return resultAccount;
    }
}
