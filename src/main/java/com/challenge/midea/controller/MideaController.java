package com.challenge.midea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.midea.model.AuthenticationRequest;
import com.challenge.midea.model.AuthenticationResponse;
import com.challenge.midea.util.JWTUtil;

@RestController
public class MideaController {
	
//	@Autowired
//	AuthenticationManager authenticationManager;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	JWTUtil jwtUtil;
	
	/*
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							authenticationRequest.getUsername(), 
							authenticationRequest.getPassword()
							)
					);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password!", e);
		}
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	 */
	@GetMapping("/")
	public String getHomeContent() {
		return ("<h1>WELCOME to mIDEA</h1>");
	}
	
	@GetMapping("/user")
	public String user() {
		return ("<h1>WELCOME User</h1>");
	}
	
	@GetMapping("/admin")
	public String admin() {
		return ("<h1>WELCOME Admin</h1>");
	}
}