/**
 * 
 */
package com.tobo.services.domain;

/**
 * @author PRASADBolla
 *
 */
public class AuthenticationBean {
	private String message;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	public  AuthenticationBean(String message) {
		this.message=message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
