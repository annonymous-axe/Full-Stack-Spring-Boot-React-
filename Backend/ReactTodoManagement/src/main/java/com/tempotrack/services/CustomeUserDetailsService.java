package com.tempotrack.services;

import com.tempotrack.entities.User;
import com.tempotrack.pojos.CustomeUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

    private UserService userService;

    public CustomeUserDetailsService(UserService userService){
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUsername(username);
        return new CustomeUserDetails(user);
    }
}
