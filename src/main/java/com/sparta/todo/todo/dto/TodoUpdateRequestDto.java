package com.sparta.todo.todo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoUpdateRequestDto {

  private String title;
  private String contents;
}
