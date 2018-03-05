package com.rvfs.challenge.mybank.controller;

import com.rvfs.challenge.mybank.dto.AccountDTO;
import com.rvfs.challenge.mybank.dto.ApiErrorDTO;
import com.rvfs.challenge.mybank.dto.TransactionDTO;
import com.rvfs.challenge.mybank.exception.MyBankException;
import com.rvfs.challenge.mybank.service.AccountService;
import com.rvfs.challenge.mybank.util.ObjectParserUtil;
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

    @PostMapping("/withdraw")
    public ResponseEntity<Object> withdraw(@Valid @RequestBody TransactionDTO transaction) {
        LOGGER.debug("withdraw request body {}", ObjectParserUtil.getInstance().toString(transaction));

        AccountDTO accountResponseBody;
        ResponseEntity<Object> responseEntity = null;

        try {
            accountResponseBody = accountService.withdraw(transaction);

            responseEntity = new ResponseEntity<>(accountResponseBody, HttpStatus.CREATED);

        }catch (MyBankException e){
            LOGGER.warn(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            LOGGER.error(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.debug("withdraw response body {}", ObjectParserUtil.getInstance().toString(responseEntity));

        }

        return responseEntity;

    }

    @PostMapping("/deposit")
    public ResponseEntity<Object> deposit(@Valid @RequestBody  TransactionDTO transaction) {
        LOGGER.debug("deposit request body {}", ObjectParserUtil.getInstance().toString(transaction));

        AccountDTO accountResponseBody;
        ResponseEntity<Object> responseEntity = null;

        try {
            accountResponseBody = accountService.deposit(transaction);

            responseEntity = new ResponseEntity<>(accountResponseBody, HttpStatus.CREATED);

        }catch (MyBankException e){
            LOGGER.warn(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            LOGGER.error(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            LOGGER.debug("deposit response body {}", ObjectParserUtil.getInstance().toString(responseEntity));

        }

        return responseEntity;

    }

    @PostMapping("/get_balance_and_statement")
    public ResponseEntity<Object> getBalanceAndStatement(@Valid @RequestBody  AccountDTO account) {
        LOGGER.debug("getBalanceAndStatement request body {}", ObjectParserUtil.getInstance().toString(account));

        AccountDTO accountResponseBody;
        ResponseEntity<Object> responseEntity = null;

        try {
            accountResponseBody = accountService.getStatement(account);

            responseEntity = new ResponseEntity<>(accountResponseBody, HttpStatus.OK);

        }catch (Exception e){
            LOGGER.error(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            LOGGER.debug("getBalanceAndStatement response body {}", ObjectParserUtil.getInstance().toString(responseEntity));

        }

        return responseEntity;

    }
}
