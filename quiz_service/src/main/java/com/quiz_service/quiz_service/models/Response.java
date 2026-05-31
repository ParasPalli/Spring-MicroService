package com.quiz_service.quiz_service.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {
  private Integer id;
  private String response;
}
