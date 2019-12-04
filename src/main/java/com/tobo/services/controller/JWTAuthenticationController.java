package com.tobo.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tobo.services.data.service.JwtTokenUtil;
import com.tobo.services.data.service.JwtUserDetailsService;
import com.tobo.services.domain.AuthenticationRequest;
import com.tobo.services.domain.JwtResponse;

@CrossOrigin
@RestController
public class JWTAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	/**
	 * @param authenticationRequest
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/authenticate")
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){

		try {
			authenticate(authenticationRequest.getUsername(),
					authenticationRequest.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));

	}

	/**
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	private void authenticate(String username, String password)
			throws Exception {

		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_IS_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_USER_CREDENTIALS", e);

		}

	}

}