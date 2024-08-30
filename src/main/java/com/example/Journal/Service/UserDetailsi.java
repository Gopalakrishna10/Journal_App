package com.example.Journal.Service;

import com.example.Journal.Repo.UserRepo;
import com.example.Journal.entity.User;
import com.example.Journal.entity.UsersPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsi implements UserDetailsService {


    @Autowired

    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(@Lazy  String username) throws UsernameNotFoundException {

        User user = userRepo.findByuserName(username);
        if (user != null) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
            return new UsersPrincipal(user);
        }

        throw new UsernameNotFoundException("User Not Found");

    }
}
