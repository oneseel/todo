package com.sparta.todo.controller;

import com.sparta.todo.dto.TodoCompletedRequestDto;
import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.dto.TodoUpdateRequestDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    // 할일카드 작성
    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(
            @RequestBody TodoRequestDto requestDto) {
        TodoResponseDto responseDto = todoService.createTodo(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 선택한 할일카드 조회
    @GetMapping("/{user_id}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable Long id) {
        TodoResponseDto responseDto = todoService.getTodo(id);
        return ResponseEntity.ok(responseDto);
    }

    // 할일카드 목록 조회
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getTodoList() {
        List<TodoResponseDto> responseDto = todoService.getTodoList();
        return ResponseEntity.ok(responseDto);
    }

    // 선택한 할일카드 수정
    @PatchMapping("/{user_id}")
    public ResponseEntity<TodoResponseDto> updateTodo(
            @PathVariable Long user_id,
            @RequestBody TodoUpdateRequestDto requestDto) {
        TodoResponseDto responseDto = todoService.updateTodo(user_id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 선택한 할일카드 삭제
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long user_id) {
        todoService.deleteTodo(user_id);
        return ResponseEntity.noContent().build();
    }

    // 할일카드 완료 여부
    @PatchMapping("/completed/{user_id}")
    public ResponseEntity<TodoResponseDto> completedTodo(
            @PathVariable Long user_id,
            @RequestBody TodoCompletedRequestDto requestDto) {
        TodoResponseDto responseDto = todoService.completedTodo(user_id, requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
