package com.rvfs.challenge.mybank.controller;

import com.rvfs.challenge.mybank.dto.AccountDTO;
import com.rvfs.challenge.mybank.dto.ApiErrorDTO;
import com.rvfs.challenge.mybank.dto.TransactionDTO;
import com.rvfs.challenge.mybank.dto.UserDTO;
import com.rvfs.challenge.mybank.model.Transaction;
import com.rvfs.challenge.mybank.service.AccountService;
import com.rvfs.challenge.mybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Account Rest controller.
 *
 * @author rodrigovfsilva
 */
@RestController
@RequestMapping("/api/account")
public class AccountRestController {

    /**
     * Message resource.
     */
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AccountService accountService;

    @PostMapping("/withdraw")
    public ResponseEntity<Object> withdraw(@Valid @RequestBody TransactionDTO transaction) {

        AccountDTO accountResponseBody;
        ResponseEntity<Object> responseEntity;

        try {
            accountResponseBody = accountService.withdraw(transaction);

            responseEntity = new ResponseEntity<>(accountResponseBody, HttpStatus.CREATED);

        }catch (Exception e){
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;

    }

    @PostMapping("/deposit")
    public ResponseEntity<Object> deposit(@Valid @RequestBody  TransactionDTO transaction) {

        AccountDTO accountResponseBody;
        ResponseEntity<Object> responseEntity;

        try {
            accountResponseBody = accountService.deposit(transaction);

            responseEntity = new ResponseEntity<>(accountResponseBody, HttpStatus.CREATED);

        }catch (Exception e){
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;

    }
}
