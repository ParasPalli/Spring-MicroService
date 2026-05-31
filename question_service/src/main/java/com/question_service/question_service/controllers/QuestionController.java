package com.question_service.question_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.question_service.question_service.models.Question;
import com.question_service.question_service.models.QuestionWrapper;
import com.question_service.question_service.models.Response;
import com.question_service.question_service.services.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {
  
  @Autowired
  QuestionService questionService;

  @GetMapping("/all")
  public ResponseEntity<List<Question>> getAllQuestions() {
      return questionService.getAllQuestions();
  }
  
  @GetMapping("category/{category}")
  public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
      return questionService.getQuetionsByCategory(category);
  }

  @PostMapping("/add")
  public ResponseEntity<String> addQuestion(@RequestBody Question question) {
    return questionService.addQuestion(question);
  }

  @GetMapping("/generate-quetion-ids")
  public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam Integer numQuestions) {
    return questionService.getQuestionForQuiz(category, numQuestions);
  }

  @PostMapping("/get-questions-by-ids")
  public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(@RequestBody List<Integer> questionIds) {
    return questionService.getQuestionsByIds(questionIds);
  }

  @PostMapping("/get-score")
  public ResponseEntity<Integer> calculateScore(@RequestBody List<Response> responses) {
    return questionService.calculateScore(responses);
  }
}
