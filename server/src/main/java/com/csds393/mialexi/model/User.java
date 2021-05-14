package com.csds393.mialexi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
public class User {

    private Long id;
    private String username;
    private String password;
    private Integer practiceLevel;
    
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPracticeLevel() {
        return practiceLevel;
    }

    public void setPracticeLevel(Integer practiceLevel) {
        this.practiceLevel = practiceLevel;
    }
}
