package com.example.blog.controller;

import com.example.blog.model.User;
import com.example.blog.service.UserService;
import com.example.blog.service.UserServiceImplementation;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private UserServiceImplementation userServiceImplementation;

    public AuthController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // handler method to handle user registration form request
    @PostMapping("/register")
    User registerUser(@RequestBody User user)
    {
        return userServiceImplementation.createNewUser(user);
    }

    @GetMapping("/users")
    List<User> getAllUsers()
    {
//        return userServiceImplementation.getAllUsers();
        return userServiceImplementation.findUsersWithBlogPostsAndFetchPosts();
    }
    @GetMapping(path = "users/{lazyLoading}")
    List<User> getAllUsersWithConditionalLazyLoading(@PathVariable Boolean lazyLoading)
    {
        if(lazyLoading)
            return userServiceImplementation.findUsersWithBlogPosts();

        List<User> users= userServiceImplementation.findUsersWithBlogPosts();
        return users.stream().map(element->{
            Hibernate.initialize(element.getBlogPosts());
            System.out.println(element.getBlogPosts());
            return element;
        }).collect(Collectors.toList());
    }
    @GetMapping("/users/paginate/{offset}/{pageSize}/{field}")
    Page<User> getUsersWithPagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field)
    {
        return userServiceImplementation.getUsersWithPagination(offset, pageSize, field);
    }
}