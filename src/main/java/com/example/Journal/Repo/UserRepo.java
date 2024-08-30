package com.example.Journal.Repo;

import com.example.Journal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User findByuserName(String Username);
    void deleteByUserName(String username);
}
