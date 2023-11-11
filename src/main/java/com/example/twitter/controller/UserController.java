package com.example.twitter.controller;

import com.example.twitter.dto.LoginUserDto;
import com.example.twitter.dto.UserResponse;
import com.example.twitter.entity.User;
import com.example.twitter.repository.UserRepository;
import com.example.twitter.service.AuthenticationService;
import com.example.twitter.service.UserService;
import com.example.twitter.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/profile")
public class UserController {

    private UserService userService;
    private AuthenticationService authenticationService;

    @Autowired
    public UserController(UserService userService,AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService=authenticationService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody User user) {
        return Converter.userResponseConverter(userService.saveUser(authenticationService.register(user)));
    }

    //TODO POST MAPPINGLERI YAP

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginUserDto loginUserDto){
        User user1 = authenticationService.login(loginUserDto);
        return Converter.userResponseConverter(user1);
    }
    @DeleteMapping("/{id}")
    public UserResponse deleteUser(@PathVariable int id){
        return Converter.userResponseConverter(userService.deleteUser(id));
    }

}