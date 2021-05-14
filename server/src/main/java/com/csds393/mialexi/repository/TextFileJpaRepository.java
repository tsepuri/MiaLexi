package com.csds393.mialexi.repository;

import org.springframework.web.bind.annotation.RestController;
import com.csds393.mialexi.model.TextFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional 
@Component
public interface TextFileJpaRepository extends JpaRepository<TextFile, Long>{

    List<TextFile> findByUsername(String username);

    ////@Transactional 
    //long deleteByUsernameAndCensoredWord(@Param("username") String username, @Param("censoredWord") String censoredWord);

    TextFile findByUsernameAndFileName(@Param("username") String username, @Param("fileName") String fileName);
}