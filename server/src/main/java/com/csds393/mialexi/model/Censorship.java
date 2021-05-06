package com.csds393.mialexi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Censorship {

    private Long id;
    private String username;
    private String censoredWord;
    
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCensoredWord() {
        return censoredWord;
    }
    public void setCensoredWord(String censoredWord){
        this.censoredWord = censoredWord;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }
   

}
