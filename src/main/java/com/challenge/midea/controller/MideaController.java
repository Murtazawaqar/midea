package com.challenge.midea.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MideaController {

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