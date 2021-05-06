package com.csds393.mialexi.repository;

import org.springframework.web.bind.annotation.RestController;
import com.csds393.mialexi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserJpaRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    
    User findByUsernameAndPassword(String username, String password);
    
}