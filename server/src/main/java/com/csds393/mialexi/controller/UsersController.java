
package com.csds393.mialexi.controller;

import com.csds393.mialexi.model.User;
import com.csds393.mialexi.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
	/** The JPA repository */
    @Autowired
    private UserJpaRepository userJpaRepository;

	/**
	 * Used to fetch all the users from DB
	 * 
	 * @return list of {@link User}
	 */
    @GetMapping(value = "/all")
    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    @CrossOrigin("http://localhost:8081")
    @GetMapping(value = "/level")
    @ResponseBody
    public Integer findLevel(@PathVariable final String username) {
        User user = userJpaRepository.findByUsername(username);
        if (user == null) {
            return 1;
        }
        else {
            return user.getLevel();
        }
    }
    
    @CrossOrigin("http://localhost:8081")
    @PostMapping(value = "/login")
    @ResponseBody
    public boolean login(@RequestBody final User user) {
        if (findAll().stream().anyMatch(u -> (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())))) {
            return true;
        }
        return false;
    }
    /**
	 * Used to create a User in the DB
	 * 
	 * @param users refers to the User needs to be saved
	 * @return the {@link User} created
	 */
    @CrossOrigin("http://localhost:8081")
    @PostMapping(value = "/register")
    @ResponseBody
    public boolean register(@RequestBody final User user) {
        user.setLevel(1);
        if (userJpaRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        userJpaRepository.save(user);
        return true;
    }
}