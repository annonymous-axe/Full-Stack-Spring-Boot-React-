package com.tempotrack.services;

import com.tempotrack.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;

    public UserServiceImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public List<User> findAll() {

        TypedQuery<User> query = entityManager.createQuery("from User", User.class);

        return query.getResultList();

    }

    @Override
    public User findByUsername(String username) {

        TypedQuery<User> query = entityManager.createQuery("from User where username=:username", User.class);

        query.setParameter("username", username);

        return query.getSingleResult();

    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteByUsername(String username) {

        User user = findByUsername(username);

        entityManager.remove(user);

    }
}
