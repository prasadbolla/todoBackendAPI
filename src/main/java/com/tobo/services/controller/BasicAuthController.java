/**
 * 
 */
package com.tobo.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tobo.services.data.service.JwtTokenUtil;
import com.tobo.services.data.service.JwtUserDetailsService;
import com.tobo.services.domain.AuthenticationBean;
import com.tobo.services.domain.AuthenticationRequest;
import com.tobo.services.domain.JwtResponse;

/**
 * @author PRASADBolla
 *
 */
@CrossOrigin
@RestController
public class BasicAuthController {
	

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@GetMapping(path="/basicAuth")
	public AuthenticationBean authenticate(){
		return new AuthenticationBean("Authenticated Successfully");
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
