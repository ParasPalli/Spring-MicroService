package com.quiz_service.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz_service.quiz_service.models.QuestionWrapper;
import com.quiz_service.quiz_service.models.Response;

@FeignClient(name="question-service")
public interface QuizInterface {
  @GetMapping("/questions/generate-quetion-ids")
  public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam Integer numQuestions);

  @PostMapping("/questions/get-questions-by-ids")
  public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> questionIds);

  @PostMapping("/questions/get-score")
  public ResponseEntity<Integer> calculateScore(@RequestBody List<Response> responses);
}
