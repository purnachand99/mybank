package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.dto.AccountDTO;
import com.rvfs.challenge.mybank.dto.TransactionDTO;
import com.rvfs.challenge.mybank.exception.BusinessException;
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

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Locale;

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
    public AccountDTO create(Account account)  {
        account.setNumber(AccountNumberGenerator.getNextNumber());
        LOGGER.debug("create {}", ObjectParserUtil.getInstance().toString(account));
        account = accountRepository.save(account);

        AccountDTO savedAccount = new AccountDTO(account.getNumber(), account.getBalance(), account.getUpdatedAt());
        LOGGER.debug("create result {}", ObjectParserUtil.getInstance().toString(savedAccount));

        return savedAccount;
    }

    @Override
    public AccountDTO find(Long id) {
        LOGGER.debug("find {}", id);

        Account account = accountRepository.findOne(id);

        LOGGER.debug("find result {}", ObjectParserUtil.getInstance().toString(account));

        AccountDTO foundAccount = new AccountDTO(account.getNumber(), account.getBalance(), account.getUpdatedAt());

        LOGGER.debug("find result {}", ObjectParserUtil.getInstance().toString(foundAccount));
        return foundAccount;
    }

    @Override
    public AccountDTO findByNumber(Long accountNumber) {
        LOGGER.debug("findByNumber {}", accountNumber);

        Account account =  accountRepository.findByNumber(accountNumber);

        LOGGER.debug("findByNumber result {}", ObjectParserUtil.getInstance().toString(accountNumber));
        return new AccountDTO(account.getNumber(), account.getBalance(), account.getUpdatedAt());

    }

    @Override
    public AccountDTO withdraw(TransactionDTO transaction) throws BusinessException {
        AccountDTO updatedAccount = new AccountDTO();

        if(transaction != null) {

            if (transaction.getAccountNumber() == null) {
                throw new BusinessException(messageSource.getMessage("error.business.account.null.or.empty", null, Locale.getDefault()));

            } else if (transaction.getAmount() == null ||
                    (transaction.getAmount() != null && transaction.getAmount().equals(BigDecimal.ZERO))) {
                throw new BusinessException(messageSource.getMessage("error.business.transaction.amount.null.or.empty", null, Locale.getDefault()));

            } else if (
                    (transaction.getAmount() != null && transaction.getAmount().compareTo(BigDecimal.ZERO) < 0)) {
                throw new BusinessException(messageSource.getMessage("error.business.transaction.amount.negative", null, Locale.getDefault()));

            } else {
                Account currentAccount = accountRepository.findByNumber(transaction.getAccountNumber());

                if(currentAccount != null) {
                    BigDecimal currentBalance = currentAccount.getBalance();

                    if (currentBalance != null) {
                        currentBalance = currentBalance.subtract(transaction.getAmount());
                    }

                    Transaction withdrawTransaction = new Transaction();
                    withdrawTransaction.setAmount(transaction.getAmount());
                    withdrawTransaction.setBalance(currentBalance);
                    withdrawTransaction.setDescription(transaction.getDescription());
                    withdrawTransaction.setType(Transaction.Type.WITHDRAW.getCode());
                    transactionRepository.save(withdrawTransaction);

                    currentAccount.setBalance(currentBalance);
                    accountRepository.save(currentAccount);

                    updatedAccount.setCurrentBalance(currentBalance);
                    updatedAccount.setAccountNumber(currentAccount.getNumber());
                    updatedAccount.setUpdateAt(currentAccount.getUpdatedAt());
                } else {
                    throw new BusinessException(messageSource.getMessage("error.business.account.not.found", new Object[]{transaction.getAccountNumber()}, Locale.getDefault()));
                }

            }
        }
        return updatedAccount;
    }

    @Override
    public AccountDTO deposit(TransactionDTO transaction) throws BusinessException {

        AccountDTO updatedAccount = new AccountDTO();

        if(transaction != null) {

            if (transaction.getAccountNumber() == null) {
                throw new BusinessException(messageSource.getMessage("error.business.account.null.or.empty", null, Locale.getDefault()));

            } else if (transaction.getAmount() == null ||
                    (transaction.getAmount() != null && transaction.getAmount().equals(BigDecimal.ZERO))) {
                throw new BusinessException(messageSource.getMessage("error.business.transaction.amount.null.or.empty", null, Locale.getDefault()));

            } else if (
                    (transaction.getAmount() != null && transaction.getAmount().compareTo(BigDecimal.ZERO) < 0)) {
                throw new BusinessException(messageSource.getMessage("error.business.transaction.amount.negative", null, Locale.getDefault()));

            } else {
                Account currentAccount = accountRepository.findByNumber(transaction.getAccountNumber());

                if(currentAccount != null) {

                    BigDecimal currentBalance = currentAccount.getBalance();

                    if (currentBalance != null) {
                        currentBalance = currentBalance.add(transaction.getAmount());
                    }

                    Transaction withdrawTransaction = new Transaction();
                    withdrawTransaction.setAmount(transaction.getAmount());
                    withdrawTransaction.setBalance(currentBalance);
                    withdrawTransaction.setDescription(transaction.getDescription());
                    withdrawTransaction.setType(Transaction.Type.DEPOSIT.getCode());
                    transactionRepository.save(withdrawTransaction);

                    currentAccount.setBalance(currentBalance);
                    accountRepository.save(currentAccount);

                    updatedAccount.setCurrentBalance(currentBalance);
                    updatedAccount.setAccountNumber(currentAccount.getId());
                    updatedAccount.setUpdateAt(currentAccount.getUpdatedAt());
                }
            }
        }
        return updatedAccount;
    }

    @Override
    public AccountDTO getStatement(AccountDTO account) {
        return null;
    }
}
