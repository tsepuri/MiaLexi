package com.csds393.mialexi.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class TextFile {
  
    private String fileName;
    private String username;
    private int index;
    
    @Column(length=511,columnDefinition="TEXT")
    private String fileContent;
    
    @Id
    @GeneratedValue
    private long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIndex(){
        return index;
    }
    public void setIndex(int index){
        this.index = index;
    }
    public String getFileContent(){
        return fileContent;
    }
    public void setFileContent(String fileContent){
        this.fileContent=fileContent;
    }
}
