package com.example.Journal.Controller;

import com.example.Journal.Repo.UserRepo;
import com.example.Journal.Service.AdminService;
import com.example.Journal.Service.UserService;
import com.example.Journal.entity.JournalEntity;
import com.example.Journal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;
    @PostMapping
    public User createJournal(@RequestBody User user){
        return adminService.addAdmin(user);
    }


    @GetMapping

    public List<User> allUsers(){
        return  userRepo.findAll() ;
    }

}
