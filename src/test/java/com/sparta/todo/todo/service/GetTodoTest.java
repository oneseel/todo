package com.sparta.todo.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.sparta.todo.todo.dto.TodoResponseDto;
import com.sparta.todo.todo.entity.Todo;
import com.sparta.todo.todo.repository.TodoRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

@ExtendWith(MockitoExtension.class)
class GetTodoTest {

  @Mock
  private TodoRepository todoRepository;

  @InjectMocks
  private TodoService todoService;

  @Test
  @DisplayName("할일카드 단건 조회 - 성공")
  void getTodoTestSuccess() {

    // given
    Todo todo = new Todo();
    todo.setId(1L);
    todo.setTitle("title");
    todo.setContents("content");
    todo.setCompleted(false);

    given(todoRepository.findById(todo.getId())).willReturn(Optional.of(todo));

    // when
    TodoResponseDto responseDto = todoService.getTodo(todo.getId());

    // then
    assertEquals(todo.getId(), responseDto.getId());
    assertEquals(todo.getTitle(), responseDto.getTitle());
    assertEquals(todo.getContents(), responseDto.getContents());
    assertEquals(todo.isCompleted(), responseDto.isCompleted());
  }

  @Test
  @DisplayName("할일카드 단건 조회 - 실패(선택한 할일카드 없음)")
  void getTodoTestFailure() {

    // given
    Long id = 2L; // 존재하지 않는 할일카드

    given(todoRepository.findById(id)).willReturn(Optional.empty());

    // when, then
    assertThrows(IllegalArgumentException.class, () -> {
      todoService.getTodo(id);
    });
  }
}