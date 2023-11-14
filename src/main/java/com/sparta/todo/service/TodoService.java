package com.sparta.todo.service;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.dto.TodoUpdateRequestDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 할일카드 작성
    public  TodoResponseDto createTodo(TodoRequestDto requestDto) {
        // Dto -> Entity
        Todo todo = new Todo(requestDto);

        Todo saveTodo = todoRepository.save(todo);

        return new TodoResponseDto(saveTodo);
    }

    // 선택한 할일카드 조회
    public TodoResponseDto getTodo(Long id) {
        Todo todo = getTodoCard(id);

        return new TodoResponseDto(todo);
    }

    // 할일카드 목록 조회
    public List<TodoResponseDto> getTodoList() {
        return todoRepository.findAllByOrderByCreatedAtDesc()
                .stream().map(TodoResponseDto::new).toList();
    }

    // 선택한 할일카드 수정
    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoUpdateRequestDto requestDto) {
        Todo todo = getTodoCard(id);

        todo.update(requestDto);

        return new TodoResponseDto(todo);
    }

    private Todo getTodoCard(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("선택한 할일카드가 존재하지 않습니다.")
                );
        return todo;
    }
}
