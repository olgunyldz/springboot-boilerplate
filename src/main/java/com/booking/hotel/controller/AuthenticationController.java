package com.booking.hotel.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.booking.hotel.config.JwtUtil;
import com.booking.hotel.controller.request.AuthRequest;
import com.booking.hotel.service.CustomUserDetailsService;

@RestController
public class AuthenticationController {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<?> creteToken(@RequestBody AuthRequest authRequest,HttpServletResponse response) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (BadCredentialsException ex) {
			throw new Exception("Incorret username or password", ex);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		response.setHeader("Authorization",jwt);
		return ResponseEntity.ok().build();
		
	}
}
