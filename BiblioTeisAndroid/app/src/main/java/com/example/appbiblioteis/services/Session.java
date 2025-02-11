package com.example.appbiblioteis.services;

import com.example.appbiblioteis.API.models.User;

public class Session {
    private static Session current = null;
    private User user;

    private Session() {
        user = new User();
    }

    public static Session getInstance() {
        if (current == null) {
            current = new Session();
        }
        return current;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
