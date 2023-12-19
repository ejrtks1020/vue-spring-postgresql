package com.example.demo.controllers;

import java.util.List;

import com.example.demo.dto.QuestionDto;
import com.example.demo.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import com.example.demo.mapper.QuestionMapper;


@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @GetMapping("/list")
    public String list(Model model){
        System.out.println("question list");
        List<QuestionDto> questionList = this.questionService.getList().stream().map(questionMapper::toDto).toList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @GetMapping(value="/detail/{id}")
    public String getQuestionDetail(Model model, @PathVariable("id") Integer id) {
        QuestionDto question = questionMapper.toDto(this.questionService.getQuestion(id));
        model.addAttribute("question", question);
        return "question_detail";
    }
    
}
