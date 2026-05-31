package com.question_service.question_service.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.question_service.question_service.models.Question;

public interface QuestionRepo extends JpaRepository<Question, Integer> {
  
  List<Question> findByCategory(String category);

  @Query(value = "SELECT q.id FROM question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
  List<Integer> findRandomQuestionsByCategory(String category, int numQ);
}
