package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.dto.AccountDTO;
import com.rvfs.challenge.mybank.dto.TransactionDTO;
import com.rvfs.challenge.mybank.exception.BusinessException;
import com.rvfs.challenge.mybank.model.Account;
import com.rvfs.challenge.mybank.model.Transaction;
import com.rvfs.challenge.mybank.repository.AccountRepository;
import com.rvfs.challenge.mybank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Locale;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    MessageSource messageSource;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public AccountDTO create(Account account)  {

        account = accountRepository.save(account);

        return new AccountDTO(account.getAccountNumber(), account.getBalance(), account.getUpdatedAt());
    }

    @Override
    public AccountDTO find(Long id) {
        Account account = accountRepository.findOne(id);

        return new AccountDTO(account.getAccountNumber(), account.getBalance(), account.getUpdatedAt());
    }

    @Override
    public AccountDTO findByAccountNumber(Long accountNumber) {
        Account account =  accountRepository.findByAccountNumber(accountNumber);

        return new AccountDTO(account.getAccountNumber(), account.getBalance(), account.getUpdatedAt());

    }

    @Override
    public AccountDTO withdraw(TransactionDTO transaction) throws BusinessException {
        AccountDTO updatedAccount = new AccountDTO();

        if(transaction != null) {

            if (transaction.getAccount() == null || (transaction.getAccount() != null &&
                    transaction.getAccount().getAccountNumber() == null)) {
                throw new BusinessException(messageSource.getMessage("error.business.account.null.or.empty", null, Locale.getDefault()));

            } else if (transaction.getAmount() == null ||
                    (transaction.getAmount() != null && transaction.getAmount().equals(BigDecimal.ZERO))) {
                throw new BusinessException(messageSource.getMessage("error.business.transaction.amount.null.or.empty", null, Locale.getDefault()));

            } else if (
                    (transaction.getAmount() != null && transaction.getAmount().compareTo(BigDecimal.ZERO) < 0)) {
                throw new BusinessException(messageSource.getMessage("error.business.transaction.amount.negative", null, Locale.getDefault()));

            } else {
                Account currentAccount = accountRepository.findByAccountNumber(transaction.getAccount().getAccountNumber());

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
                updatedAccount.setAccountNumber(currentAccount.getId());

            }
        }
        return updatedAccount;
    }

    @Override
    public AccountDTO deposit(TransactionDTO transaction) throws BusinessException {

        AccountDTO updatedAccount = new AccountDTO();

        if(transaction != null) {

            if (transaction.getAccount() == null || (transaction.getAccount() != null &&
                    transaction.getAccount().getAccountNumber() == null)) {
                throw new BusinessException(messageSource.getMessage("error.business.account.null.or.empty", null, Locale.getDefault()));

            } else if (transaction.getAmount() == null ||
                    (transaction.getAmount() != null && transaction.getAmount().equals(BigDecimal.ZERO))) {
                throw new BusinessException(messageSource.getMessage("error.business.transaction.amount.null.or.empty", null, Locale.getDefault()));

            } else if (
                    (transaction.getAmount() != null && transaction.getAmount().compareTo(BigDecimal.ZERO) < 0)) {
                throw new BusinessException(messageSource.getMessage("error.business.transaction.amount.negative", null, Locale.getDefault()));

            } else {
                Account currentAccount = accountRepository.findByAccountNumber(transaction.getAccount().getAccountNumber());

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

            }
        }
        return updatedAccount;
    }

    @Override
    public AccountDTO getStatement(AccountDTO account) {
        return null;
    }
}
