package com.friendsplushies.controller;

import com.friendsplushies.constant.ServicePath;
import com.friendsplushies.model.response.UserResponse;
import com.friendsplushies.service.UserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class HomeController extends BaseController {
    public static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    UserService userService;

    @GetMapping(ServicePath.SLASH)
    public String index() {
        return "Welcome to Friends and Plushies";
    }

    @GetMapping(ServicePath.ME)
    public UserResponse me() {
        return userService.me();
    }
}
