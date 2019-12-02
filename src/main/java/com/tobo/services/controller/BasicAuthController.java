/**
 * 
 */
package com.tobo.services.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PRASADBolla
 *
 */
@CrossOrigin
@RestController
public class BasicAuthController {
	@GetMapping(path="/basicAuth")
	public AuthenticationBean authenticate(){
		return new AuthenticationBean("Authenticated Successfully");
	}

}
