package com.sparta.todo.todo.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
class DeleteTodoTest {

  @Mock
  private TodoRepository todoRepository;

  @InjectMocks
  private TodoService todoService;

  @Test
  @DisplayName("할일카드 삭제 - 성공")
  void deleteTodoTestSuccess() {

    // given
    Long id = 1L;
    Todo todo = new Todo();
    User user = new User();
    todo.setId(1L);
    todo.setTitle("title");
    todo.setContents("content");
    todo.setAuthor(user.getUsername());
    todo.setCompleted(false);

    given(todoRepository.findById(id)).willReturn(Optional.of(todo));

    // when
    todoService.deleteTodo(id);

    // then
    verify(todoRepository, times(1)).delete(todo);
  }

  @Test
  @DisplayName("할일카드 삭제 - 실패(존재하지 않는 할일카드)")
  void deleteTodoTestFailure() {

    // given
    Long id = 2L;

    given(todoRepository.findById(id)).willReturn(Optional.empty());

    // when, then
    assertThrows(IllegalArgumentException.class, () -> todoService.deleteTodo(id));
  }

}