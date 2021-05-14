package com.csds393.mialexi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class PracticeFile {

    @Id
    @GeneratedValue
    private long id;

    private int name;

    @Column(columnDefinition = "TEXT")
    private String fileContent;

    public Long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id=id;
    }

    public int getName() {
        return name;
    }
    
    public void setName(int name) {
        this.name=name;
    }
    public String getFileContent(){
        return fileContent;
    }
    public void setFileContent(String fileContent){
        this.fileContent=fileContent;
    }
}
