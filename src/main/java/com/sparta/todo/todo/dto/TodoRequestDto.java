package com.sparta.todo.todo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoRequestDto {

  private String title;
  private String contents;
  private String author;
  private boolean completed;
}
