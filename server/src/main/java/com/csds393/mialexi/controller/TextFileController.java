package com.csds393.mialexi.controller;

import com.csds393.mialexi.model.TextFile;
import com.csds393.mialexi.repository.TextFileJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.LinkedList;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/textFile")
public class TextFileController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
	/** The JPA repository */
    @Autowired
    private TextFileJpaRepository textFileJpaRepository;


    @GetMapping(value = "/{username}")
    public List<String> getTextFiles(@PathVariable final String username) {
        List<TextFile> textFiles = textFileJpaRepository.findByUsername(username);
        List<String> stringFiles = new LinkedList<String>();
        for (TextFile textFile : textFiles) {
            stringFiles.add(textFile.getFileName());
        }
        return stringFiles;
    } 
    
    @PostMapping(value = "/add")
    public boolean addTextFile(@RequestBody final TextFile textFile){
        textFile.setFileContent("Apple carrot crocodile Apple carrot crocodile Apple carrot crocodile Apple carrot crocodile Apple carrot crocodile Apple carrot crocodile Apple carrot crocodile Apple carrot crocodile Apple carrot crocodile Apple carrot crocodile Apple carrot crocodile Apple");
        textFileJpaRepository.save(textFile);
        return true;
        
    } 

    @GetMapping(value = "/get")
    public TextFile getTextFileString(@RequestParam final String username, @RequestParam final String fileName) {
        return textFileJpaRepository.findByUsernameAndFileName(username, fileName);
    }
    

    @PostMapping(value = "/updateIndex")
    public boolean updateIndex(@RequestBody final TextFile textFile) {
        TextFile text = textFileJpaRepository.findByUsernameAndFileName(textFile.getUsername(), textFile.getFileName());
        if (text == null) {
            return false;
        }
        else {
            text.setIndex(textFile.getIndex());
            textFileJpaRepository.save(text);
            return true;
        }
    }
    
    ///@PostMapping(value = "/remove")
    ///public long removeCensoredWord(@RequestBody final Censorship censorship){
     ///   return censorshipJpaRepository.deleteByUsernameAndCensoredWord(censorship.getUsername(), censorship.getCensoredWord());
    ///}
        
    
}
