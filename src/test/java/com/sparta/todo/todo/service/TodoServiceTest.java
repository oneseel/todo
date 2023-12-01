package com.sparta.todo.todo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
class TodoServiceTest {

  @Mock
  private TodoRepository todoRepository;

  @InjectMocks
  private TodoService todoService;

  @Test
  @DisplayName("할일카드 작성 - 성공")
  void Test1() {
    // given
    TodoRequestDto todoRequestDto = new TodoRequestDto();
    todoRequestDto.setTitle("할일카드 제목");
    todoRequestDto.setContents("할일카드 내용");
    todoRequestDto.setAuthor("작성자");
    todoRequestDto.setCompleted(false);

    User loginUser = new User();

    Todo todo = new Todo();
    todo.setId(1L);
    todo.setTitle(todoRequestDto.getTitle());
    todo.setContents(todoRequestDto.getContents());
    todo.setAuthor(todoRequestDto.getAuthor());
    todo.setCompleted(todoRequestDto.isCompleted());

    given(todoRepository.save(any(Todo.class))).willReturn(todo);

    // when
    TodoResponseDto saveTodo = todoService.createTodo(todoRequestDto, loginUser);

    // then
    assertNotNull(saveTodo);
    assertNotNull(saveTodo.getId());
    assertEquals(todoRequestDto.getTitle(), saveTodo.getTitle());
    assertEquals(todoRequestDto.getContents(), saveTodo.getContents());
    assertEquals(todoRequestDto.getAuthor(), saveTodo.getAuthor());
    assertEquals(todoRequestDto.isCompleted(), saveTodo.isCompleted());
  }

  @Test
  @DisplayName("할일카드 작성 - 실패(인증되지 않은 사용자)")
  void Test2() {
    // Given
    TodoRequestDto todoRequestDto = new TodoRequestDto();
    todoRequestDto.setTitle("할일카드 제목");
    todoRequestDto.setContents("할일카드 내용");
    todoRequestDto.setAuthor("작성자");
    todoRequestDto.setCompleted(false);

    User unauthenticatedUser = new User();

    // when & then
    assertThrows(IllegalArgumentException.class, () -> {
      given(todoRepository.save(any(Todo.class))).willThrow(
          new IllegalArgumentException("인증되지 않은 사용자"));
      todoService.createTodo(todoRequestDto, unauthenticatedUser);
    });
  }

}