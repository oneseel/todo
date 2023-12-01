package com.sparta.todo.todo.dto;

import static org.junit.jupiter.api.Assertions.*;

import com.sparta.todo.todo.entity.Todo;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class TodoResponseDtoTest {

  @Test
  void todoResponseDtoTest() {
    // Given
    Todo todo = new Todo();
    todo.setId(1L);
    todo.setTitle("Title");
    todo.setAuthor("Author");
    todo.setContents("Contents");
    todo.setCreatedAt(LocalDateTime.now());
    todo.setCompleted(true);

    // When
    TodoResponseDto responseDto = new TodoResponseDto(todo);

    // Then
    assertNotNull(responseDto);
    assertEquals(1L, responseDto.getId());
    assertEquals("Title", responseDto.getTitle());
    assertEquals("Author", responseDto.getAuthor());
    assertEquals("Contents", responseDto.getContents());
    assertNotNull(responseDto.getCreatedAt());
    assertTrue(responseDto.isCompleted());
  }

}