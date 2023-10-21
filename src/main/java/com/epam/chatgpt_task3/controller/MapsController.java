package com.epam.chatgpt_task3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapsController {

    @GetMapping("/maps")
    public String displayMap() {
        return "maps"; // Return the name of your HTML/Thymeleaf template
    }
}
