package com.rvfs.challenge.mybank.controller.user;

import com.rvfs.challenge.mybank.controller.user.request.SigninRequestBody;
import com.rvfs.challenge.mybank.controller.user.request.SignupResquestBody;
import com.rvfs.challenge.mybank.dto.ApiErrorDTO;
import com.rvfs.challenge.mybank.dto.UserDTO;
import com.rvfs.challenge.mybank.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * User Rest controller.
 *
 * @author rodrigovfsilva
 */
@RestController
@RequestMapping("/api/user")
@Api(value="user", description="Operations for Users data")
public class UserRestController {

    /**
     * Message resource.
     */
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Signup operation",response = UserDTO.class)
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody SignupResquestBody user) {

        UserDTO userResponseBody = null;
        ResponseEntity<Object> responseEntity = null;

        try {
            userResponseBody = userService.signup(new UserDTO(user.getEmail(), user.getPassword(), user.getName()));

            responseEntity = new ResponseEntity<>(userResponseBody, HttpStatus.CREATED);

        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;

    }

    @ApiOperation(value = "Signin operation",response = UserDTO.class)
    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@Valid @RequestBody SigninRequestBody user) {

        UserDTO userResponseBody = null;
        ResponseEntity<Object> responseEntity = null;

        try {
            userResponseBody = userService.signin(new UserDTO(user.getEmail(), user.getPassword(), null));

            responseEntity = new ResponseEntity<>(userResponseBody, HttpStatus.OK);

        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return responseEntity;

    }
}
