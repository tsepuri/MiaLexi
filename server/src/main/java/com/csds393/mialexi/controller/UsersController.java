
package com.csds393.mialexi.controller;

import com.csds393.mialexi.model.User;
import com.csds393.mialexi.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
	/** The JPA repository */
    @Autowired
    private UserJpaRepository userJpaRepository;

	
    @GetMapping(value = "/level")
    @ResponseBody
    public Integer findLevel(@RequestParam final String username) {
        User user = userJpaRepository.findByUsername(username);
        if (user == null) {
            return 1;
        }
        else {
            return user.getPracticeLevel();
        }
    }

   
    @PostMapping(value = "/updateLevel")
    public boolean updateLevel(@RequestBody final User user) {
        User user1 = userJpaRepository.findByUsername(user.getUsername());
        if (user1 == null) {
            return false;
        }
        else {
            user1.setPracticeLevel(user.getPracticeLevel());
            userJpaRepository.save(user1);
            return true;
        }
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public boolean login(@RequestBody final User user) {
        if (userJpaRepository.findAll().stream().anyMatch(u -> (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())))) {
            return true;
        }
       else{ 
           return false;
        }
    }
    /**
	 * Used to create a User in the DB
	 * 
	 * @param users refers to the User needs to be saved
	 * @return the {@link User} created
	 */
    @PostMapping(value = "/register")
    @ResponseBody
    public boolean register(@RequestBody final User user) {
        user.setPracticeLevel(1);
        if (userJpaRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        else{ 
            userJpaRepository.save(user);
            return true;
        }
    }
}