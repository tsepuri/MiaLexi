package com.csds393.mialexi.controller;

import com.csds393.mialexi.model.Censorship;
import com.csds393.mialexi.repository.CensorshipJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.LinkedList;

@RestController
@CrossOrigin
@RequestMapping("/censorship")
public class CensorshipController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
	/** The JPA repository */
    @Autowired
    private CensorshipJpaRepository censorshipJpaRepository;


    @GetMapping(value = "/{username}")
    public List<String> getUserCensored(@PathVariable final String username) {
        List<Censorship> usercensored = censorshipJpaRepository.findAllByUsername(username);
        List<String> stringcensored = new LinkedList<String>();
        for (Censorship rice : usercensored) {
            stringcensored.add(rice.getCensoredWord());
        }
        return stringcensored;
    } 
    @PostMapping(value = "/add")
    public boolean addCensoredWord(@RequestBody final Censorship censorship){
        censorshipJpaRepository.save(censorship);
        return true;
    } 

    @PostMapping(value = "/remove")
    public long removeCensoredWord(@RequestBody final Censorship censorship){
        return censorshipJpaRepository.deleteByUsernameAndCensoredWord(censorship.getUsername(), censorship.getCensoredWord());
    }
   
}
