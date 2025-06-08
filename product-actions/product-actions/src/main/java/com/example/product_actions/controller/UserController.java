//package com.example.product_actions.controller;
//
//import com.example.product_actions.model.User;
//import com.example.product_actions.service.UserServiceImp;
//import com.example.product_actions.model.User;
//
//import com.example.product_actions.service.UserServiceImp;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("api/user")
//public class UserController {
//    @Autowired
//    private UserServiceImp userServiceImp;
//
//    @GetMapping("/login")
//    public String Login(){
//        return "you re authenticated";
//    }
//    @PostMapping("/create")
//    public User createUser(@RequestBody User user) {
//        return userServiceImp.createUser(user);
//    }
//}
