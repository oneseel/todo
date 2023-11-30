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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

  private final TodoRepository todoRepository;

  private final UserRepository userRepository;

  // 할일카드 작성
  public TodoResponseDto createTodo(TodoRequestDto requestDto) {
    User user = getUser(); // 회원 확인

    Todo todo = new Todo(requestDto, user);

    Todo saveTodo = todoRepository.save(todo);

    return new TodoResponseDto(saveTodo);
  }

  // 선택한 할일카드 조회
  public TodoResponseDto getTodo(Long todo_id) {
    Todo todo = getTodoCard(todo_id); // 할일카드 확인

    return new TodoResponseDto(todo);
  }

  // 할일카드 목록 조회
  public List<TodoResponseDto> getTodoList() {
    return todoRepository.findAllByOrderByCreatedAtDesc()
        .stream().map(TodoResponseDto::new).toList();
  }

  // 선택한 할일카드 수정
  @Transactional
  public TodoResponseDto updateTodo(Long todo_id, TodoUpdateRequestDto requestDto) {
    Todo todo = validateTodoAuthorization(todo_id);

    todo.update(requestDto);

    return new TodoResponseDto(todo);
  }

  // 선택한 할일카드 삭제
  public void deleteTodo(Long todo_id) {
    Todo todo = validateTodoAuthorization(todo_id);

    todoRepository.delete(todo);
  }

  @Transactional
  // 할일카드 완료 여부
  public TodoResponseDto completedTodo(Long todo_id, TodoCompletedRequestDto requestDto) {
    Todo todo = validateTodoAuthorization(todo_id);

    todo.completed(requestDto);

    return new TodoResponseDto(todo);
  }

  // 할일카드 존재 여부
  public Todo getTodoCard(Long id) {
    return todoRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("선택한 할일카드가 존재하지 않습니다.")
        );
  }

  // 회원 존재 여부
  public User getUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    // 현재 로그인한 사용자의 정보를 가져오기
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new IllegalArgumentException("현재 로그인한 사용자를 찾을 수 없습니다."));
  }

  // 권한 확인
  private Todo validateTodoAuthorization(Long todo_id) {
    User user = getUser(); // 회원 확인

    Todo todo = getTodoCard(todo_id); // 할일카드 확인

    // 현재 로그인한 회원과 할일카드의 작성자가 일치하는지 확인
    if (!todo.getUser().equals(user)) {
      throw new IllegalArgumentException("권한이 없습니다.");
    }
    return todo;
  }
}
