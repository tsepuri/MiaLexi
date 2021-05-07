package com.csds393.mialexi;

import org.junit.jupiter.api.Test;

import com.csds393.mialexi.controller.CensorshipController;
import com.csds393.mialexi.controller.TextInputController;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;


import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.csds393.mialexi.model.Censorship;
import com.csds393.mialexi.repository.CensorshipJpaRepository;

class CensorshipControllerTests {
    @InjectMocks
    private CensorshipController censorshipController;

    @Mock
    private CensorshipJpaRepository censorshipJpaRepository;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    void testAddCensoredWord() {
    	Censorship censor = new Censorship();
    	censor.setCensoredWord("dumb");
    	censor.setUsername("Hunter");
    	assertTrue(censorshipController.addCensoredWord(censor));
    }
    
    @Test
    void testRemoveCensoredWord() {
    	Censorship censor = new Censorship();
    	assertEquals(censorshipController.removeCensoredWord(censor), 0);
    }
    
}