package com.websecurity2.websecurity2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error/denied")
    public String errorDenied() {
        return "error/denied";
    }

}
