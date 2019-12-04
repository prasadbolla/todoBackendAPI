/**
 * 
 */
package com.tobo.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tobo.services.data.service.JwtUserDetailsService;
import com.tobo.services.model.User;

/**
 * @author PRASADBolla
 *
 */
@CrossOrigin
@RestController
public class UserRegistrationController {

	@Autowired
	JwtUserDetailsService jwtUserDetailsService;

	@PostMapping(value = "/users/user")
	public ResponseEntity<?> createUser(@RequestBody User user)
			throws Exception {
		return ResponseEntity.ok(jwtUserDetailsService.saveUser(user));

	}
}
