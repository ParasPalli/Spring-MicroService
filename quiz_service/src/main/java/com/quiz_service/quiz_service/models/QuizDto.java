package com.quiz_service.quiz_service.models;

import lombok.Data;

@Data
public class QuizDto {
  String categoryName;
  Integer numQuestions;
  String title;
}
