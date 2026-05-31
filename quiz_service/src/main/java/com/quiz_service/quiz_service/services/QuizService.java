package com.quiz_service.quiz_service.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz_service.quiz_service.feign.QuizInterface;
import com.quiz_service.quiz_service.models.QuestionWrapper;
import com.quiz_service.quiz_service.models.Quiz;
import com.quiz_service.quiz_service.models.QuizDto;
import com.quiz_service.quiz_service.models.Response;
import com.quiz_service.quiz_service.repos.QuizRepo;

@Service
public class QuizService {
 
  @Autowired
  private QuizRepo quizRepository;

  @Autowired
  private QuizInterface quizInterface;

  public ResponseEntity<String> createQuiz(QuizDto quizDto) {
    try {
      List<Integer> questions = quizInterface.generateQuestions(
        quizDto.getCategoryName(), quizDto.getNumQuestions()
      ).getBody();

      Quiz quiz = new Quiz();
      quiz.setTitle(quizDto.getTitle());
      quiz.setQuestionIds(questions);
      quizRepository.save(quiz);
      
      return ResponseEntity.ok("Quiz created successfully");
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Error creating quiz: " + e.getMessage());
    }
  }
  
  public ResponseEntity<List<QuestionWrapper>> getQuizQuestionById(Integer id) {
    try {
      Quiz quiz = quizRepository.findById(id).orElse(null);
      if (quiz == null) return ResponseEntity.status(404).body(new ArrayList<>());

      return quizInterface.getQuestionsById(quiz.getQuestionIds());
    } catch (Exception e) {
      return ResponseEntity.status(500).body(new ArrayList<>());
    }
  }

  public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
    ResponseEntity<Integer> score = quizInterface.calculateScore(responses);
    if (score.getStatusCode().is2xxSuccessful()) {
      return ResponseEntity.ok(score.getBody());
    } else {
      return ResponseEntity.status(500).body(0);
    }
  }
}
