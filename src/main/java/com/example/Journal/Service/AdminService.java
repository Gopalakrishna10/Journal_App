package com.example.Journal.Service;

import com.example.Journal.Repo.UserRepo;
import com.example.Journal.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminService {

    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User addAdmin(User users){
        users.setPassword(encoder.encode(users.getPassword()));
        users.setRoles(Arrays.asList("Admin"));
        userRepo.save(users);
        return users;
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }
}
