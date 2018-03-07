package com.rvfs.challenge.mybank.web.controller.account;

import com.rvfs.challenge.mybank.web.controller.account.request.GetBalanceRequestBody;
import com.rvfs.challenge.mybank.web.controller.account.request.TransactionRequestBody;
import com.rvfs.challenge.mybank.service.dto.AccountDTO;
import com.rvfs.challenge.mybank.service.dto.ApiErrorDTO;
import com.rvfs.challenge.mybank.service.dto.TransactionDTO;
import com.rvfs.challenge.mybank.exception.MyBankException;
import com.rvfs.challenge.mybank.service.AccountService;
import com.rvfs.challenge.mybank.util.ObjectParserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.invoke.MethodHandles;

/**
 * Account Rest controller.
 *
 * @author rodrigovfsilva
 */
@RestController
@RequestMapping("/api/account")
@Api(value="account", description="Account Operations: deposit, withdraw and get statements and balance information")
public class AccountRestController {

    /**
     * Logger definition.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Message resource.
     */
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "Withdraw operation",response = AccountDTO.class)
    @PostMapping("/withdraw")
    public ResponseEntity<Object> withdraw(@Valid @RequestBody TransactionRequestBody transaction) {
        LOGGER.debug("withdraw request body {}", ObjectParserUtil.getInstance().toString(transaction));

        AccountDTO accountResponseBody;
        ResponseEntity<Object> responseEntity = null;

        try {
            TransactionDTO withdrawTransaction = new TransactionDTO(transaction.getAccountNumber(), transaction.getAmount(), transaction.getDescription());

            accountResponseBody = accountService.withdraw(withdrawTransaction);

            responseEntity = new ResponseEntity<>(accountResponseBody, HttpStatus.CREATED);

        } catch (MyBankException e) {
            LOGGER.warn(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.debug("withdraw response body {}", ObjectParserUtil.getInstance().toString(responseEntity));

        }

        return responseEntity;

    }

    @ApiOperation(value = "Deposit operation",response = AccountDTO.class)
    @PostMapping("/deposit")
    public ResponseEntity<Object> deposit(@Valid @RequestBody TransactionRequestBody transaction) {
        LOGGER.debug("deposit request body {}", ObjectParserUtil.getInstance().toString(transaction));

        AccountDTO accountResponseBody;
        ResponseEntity<Object> responseEntity = null;

        try {

            TransactionDTO depositTransaction = new TransactionDTO(transaction.getAccountNumber(), transaction.getAmount(), transaction.getDescription());

            accountResponseBody = accountService.deposit(depositTransaction);

            responseEntity = new ResponseEntity<>(accountResponseBody, HttpStatus.CREATED);

        } catch (MyBankException e) {
            LOGGER.warn(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.debug("deposit response body {}", ObjectParserUtil.getInstance().toString(responseEntity));

        }

        return responseEntity;

    }

    @ApiOperation(value = "Balance and statement operation",response = AccountDTO.class)
    @PostMapping("/get_balance_and_statement")
    public ResponseEntity<Object> getBalanceAndStatement(@Valid @RequestBody GetBalanceRequestBody getBalance) {
        LOGGER.debug("getBalanceAndStatement request body {}", ObjectParserUtil.getInstance().toString(getBalance));

        AccountDTO accountResponseBody;
        ResponseEntity<Object> responseEntity = null;

        try {
            AccountDTO account = new AccountDTO();
            account.setAccountNumber(getBalance.getAccountNumber());

            accountResponseBody = accountService.getBalanceAndStatement(account);

            responseEntity = new ResponseEntity<>(accountResponseBody, HttpStatus.OK);

        } catch (MyBankException e) {
            LOGGER.warn(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.debug("getBalanceAndStatement response body {}", ObjectParserUtil.getInstance().toString(responseEntity));

        }

        return responseEntity;

    }
}
