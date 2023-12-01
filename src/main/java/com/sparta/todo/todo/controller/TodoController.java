package com.sparta.todo.todo.controller;

import com.sparta.todo.security.UserDetailsImpl;
import com.sparta.todo.todo.dto.TodoCompletedRequestDto;
import com.sparta.todo.todo.dto.TodoRequestDto;
import com.sparta.todo.todo.dto.TodoResponseDto;
import com.sparta.todo.todo.dto.TodoUpdateRequestDto;
import com.sparta.todo.todo.service.TodoService;
import com.sparta.todo.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

  private final TodoService todoService;

  // 할일카드 작성
  @PostMapping
  public ResponseEntity<TodoResponseDto> createTodo(
      @RequestBody TodoRequestDto requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    User loginUser = userDetails.getUser();
    TodoResponseDto responseDto = todoService.createTodo(requestDto, loginUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  // 선택한 할일카드 조회
  @GetMapping("/{todoId}")
  public ResponseEntity<TodoResponseDto> getTodo(@PathVariable Long todoId) {
    TodoResponseDto responseDto = todoService.getTodo(todoId);
    return ResponseEntity.ok(responseDto);
  }

  // 할일카드 목록 조회
  @GetMapping
  public ResponseEntity<List<TodoResponseDto>> getTodoList() {
    List<TodoResponseDto> responseDto = todoService.getTodoList();
    return ResponseEntity.ok(responseDto);
  }

  // 선택한 할일카드 수정
  @PatchMapping("/{todoId}")
  public ResponseEntity<TodoResponseDto> updateTodo(
      @PathVariable Long todoId,
      @RequestBody TodoUpdateRequestDto requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    User user = userDetails.getUser();
    if (!haveModifyAuthorization(user, todoId)) {
      throw new IllegalArgumentException("수정, 삭제 권한이 없습니다.");
    }

    TodoResponseDto responseDto = todoService.updateTodo(todoId, requestDto);
    return ResponseEntity.ok(responseDto);
  }

  // 선택한 할일카드 삭제
  @DeleteMapping("/{todoId}")
  public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    User user = userDetails.getUser();
    if (!haveModifyAuthorization(user, todoId)) {
      throw new IllegalArgumentException("수정, 삭제 권한이 없습니다.");
    }

    todoService.deleteTodo(todoId);
    return ResponseEntity.noContent().build();
  }

  // 할일카드 완료 여부
  @PatchMapping("/completed/{todoId}")
  public ResponseEntity<TodoResponseDto> completedTodo(
      @PathVariable Long todoId,
      @RequestBody TodoCompletedRequestDto requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    User user = userDetails.getUser();
    if (!haveModifyAuthorization(user, todoId)) {
      throw new IllegalArgumentException("수정, 삭제 권한이 없습니다.");
    }
    TodoResponseDto responseDto = todoService.completedTodo(todoId, requestDto);
    return ResponseEntity.ok(responseDto);
  }

  public boolean haveModifyAuthorization(User loginUser, Long id) {
    Long authorId = todoService.getAuthorIdByTodoId(id);
    return loginUser.getId().equals(authorId);
  }
}
