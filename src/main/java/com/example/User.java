package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;

    protected User() {}

    public User(String firstName, String password) {
        this.name = firstName;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, name='%s', password='%s']", id, name, password);
    }

}