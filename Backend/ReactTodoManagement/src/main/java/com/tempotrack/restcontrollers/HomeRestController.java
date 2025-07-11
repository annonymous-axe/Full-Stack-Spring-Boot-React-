package com.tempotrack.restcontrollers;

import com.tempotrack.entities.User;
import com.tempotrack.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.tempotrack.services.JwtService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HomeRestController {

	JwtService jwtService;
	UserService userService;

	public HomeRestController(JwtService jwtService, UserService userService){
		this.jwtService = jwtService;
		this.userService = userService;
	}

	@GetMapping("/home")
	public String welcome(HttpSession session){

		String username = (String) session.getAttribute("username");

		log.info("username : "+username);

		User user = userService.findByUsername(username);

		log.info("full name : "+user.getFullName());

		return "Welcome, "+user.getFullName();
	}

}