package com.sparta.todo.todo.service;

import com.sparta.todo.todo.dto.TodoCompletedRequestDto;
import com.sparta.todo.todo.dto.TodoRequestDto;
import com.sparta.todo.todo.dto.TodoResponseDto;
import com.sparta.todo.todo.dto.TodoUpdateRequestDto;
import com.sparta.todo.todo.entity.Todo;
import com.sparta.todo.todo.repository.TodoRepository;
import com.sparta.todo.user.entity.User;
import com.sparta.todo.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

  private final TodoRepository todoRepository;

  // 할일카드 작성
  @Transactional
  public TodoResponseDto createTodo(TodoRequestDto requestDto, User loginUser) {
    Todo todo = new Todo(requestDto, loginUser);
    Todo saveTodo = todoRepository.save(todo);
    return new TodoResponseDto(saveTodo);
  }

  // 선택한 할일카드 조회
  public TodoResponseDto getTodo(Long todoId) {
    Todo todo = getTodoCard(todoId);
    return new TodoResponseDto(todo);
  }

  // 할일카드 목록 조회
  public List<TodoResponseDto> getTodoList() {
    return todoRepository.findAllByOrderByCreatedAtDesc()
        .stream().map(TodoResponseDto::new).toList();
  }

  // 선택한 할일카드 수정
  @Transactional
  public TodoResponseDto updateTodo(Long todoId, TodoUpdateRequestDto requestDto) {
    Todo todo = getTodoCard(todoId);
    todo.update(requestDto);
    return new TodoResponseDto(todo);
  }

  // 선택한 할일카드 삭제
  public void deleteTodo(Long todoId) {
    Todo todo = getTodoCard(todoId);
    todoRepository.delete(todo);
  }

  @Transactional
  // 할일카드 완료 여부
  public TodoResponseDto completedTodo(Long todoId, TodoCompletedRequestDto requestDto) {
    Todo todo = getTodoCard(todoId);
    todo.completed(requestDto);
    return new TodoResponseDto(todo);
  }

  public Long getAuthorIdByTodoId(Long todoId) {
    Todo todo = todoRepository.findById(todoId)
        .orElseThrow(() -> new IllegalArgumentException("해당 할일카드를 찾을 수 없습니다."));
    return todo.getUser().getId();
  }

  public Todo getTodoCard(Long todoId) {
    return todoRepository.findById(todoId)
        .orElseThrow(() -> new IllegalArgumentException("해당 할일카드를 찾을 수 없습니다."));
  }
}
