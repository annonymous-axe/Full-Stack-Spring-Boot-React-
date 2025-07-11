package com.tempotrack.restcontrollers;

import com.tempotrack.entities.User;
import com.tempotrack.services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtService jwtService;

    @PostMapping("/loginUser")
    public ResponseEntity<String> home(@RequestBody User user) {
        log.info("username : "+user.getUsername());
        log.info("password : "+user.getPassword());
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()) {
            log.info("success");
            return new ResponseEntity<String>(jwtService.generateToken(user.getUsername()), HttpStatus.ACCEPTED);
        }
        log.info("fail");
        return new ResponseEntity<String>("Success", HttpStatus.ACCEPTED);
    }

//    @PostMapping("/loginUser")
//    public String loginUser(@RequestBody User user){
//
//        System.out.println("username : "+user.getUsername());
//        System.out.println("password : "+user.getPassword());
//
//
//        return "login user";
//    }
}