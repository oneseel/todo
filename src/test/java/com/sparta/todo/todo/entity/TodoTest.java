package com.sparta.todo.todo.entity;

import static org.junit.jupiter.api.Assertions.*;

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
    todoRequestDto.setTitle("할일카드 제목");
    todoRequestDto.setContents("할일카드 내용");

    User user = new User();
    user.setUsername("작성자");

    // When
    Todo todo = new Todo(todoRequestDto, user);
    todo.setId(1L);

    // Then
    assertNotNull(todo);
    assertNotNull(todo.getId());
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
    todoRequestDto.setTitle("기존 제목");
    todoRequestDto.setContents("기존 내용");

    User user = new User();
    user.setUsername("작성자");

    Todo todo = new Todo(todoRequestDto, user);

    TodoUpdateRequestDto updateRequestDto = new TodoUpdateRequestDto();
    updateRequestDto.setTitle("새로운 제목");
    updateRequestDto.setContents("새로운 내용");

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
    todoRequestDto.setTitle("할일카드 제목");
    todoRequestDto.setContents("할일카드 내용");

    User user = new User();
    user.setUsername("작성자");

    Todo todo = new Todo(todoRequestDto, user);

    TodoCompletedRequestDto completedRequestDto = new TodoCompletedRequestDto();
    completedRequestDto.setCompleted(true);

    // When
    todo.completed(completedRequestDto);

    // Then
    assertTrue(todo.isCompleted());
  }
}