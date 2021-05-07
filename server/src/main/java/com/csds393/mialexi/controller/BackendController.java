package com.csds393.mialexi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

 // Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
    // Required because of 'mode: history' usage in frontend routing, see README for further details
public class BackendController {

    @RequestMapping(value = "{_:^(?!index\\.html|api).$}")
    public String redirectApi() {
        return "forward:/";
    }
}