package com.quiz_service.quiz_service.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.quiz_service.quiz_service.models.QuizDto;

@SpringBootTest
public class QuizServiceTest {
  
  @Autowired
  private QuizService quizService;

  @Test
  public void createQuiz() {
    QuizDto quizDto = new QuizDto();
    quizDto.setCategoryName("Science");
    quizDto.setNumQuestions(2);
    quizDto.setTitle("Science Quiz");

    assertEquals("Quiz created successfully", quizService.createQuiz(quizDto).getBody());
  }
}
