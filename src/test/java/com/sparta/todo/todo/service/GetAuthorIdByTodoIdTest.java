package com.sparta.todo.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.sparta.todo.todo.entity.Todo;
import com.sparta.todo.todo.repository.TodoRepository;
import com.sparta.todo.user.entity.User;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAuthorIdByTodoIdTest {

  @Mock
  private TodoRepository todoRepository;

  @InjectMocks
  private TodoService todoService;

  @Test
  @DisplayName("할일카드 작성자 ID 조회 - 성공")
  void getAuthorIdByTodoIdSuccess() {

    // given
    Long todoId = 1L;
    User user = new User();
    user.setId(10L);

    Todo todo = new Todo();
    todo.setId(todoId);
    todo.setUser(user);

    given(todoRepository.findById(todoId)).willReturn(Optional.of(todo));

    // when
    Long authorId = todoService.getAuthorIdByTodoId(todoId);

    // then
    assertEquals(user.getId(), authorId);
  }

  @Test
  @DisplayName("할일카드 작성자 ID 조회 - 실패(존재하지 않는 할일카드)")
  void getAuthorIdByTodoIdFailure() {

    // given
    Long todoId = 2L;

    given(todoRepository.findById(todoId)).willReturn(Optional.empty());

    // when, then
    assertThrows(IllegalArgumentException.class, () -> todoService.getAuthorIdByTodoId(todoId));
  }

}