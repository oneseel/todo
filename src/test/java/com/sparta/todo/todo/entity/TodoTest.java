package com.sparta.todo.todo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sparta.todo.todo.dto.TodoCompletedRequestDto;
import com.sparta.todo.todo.dto.TodoRequestDto;
import com.sparta.todo.todo.dto.TodoUpdateRequestDto;
import com.sparta.todo.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TodoTest {

  @Test
  @DisplayName("Todo 엔터티 생성 - 성공")
  void createTodoEntity() {
    // Given
    TodoRequestDto todoRequestDto = new TodoRequestDto();
    todoRequestDto.setTitle("title");
    todoRequestDto.setContents("content");

    User user = new User();
    user.setUsername("author");

    // When
    Todo todo = new Todo(todoRequestDto, user);

    // Then
    assertNotNull(todo);
    assertEquals(todoRequestDto.getTitle(), todo.getTitle());
    assertEquals(todoRequestDto.getContents(), todo.getContents());
    assertEquals(user.getUsername(), todo.getAuthor());
    assertFalse(todo.isCompleted());
    assertEquals(user, todo.getUser());
  }

  @Test
  @DisplayName("Todo 엔터티 update - 성공")
  void updateTodoEntity() {
    // Given
    TodoRequestDto todoRequestDto = new TodoRequestDto();
    todoRequestDto.setTitle("title");
    todoRequestDto.setContents("content");

    User user = new User();
    user.setUsername("author");

    Todo todo = new Todo(todoRequestDto, user);

    TodoUpdateRequestDto updateRequestDto = new TodoUpdateRequestDto();
    updateRequestDto.setTitle("title update");
    updateRequestDto.setContents("content update");

    // When
    todo.update(updateRequestDto);

    // Then
    assertEquals(updateRequestDto.getTitle(), todo.getTitle());
    assertEquals(updateRequestDto.getContents(), todo.getContents());
  }

  @Test
  @DisplayName("Todo 엔터티 completed - 성공")
  void completeTodoEntity() {
    // Given
    TodoRequestDto todoRequestDto = new TodoRequestDto();
    todoRequestDto.setTitle("title");
    todoRequestDto.setContents("content");

    User user = new User();
    user.setUsername("author");

    Todo todo = new Todo(todoRequestDto, user);

    TodoCompletedRequestDto completedRequestDto = new TodoCompletedRequestDto();
    completedRequestDto.setCompleted(true);

    // When
    todo.completed(completedRequestDto);

    // Then
    assertTrue(todo.isCompleted());
  }
}