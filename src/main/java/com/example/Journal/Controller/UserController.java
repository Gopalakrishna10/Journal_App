package com.example.Journal.Controller;


import com.example.Journal.Repo.UserRepo;
import com.example.Journal.Service.UserService;
import com.example.Journal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;


    @PutMapping()
    public ResponseEntity<User> updateUser( @RequestBody User updatedUser) {
        User user = userService.updateUser(updatedUser);
        return ResponseEntity.ok(user);
    }



    @GetMapping

    public ResponseEntity< User> getUser(){
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        if(username != null) {
             User get=userRepo.findByuserName(username);
            return new ResponseEntity<>(get, HttpStatus.OK);
        }
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
