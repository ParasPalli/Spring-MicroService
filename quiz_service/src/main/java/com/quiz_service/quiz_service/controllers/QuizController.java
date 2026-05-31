package com.quiz_service.quiz_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz_service.quiz_service.models.QuizDto;
import com.quiz_service.quiz_service.services.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
 
  @Autowired
  private QuizService quizService;

  @PostMapping("/create")
  public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
    return quizService.createQuiz(quizDto);
  }

  // @GetMapping("/get-question/{id}")
  // public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id) {

  // }

  // @PostMapping("submit/{id}")
  // public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
    
  // }

}
