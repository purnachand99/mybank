package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.dto.AccountDTO;
import com.rvfs.challenge.mybank.dto.TransactionDTO;
import com.rvfs.challenge.mybank.exception.MyBankException;
import com.rvfs.challenge.mybank.model.Account;

/**
 * Account service interface.
 */
public interface AccountService {

    /**
     * Create a new account.
     * @param account Account to be created.
     * @return New account data.
     */
    AccountDTO create(Account account);

    /**
     * Find account by id.
     * @param id Account id.
     * @return Account data.
     * @throws MyBankException If occurs an error during the method execution.
     */
    AccountDTO find(Long id) throws MyBankException;

    /**
     * Find accung by number.
     * @param accountNumber Account number.
     * @return Account data.
     * @throws MyBankException If occurs an error during the method execution.
     */
    AccountDTO findByNumber(Long accountNumber) throws MyBankException;

    /**
     * Withdraw an amount.
     *
     * @param transaction Transaction data.
     * @return Account updated.
     * @throws MyBankException If occurs an error during the method execution.
     */
    AccountDTO withdraw(TransactionDTO transaction) throws MyBankException;


    /**
     * Deposit an amount.
     *
     * @param transaction Transaction data.
     * @return Account updated.
     * @throws MyBankException If occurs an error during the method execution.
     */
    AccountDTO deposit(TransactionDTO transaction) throws MyBankException;


    /**
     * Get balance data and statemens of an account.
     *
     * @param account Account to be found.
     * @return Account data.
     * @throws MyBankException If occurs an error during the method execution.
     */
    AccountDTO getStatement(AccountDTO account) throws MyBankException;

}
