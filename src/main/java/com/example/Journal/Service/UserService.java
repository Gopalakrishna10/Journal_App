package com.example.Journal.Service;

import com.example.Journal.Repo.UserRepo;
import com.example.Journal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired

    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User addUser(User users){
        users.setPassword(encoder.encode(users.getPassword()));
        users.setRoles(Arrays.asList("USER"));
        userRepo.save(users);
        return users;
    }


    public String verify(User users) {


        return "success";
    }



    public ResponseEntity<List<User>> getUser(){
        return new ResponseEntity<List<User>>(userRepo.findAll(),HttpStatus.OK);

    }

    public ResponseEntity<User> byUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
     User user=  userRepo.findByuserName(username);
         return new ResponseEntity<>(user,HttpStatus.OK);
    }
    
    public Optional<User> getByIdUser(Long id){
        return userRepo.findById(id);
    }

    public User finByUsername(String username){
       return userRepo.findByuserName(username);

    }



//    post

    public User updateUser( User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userdb = userRepo.findByuserName(userName);
        userdb.setUserName(user.getUserName());
        userdb.setPassword(user.getPassword());
        return userRepo.save(user);
    }

}
