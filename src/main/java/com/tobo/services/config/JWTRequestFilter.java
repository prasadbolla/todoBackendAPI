package com.tobo.services.config;

import io.jsonwebtoken.ExpiredJwtException;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tobo.services.data.service.JwtTokenUtil;
import com.tobo.services.data.service.JwtUserDetailsService;
import com.tobo.services.util.CommonUtil;

/**
 * @author PRASADBolla
 *
 */
@Component
public class JWTRequestFilter extends OncePerRequestFilter {

	private static final String BEARER = "Bearer";

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/* (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		
		// Process the request If it is start with Bearer.
		if (!CommonUtil.isNullOrEmpty(requestTokenHeader)
				&& requestTokenHeader.startsWith(BEARER)) 
			validateJsonWebToken(request, requestTokenHeader);
		
		chain.doFilter(request, response);

	}

	/**
	 * @param request
	 * @param requestTokenHeader
	 */
	private void validateJsonWebToken(HttpServletRequest request,
			final String requestTokenHeader) {
		String username = null;
		String jsonwebtoken = null;

			//token start with bearer.. substring to remove the token
			jsonwebtoken = requestTokenHeader.substring(7);

			try {
				username = jwtTokenUtil.getUsernameFromToken(jsonwebtoken);

			} catch (IllegalArgumentException e) {
				logger.error("Unable to get Json Web Token");
			} catch (ExpiredJwtException e) {
				logger.error("Json Web Token has expired");
			}
			
		// token validation
		//check for username is present and authentication is null
		if (username != null
				&& SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.jwtUserDetailsService
					.loadUserByUsername(username);

			// if token is valid configure Spring Security 
			//to manually set authentication

			if (jwtTokenUtil.validateToken(jsonwebtoken, userDetails)) {
				//create username and password authentication token based on 
				//userdetails and authorities
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource()
								.buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(
						usernamePasswordAuthenticationToken);
			}

		}
	}

}