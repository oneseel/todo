package com.sparta.todo.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;

import com.sparta.todo.todo.dto.TodoResponseDto;
import com.sparta.todo.todo.entity.Todo;
import com.sparta.todo.todo.repository.TodoRepository;
import com.sparta.todo.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetTodoListTest {

  @Mock
  private TodoRepository todoRepository;

  @InjectMocks
  private TodoService todoService;

  @Test
  @DisplayName("전체 목록 조회 - 성공")
  void getTodoListTestSuccess() {
    // given
    Todo todo1 = createTodo(1L, "title_1", "content_1", false);
    Todo todo2 = createTodo(2L, "title_2", "content_2", true);

    TodoResponseDto responseDto1 = new TodoResponseDto(todo1);
    TodoResponseDto responseDto2 = new TodoResponseDto(todo2);

    List<Todo> todoList = List.of(todo1, todo2);
    List<TodoResponseDto> expectedTodoList = List.of(responseDto1, responseDto2);

    given(todoRepository.findAllByOrderByCreatedAtDesc()).willReturn(todoList);

    // when
    List<TodoResponseDto> actualTodoList = todoService.getTodoList();

    // then
    assertEquals(expectedTodoList.size(), actualTodoList.size());
    for (int i = 0; i < expectedTodoList.size(); i++) {
      TodoResponseDto expectedDto = expectedTodoList.get(i);
      TodoResponseDto actualDto = actualTodoList.get(i);

      assertEquals(expectedDto.getId(), actualDto.getId());
      assertEquals(expectedDto.getTitle(), actualDto.getTitle());
      assertEquals(expectedDto.getContents(), actualDto.getContents());
      assertEquals(expectedDto.getAuthor(), actualDto.getAuthor());
      assertEquals(expectedDto.isCompleted(), actualDto.isCompleted());
    }
  }

  private static Todo createTodo(
      Long id, String title, String content, boolean completed) {
    Todo todo = new Todo();
    User user = new User();

    todo.setId(id);
    todo.setTitle(title);
    todo.setContents(content);
    todo.setCompleted(completed);
    todo.setAuthor(user.getUsername());
    return todo;
  }

}