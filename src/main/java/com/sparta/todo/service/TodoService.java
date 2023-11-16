package com.sparta.todo.service;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.dto.TodoUpdateRequestDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import com.sparta.todo.repository.TodoRepository;
import com.sparta.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    private final UserRepository userRepository;

    // 할일카드 작성
    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 현재 로그인한 사용자의 정보를 가져오기
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("현재 로그인한 사용자를 찾을 수 없습니다."));

        // Todo에 작성자 정보 추가
        Todo todo = new Todo(requestDto, user);

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

    // 선택한 할일카드 삭제
    public void deleteTodo(Long id) {
        Todo todo = getTodoCard(id);

        todoRepository.delete(todo);
    }

    private Todo getTodoCard(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("선택한 할일카드가 존재하지 않습니다.")
                );
        return todo;
    }
}
