package com.sparta.todo.comment.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class CommentRequestDtoTest {

  @Test
  void commentRequestDtoTest() {
    CommentRequestDto requestDto = new CommentRequestDto();
    requestDto.setComment("comment");
    requestDto.setAuthor("author");

    assertNotNull(requestDto.getComment());
    assertNotNull(requestDto.getAuthor());
    assertEquals("comment", requestDto.getComment());
    assertEquals("author", requestDto.getAuthor());
  }

  @Test
  void commentUpdateRequestDtoTest() {
    CommentUpdateRequestDto requestDto = new CommentUpdateRequestDto();
    requestDto.setComment("comment update");

    assertEquals("comment update", requestDto.getComment());
  }
}