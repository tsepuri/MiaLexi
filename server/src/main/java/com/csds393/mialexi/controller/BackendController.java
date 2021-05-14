package com.csds393.mialexi.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.servlet.ModelAndView;
 // Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
    // Required because of 'mode: history' usage in frontend routing, see README for further details
    @RestController
    public class BackendController implements ErrorController {
        
        private static final String PATH = "/error";
        
        @RequestMapping(value = PATH)
        public ModelAndView saveLeadQuery() {           
            return new ModelAndView("forward:/");
        }
    
        @Override
        public String getErrorPath() {
            return PATH;
        }
    }