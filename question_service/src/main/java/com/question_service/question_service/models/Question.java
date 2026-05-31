package com.question_service.question_service.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String questionTitle;
  private String option1;
  private String option2;
  private String option3;
  private String option4;
  private String rightAnswer;

  @Column(name = "difficulty_level")
  private String difficultyLevel;

  private String category;

}
