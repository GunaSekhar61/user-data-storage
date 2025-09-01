package com.user_data_storage.user_info_storage.controllers;

import com.user_data_storage.user_info_storage.models.User;
import com.user_data_storage.user_info_storage.repositery.CreateUserRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class UserSignUp {
    @Autowired
    private CreateUserRecordRepo repo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public User registerUser(@RequestBody User user){
        String encodedPwd=passwordEncoder.encode(user.getPassword());
        String generateUserName= user.getEmail().split("@")[0];
        user.setPassword(encodedPwd);
        user.setUserName(generateUserName);
        repo.save(user);
        return user;
    }
}
