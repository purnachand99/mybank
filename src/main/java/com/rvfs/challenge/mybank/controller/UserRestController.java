package com.rvfs.challenge.mybank.controller;

import com.rvfs.challenge.mybank.controller.dto.UserDTO;
import com.rvfs.challenge.mybank.model.Customer;
import com.rvfs.challenge.mybank.model.User;
import com.rvfs.challenge.mybank.service.CustomerService;
import com.rvfs.challenge.mybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * User Rest controller.
 * 
 * @author rodrigovfsilva
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private CustomerService customService;

	@PostMapping("/signup")
	public ResponseEntity<Object> signup(@RequestBody UserDTO user) {
		userService.signup(new User(user.getEmail(), user.getPassword()));
		customService.create(new Customer(user.getName()));

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getEmail())
				.toUri();

		return ResponseEntity.created(location).build();

	}

}
