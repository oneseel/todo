package com.sparta.todo.todo.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TodoRequestDtoTest {

  @Test
  void todoRequestDtoTest() {
    TodoRequestDto requestDto = new TodoRequestDto();

    requestDto.setTitle("title");
    requestDto.setContents("content");
    requestDto.setAuthor("author");
    requestDto.setCompleted(false);

    assertNotNull(requestDto.getTitle());
    assertNotNull(requestDto.getContents());
    assertNotNull(requestDto.getAuthor());
    assertEquals("title", requestDto.getTitle());
    assertEquals("content", requestDto.getContents());
    assertEquals("author", requestDto.getAuthor());
    assertFalse(requestDto.isCompleted());
  }

  @Test
  void todoUpdateRequestDtoTest() {
    TodoUpdateRequestDto requestDto = new TodoUpdateRequestDto();

    requestDto.setTitle("title update");
    requestDto.setContents("content update");

    assertNotNull(requestDto.getTitle());
    assertNotNull(requestDto.getContents());
    assertEquals("title update", requestDto.getTitle());
    assertEquals("content update", requestDto.getContents());
  }

  @Test
  void todoCompletedRequestDtoTest() {
    TodoCompletedRequestDto requestDto = new TodoCompletedRequestDto();

    requestDto.setCompleted(true);

    assertTrue(requestDto.isCompleted());
  }
}