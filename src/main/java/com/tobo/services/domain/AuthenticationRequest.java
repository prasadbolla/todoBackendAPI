package com.tobo.services.domain;

/**
 * @author PRASADBolla
 *
 */
public class AuthenticationRequest {
	public String username;
	public String password;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 */
	public AuthenticationRequest() {

	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param username
	 * @param password
	 */
	public AuthenticationRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
