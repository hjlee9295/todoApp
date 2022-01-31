package com.example.todoApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //일반 컨트롤러는 페이지를 반환 <> RestController는 데이터를 반환
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "Hojin Lee");
        return "greetings"; //templates/greetings.mustache -> sent to Browser
    }
}
