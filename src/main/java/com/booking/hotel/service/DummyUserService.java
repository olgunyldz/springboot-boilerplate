package com.booking.hotel.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class DummyUserService {

    private Map<String, User> users = new HashMap<>();

    @PostConstruct
    public void initialize() {
        users.put("admin", new User("admin", "admin",new ArrayList<>()));
        users.put("user1", new User("user1", "user1",new ArrayList<>()));
        users.put("user2", new User("user2", "user2",new ArrayList<>()));
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }
}
