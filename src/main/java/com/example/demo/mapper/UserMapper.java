package com.example.demo.mapper;

import com.example.demo.model.User;

public interface UserMapper {

    void save(User user);

    void update(User user);

    User query(Long id);

}
