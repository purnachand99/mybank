package com.rvfs.challenge.mybank.web.controller;

import com.rvfs.challenge.mybank.dto.UserDTO;
import com.rvfs.challenge.mybank.model.User;
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


	@PostMapping("/signup")
	public ResponseEntity<Object> signup(@RequestBody UserDTO user) {
		userService.signup(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getEmail())
				.toUri();

		return ResponseEntity.created(location).build();

	}

}
