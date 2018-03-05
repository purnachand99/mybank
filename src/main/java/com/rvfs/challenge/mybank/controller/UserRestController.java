package com.rvfs.challenge.mybank.controller;

import com.rvfs.challenge.mybank.dto.UserDTO;
import com.rvfs.challenge.mybank.dto.ApiErrorDTO;
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
 * User Rest controller.
 *
 * @author rodrigovfsilva
 */
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    /**
     * Message resource.
     */
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    /**
     * Sign up operation.
     *
     * @param user User to Sign Up
     * @return Response Entity with user or error data.
     */
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody UserDTO user) {

        UserDTO userResponseBody = null;
        ResponseEntity<Object> responseEntity = null;

        try {
            userResponseBody = userService.signup(user);

            responseEntity = new ResponseEntity<>(userResponseBody, HttpStatus.CREATED);

        }catch (Exception e){
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;

    }

    /**
     * Sign in operation
     * @param user User to sign in.
     * @return Response Entity with user or error data.
     */
    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@Valid @RequestBody UserDTO user) {

        UserDTO userResponseBody = null;
        ResponseEntity<Object> responseEntity = null;

        try {
            userResponseBody = userService.signin(user);

            responseEntity = new ResponseEntity<>(userResponseBody, HttpStatus.OK);

        }catch (Exception e){
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return responseEntity;

    }
}
