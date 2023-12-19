package com.example.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.QuestionRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import java.lang.String;



@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private QuestionRepository questionRepository;


    // RequestParam의 name argument는 필수
    @PostMapping(value="/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam(name = "content") String content) {

        return String.format("redirect:/question/detail/%s", id);
    }
    
}