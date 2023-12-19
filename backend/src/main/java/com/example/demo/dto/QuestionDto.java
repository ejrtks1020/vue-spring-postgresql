package com.example.demo.dto;

import com.example.demo.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import lombok.Builder;

@Getter
@NoArgsConstructor
public class QuestionDto {

    private Integer id;
    private String subject;
    private String content;
    private LocalDateTime createDate;

    
    @Builder
    public QuestionDto (Integer id, String subject, String content, LocalDateTime createDate){
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.createDate = createDate;
    }    

    public Question toEntity(){
        return Question.builder().subject(subject).content(content).build();
    }   
}
