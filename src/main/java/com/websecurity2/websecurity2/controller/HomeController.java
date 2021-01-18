package com.websecurity2.websecurity2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute(authentication);
        }
        return "home";
    }

    @GetMapping("/admin")
    public String admin(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute(authentication);
        }
        return "admin/admin";
    }

}
