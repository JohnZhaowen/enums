package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    void save(User user);

    void update(User user);

    User query(Long id);
}
