package com.example.Journal.Repo;

import com.example.Journal.entity.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepo extends JpaRepository<JournalEntity,Long> {
}

