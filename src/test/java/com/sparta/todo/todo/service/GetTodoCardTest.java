package com.sparta.todo.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.sparta.todo.todo.entity.Todo;
import com.sparta.todo.todo.repository.TodoRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetTodoCardTest {

  @Mock
  private TodoRepository todoRepository;

  @InjectMocks
  private TodoService todoService;

  @Test
  @DisplayName("할일카드 조회 - 성공")
  void getTodoCardSuccess() {

    // given
    Long todoId = 1L;
    Todo todo = new Todo();
    todo.setId(todoId);

    given(todoRepository.findById(todoId)).willReturn(Optional.of(todo));

    // when
    Todo getTodo = todoService.getTodoCard(todoId);

    // then
    assertEquals(todo, getTodo);
  }

  @Test
  @DisplayName("할일카드 조회 - 실패(존재하지 않는 할일카드)")
  void getTodoCardFailure() {

    // given
    Long todoId = 2L;

    given(todoRepository.findById(todoId)).willReturn(Optional.empty());

    // when, then
    assertThrows(IllegalArgumentException.class, () -> todoService.getTodoCard(todoId));
  }

}