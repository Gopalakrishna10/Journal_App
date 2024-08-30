package com.example.Journal.Service;

import com.example.Journal.Repo.JournalRepo;
import com.example.Journal.Repo.UserRepo;
import com.example.Journal.entity.JournalEntity;
import com.example.Journal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepo journalRepo;
    @Autowired

    private UserRepo userRepo;


    @Transactional
    public JournalEntity addJournal(JournalEntity journal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        journal.setDate(LocalDateTime.now());
        User user = userRepo.findByuserName(username);
        journal.setUsers(user);
        return journalRepo.save(journal);


    }

    public void update(JournalEntity journal){
        journalRepo.save(journal);
    }



    public ResponseEntity<List<JournalEntity>> getJournal(){
        return new ResponseEntity<>(journalRepo.findAll(), HttpStatus.OK);
    }

    public Optional<JournalEntity> getByIdJournal(Long id){
        return journalRepo.findById(id);
    }

    public void deleteJournal(Long id){
          journalRepo.deleteById(id);
    }

}
