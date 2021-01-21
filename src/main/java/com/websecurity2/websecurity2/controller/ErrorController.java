package com.websecurity2.websecurity2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ErrorController extends Exception{

    @GetMapping("/error/denied")
    public String errorDenied() {
        return "error/denied";
    }

}

