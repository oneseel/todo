package com.sparta.todo.controller;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.dto.TodoUpdateRequestDto;
import com.sparta.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    // 할일카드 작성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 상태 번호 201
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.createTodo(requestDto);
    }

    // 선택한 할일카드 조회
    @GetMapping("/{id}")
    public TodoResponseDto getTodo(@PathVariable Long id) {
        return todoService.getTodo(id);
    }

    // 할일카드 목록 조회
    @GetMapping
    public List<TodoResponseDto> getTodoList() {
        return todoService.getTodoList();
    }

    // 선택한 할일카드 수정
    @PatchMapping("/{id}")
    public TodoResponseDto updateTodo(@PathVariable Long id, @RequestBody TodoUpdateRequestDto requestDto) {
        return todoService.updateTodo(id, requestDto);
    }
}
