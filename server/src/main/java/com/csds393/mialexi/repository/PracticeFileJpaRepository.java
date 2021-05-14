package com.csds393.mialexi.repository;


import com.csds393.mialexi.model.PracticeFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PracticeFileJpaRepository extends JpaRepository<PracticeFile, Long> {

    PracticeFile findByname(int name);
        
}
