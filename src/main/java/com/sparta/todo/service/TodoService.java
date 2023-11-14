package com.sparta.todo.service;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public  TodoResponseDto createTodo(TodoRequestDto requestDto) {
        // Dto -> Entity
        Todo todo = new Todo(requestDto);

        Todo saveTodo = todoRepository.save(todo);

        return new TodoResponseDto(saveTodo);

    }
}
