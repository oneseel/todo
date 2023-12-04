package com.sparta.todo.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.sparta.todo.todo.dto.TodoResponseDto;
import com.sparta.todo.todo.dto.TodoUpdateRequestDto;
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
class UpdateTodoTest {

  @Mock
  private TodoRepository todoRepository;

  @InjectMocks
  private TodoService todoService;

  @Test
  @DisplayName("할일카드 수정 - 성공")
  void updateTodoTestSuccess() {

    // given
    Long id = 1L;
    Todo todo = new Todo();
    User user = new User();
    todo.setId(1L);
    todo.setTitle("title");
    todo.setContents("content");
    todo.setAuthor(user.getUsername());
    todo.setCompleted(false);

    TodoUpdateRequestDto requestDto = new TodoUpdateRequestDto();
    requestDto.setTitle("title update");
    requestDto.setContents("content update");

    given(todoRepository.findById(id)).willReturn(Optional.of(todo));

    // when
    TodoResponseDto responseDto = todoService.updateTodo(id, requestDto);

    // then
    assertEquals("title update", responseDto.getTitle());
    assertEquals("content update", responseDto.getContents());
  }

  @Test
  @DisplayName("할일카드 수정 - 실패(존재하지 않는 할일카드)")
  void updateTodoTestFailure() {

    // given
    Long id = 2L;
    TodoUpdateRequestDto requestDto = new TodoUpdateRequestDto();
    requestDto.setTitle("title update");
    requestDto.setContents("content update");

    given(todoRepository.findById(id)).willReturn(Optional.empty());

    // when, then
    assertThrows(IllegalArgumentException.class, () -> todoService.updateTodo(id, requestDto));
  }

}