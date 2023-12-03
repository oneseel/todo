package com.sparta.todo.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.sparta.todo.todo.dto.TodoRequestDto;
import com.sparta.todo.todo.dto.TodoResponseDto;
import com.sparta.todo.todo.entity.Todo;
import com.sparta.todo.todo.repository.TodoRepository;
import com.sparta.todo.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateTodoTest {

  @Mock
  private TodoRepository todoRepository;

  @InjectMocks
  private TodoService todoService;

  @Test
  @DisplayName("할일카드 작성 - 성공")
  void createTodoTestSuccess() {

    // given
    TodoRequestDto requestDto = new TodoRequestDto();
    requestDto.setTitle("title");
    requestDto.setContents("content");
    requestDto.setCompleted(false);

    User loginUser = new User();
    requestDto.setAuthor(loginUser.getUsername());

    Todo todo = new Todo();
    todo.setId(1L);
    todo.setTitle(requestDto.getTitle());
    todo.setContents(requestDto.getContents());
    todo.setAuthor(requestDto.getAuthor());
    todo.setCompleted(requestDto.isCompleted());

    given(todoRepository.save(any(Todo.class))).willReturn(todo);

    // when
    TodoResponseDto saveTodo = todoService.createTodo(requestDto, loginUser);

    // then
    assertNotNull(saveTodo);
    assertNotNull(saveTodo.getId());
    assertEquals(requestDto.getTitle(), saveTodo.getTitle());
    assertEquals(requestDto.getContents(), saveTodo.getContents());
    assertEquals(requestDto.getAuthor(), saveTodo.getAuthor());
    assertEquals(requestDto.isCompleted(), saveTodo.isCompleted());
  }

  @Test
  @DisplayName("할일카드 작성 - 실패(인증되지 않은 사용자)")
  void createTodoTestFailure() {
    // Given
    TodoRequestDto requestDto = new TodoRequestDto();
    requestDto.setTitle("title");
    requestDto.setContents("content");
    requestDto.setCompleted(false);

    User unauthenticatedUser = new User();
    requestDto.setAuthor(unauthenticatedUser.getUsername());

    // when & then
    assertThrows(IllegalArgumentException.class, () -> {
      given(todoRepository.save(any(Todo.class))).willThrow(
          new IllegalArgumentException("인증되지 않은 사용자"));

      todoService.createTodo(requestDto, unauthenticatedUser);
    });
  }

}