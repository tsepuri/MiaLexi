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

    private int index;

    @Column(columnDefinition = "TEXT")
    private String fileContent;

    public Long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id=id;
    }

    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index=index;
    }
    public String getFileContent(){
        return fileContent;
    }
    public void setFileContent(String fileContent){
        this.fileContent=fileContent;
    }
}
