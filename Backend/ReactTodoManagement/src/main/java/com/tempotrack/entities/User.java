package com.tempotrack.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    private String username;

    private String password;

    public User(String fullName, String username, String password){
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                ", Full Name='" + fullName + '\'' +
                ", email='" + username + '\'' +
                '}';
    }

}
