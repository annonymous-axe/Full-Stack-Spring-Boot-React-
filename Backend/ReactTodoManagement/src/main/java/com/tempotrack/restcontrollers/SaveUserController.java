package com.tempotrack.restcontrollers;

import com.tempotrack.entities.User;
import com.tempotrack.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveUserController {

    PasswordEncoder passwordEncoder;
    UserService userService;

    public SaveUserController(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestBody User user){

        // encode password before saving it
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.save(user);

        return new ResponseEntity<String>("Save User", HttpStatus.CREATED);
    }

}
