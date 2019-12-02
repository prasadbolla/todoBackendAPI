package com.tobo.services;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author PRASADBolla 
 *         Extending "WebSecurityConfigurerAdapter" to enable authentication and authorization.
 *         It helps in requiring the user to be
 *         authenticated prior to accessing any configured URL.
 *         I have used to BasicAut in this example. Better approach is JWT.
 */
@Configuration
@EnableWebSecurity
public class TodoServiceSecurityConfiguration extends
		WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
		.authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**")
		.permitAll().anyRequest().authenticated()//.and()//.formLogin()
				.and().httpBasic();
	}
}
