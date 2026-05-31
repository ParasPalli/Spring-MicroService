package com.quiz_service.quiz_service.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz_service.quiz_service.models.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {
  
}
