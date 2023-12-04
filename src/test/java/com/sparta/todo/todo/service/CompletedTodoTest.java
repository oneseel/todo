package com.sparta.todo.todo.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import com.sparta.todo.todo.dto.TodoCompletedRequestDto;
import com.sparta.todo.todo.dto.TodoResponseDto;
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
class CompletedTodoTest {

  @Mock
  private TodoRepository todoRepository;

  @InjectMocks
  private TodoService todoService;

  @Test
  @DisplayName("할일카드 완료여부 - 성공")
  void completedTodoTestSuccess() {

    // given
    Long id = 1L;
    Todo todo = new Todo();
    User user = new User();
    todo.setId(1L);
    todo.setTitle("title");
    todo.setContents("content");
    todo.setAuthor(user.getUsername());
    todo.setCompleted(false);

    TodoCompletedRequestDto requestDto = new TodoCompletedRequestDto();
    requestDto.setCompleted(true);

    given(todoRepository.findById(id)).willReturn(Optional.of(todo));

    // when
    TodoResponseDto responseDto = todoService.completedTodo(id, requestDto);

    // then
    assertTrue(responseDto.isCompleted());
  }

  @Test
  @DisplayName("할일카드 완료여부 - 실패(존재하지 않는 할일카드)")
  void completedTodoTestFailure() {

    // given
    Long id = 2L;
    TodoCompletedRequestDto requestDto = new TodoCompletedRequestDto();
    requestDto.setCompleted(true);

    given(todoRepository.findById(id)).willReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () -> todoService.completedTodo(id, requestDto));
  }
}