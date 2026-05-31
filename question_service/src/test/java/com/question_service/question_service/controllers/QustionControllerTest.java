package com.question_service.question_service.controllers;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.question_service.question_service.models.Question;

@SpringBootTest
public class QustionControllerTest {
  
  @Autowired
  QuestionController questionController;

  @Test
  public void testGetAllQuestions() {
    List<Question> questions = questionController.getAllQuestions().getBody();
    assert questions.size() > 0;
  }

  @Test
  public void testGetQuestionsByCategory() {
    List<Question> questions = questionController.getQuestionsByCategory("Science").getBody();
    assert questions.size() > 0;
    for (Question q : questions) {
      assert q.getCategory().equals("Science");
    }
  }

  @Test
  public void testAddQuestion() {
    Question newQuestion = new Question();
    newQuestion.setCategory("History");
    newQuestion.setQuestionTitle("Who was the first president of the United States?");
    newQuestion.setOption1("George Washington");
    newQuestion.setOption2("Thomas Jefferson");
    newQuestion.setOption3("Abraham Lincoln");
    newQuestion.setOption4("John Adams");
    newQuestion.setRightAnswer("George Washington");
    newQuestion.setCategory("Easy");

    String response = questionController.addQuestion(newQuestion).getBody();
    assert response.equals("Question added successfully");
  }

  @Test
  public void testGenerateQuestions() {
    List<Integer> questionIds = questionController.generateQuestions("Science", 2).getBody();
    assert questionIds.size() == 2;
  }
}
