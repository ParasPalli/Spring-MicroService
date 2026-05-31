package com.quiz_service.quiz_service.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizDto {
  String categoryName;
  Integer numQuestions;
  String title;
}
