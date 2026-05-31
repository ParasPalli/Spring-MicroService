package com.question_service.question_service.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.question_service.question_service.models.Question;
import com.question_service.question_service.models.QuestionWrapper;
import com.question_service.question_service.models.Response;
import com.question_service.question_service.repos.QuestionRepo;

@Service
public class QuestionService {
  
  @Autowired
  QuestionRepo questionRepo;

  public ResponseEntity<List<Question>> getAllQuestions() {
    try {
      List<Question> questions = questionRepo.findAll();
      return ResponseEntity.ok(questions);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new ArrayList<>());
    }
  }

  public ResponseEntity<List<Question>> getQuetionsByCategory(String category) {
    try {
      List<Question> questions = questionRepo.findByCategory(category);
      return ResponseEntity.ok(questions);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new ArrayList<>());
    }
  }

  public ResponseEntity<String> addQuestion(Question question) {
    try {
      questionRepo.save(question);
      return ResponseEntity.ok("Question added successfully");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Failed to add question");
    }
  }

  public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer numQuestions) {
    List<Integer> questionIds = questionRepo.findRandomQuestionsByCategory(category, numQuestions);
    return ResponseEntity.ok(questionIds);
  }

  public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(List<Integer> questionIds) {
    List<Question> questions = questionRepo.findAllById(questionIds);

    List<QuestionWrapper> wrappers = questions.stream().map(question -> {
      QuestionWrapper wrapper = new QuestionWrapper();
      wrapper.setId(question.getId());
      wrapper.setQuestionTitle(question.getQuestionTitle());
      wrapper.setOption1(question.getOption1());
      wrapper.setOption2(question.getOption2());
      wrapper.setOption3(question.getOption3());
      wrapper.setOption4(question.getOption4());
      
      return wrapper;
    }).toList();

    return ResponseEntity.ok(wrappers);
  }

  public ResponseEntity<Integer> calculateScore(List<Response> responses) {
    int score = 0;
    for (Response response : responses) {
      Question question = questionRepo.findById(response.getId()).orElse(null);
      if (question != null && response.getResponse().equals(question.getRightAnswer())) score++;
    }
    return ResponseEntity.ok(score);
  }
}
