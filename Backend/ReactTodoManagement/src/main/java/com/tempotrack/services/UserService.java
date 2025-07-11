package com.tempotrack.services;

import com.tempotrack.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByUsername(String username);

    void save(User user);

    void deleteByUsername(String username);

}
