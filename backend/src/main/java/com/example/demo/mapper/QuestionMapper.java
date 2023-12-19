package com.example.demo.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import com.example.demo.dto.QuestionDto;
import com.example.demo.entity.Question;

@Component
public class QuestionMapper {
    public QuestionDto toDto(Question question){
        Integer id = question.getId();
        String subject = question.getSubject();
        String content = question.getContent();
        LocalDateTime createDate = question.getCreateDate();
        return QuestionDto.builder().id(id).content(content).subject(subject).createDate(createDate).build();
    }
}
