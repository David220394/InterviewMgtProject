package com.accenture.interviewproj.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.interviewproj.security.AuthenticationToken;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

	@GetMapping
	public ResponseEntity<?> getName() {
		return ResponseEntity.ok(new AuthenticationToken("Heelo"));
	}
}
