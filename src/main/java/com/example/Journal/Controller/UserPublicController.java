package com.example.Journal.Controller;

import com.example.Journal.Service.UserService;
import com.example.Journal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class UserPublicController {

    @Autowired
    private UserService userService;

    @PostMapping("createuser")
    public User createJournal(@RequestBody User user){
        return  userService.addUser(user);
    }

    @PostMapping("login")
    public String login(@RequestBody User users){
        System.out.println(users);
        return userService.verify(users);
    }

}
