package com.example.Journal.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UsersPrincipal implements UserDetails {
    private User user;

    public  UsersPrincipal(User user){
        this.user =user;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USERS"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }
    @Override
    public boolean isAccountNonExpired() {
        // You can customize this based on your application logic
        return true; // Assuming accounts never expire
    }

    @Override
    public boolean isAccountNonLocked() {
        // Customize this based on your application logic
        return true; // Assuming accounts are never locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Customize this based on your application logic
        return true; // Assuming credentials never expire
    }

    @Override
    public boolean isEnabled() {
        // Customize this based on your application logic
        return true; // Assuming the user is always enabled
    }
}
