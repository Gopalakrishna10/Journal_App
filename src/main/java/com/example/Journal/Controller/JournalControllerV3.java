package com.example.Journal.Controller;

import com.example.Journal.Service.JournalService;
import com.example.Journal.Service.UserService;
import com.example.Journal.entity.JournalEntity;
import com.example.Journal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalControllerV3 {

    @Autowired
    private JournalService journalService;
    @Autowired
    private UserService userService;



    @PostMapping("add")
    public ResponseEntity<JournalEntity> createJournal(@RequestBody JournalEntity journal){
        try{
            JournalEntity createdJournal = journalService.addJournal(journal);
          return  new ResponseEntity<>(createdJournal,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get")
    public ResponseEntity<List<JournalEntity >>getJournalbyUser(){
        User user = userService.byUsername().getBody();
        List<JournalEntity> jon=user.getJournalEntries();

        if(jon != null && !jon.isEmpty()){
            return  new ResponseEntity<>(jon, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    @GetMapping("gets")
    public ResponseEntity<List<JournalEntity >>getJournal(){
        return journalService.getJournal();
    }
    @GetMapping("/id/{id}")
    public JournalEntity getByIdJournal(@PathVariable Long id){
        return journalService.getByIdJournal(id).orElse(null);
    }


    @PutMapping("/id/{username}/{id}")
    public JournalEntity updateJournal(@PathVariable Long id,JournalEntity journal, @PathVariable String username){
        JournalEntity old =journalService.getByIdJournal(id).orElse(null);
        if(old!=null){
            old.setTitle(journal.getTitle() != null && !journal.getTitle().equals("")?journal.getTitle() : old.getTitle());
            old.setContent(journal.getContent() != null && !journal.getContent().equals("")? journal.getContent() : old.getContent());
        }
        journalService.update(old);
        return old;
    }

    @DeleteMapping("/id/{id}")
    public boolean  deleteJournal(@PathVariable Long id){
         journalService.deleteJournal(id);
         return true;

    }

}
