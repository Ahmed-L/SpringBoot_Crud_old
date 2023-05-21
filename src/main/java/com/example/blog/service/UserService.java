package com.example.blog.service;

import com.example.blog.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User getUserByEmail(String email);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    User updateUserById(Long id, User user);
    User createNewUser(User user);
    String deleteUserById(Long id);

}