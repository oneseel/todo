package com.sparta.todo.service;

import com.sparta.todo.dto.TodoCompletedRequestDto;
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
        User user = getUser();

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
        getUser();

        Todo todo = getTodoCard(id);

        todo.update(requestDto);

        return new TodoResponseDto(todo);
    }

    // 선택한 할일카드 삭제
    public void deleteTodo(Long id) {
        getUser();

        Todo todo = getTodoCard(id);

        todoRepository.delete(todo);
    }

    @Transactional
    // 할일카드 완료 여부
    public TodoResponseDto completedTodo(Long id, TodoCompletedRequestDto requestDto) {
        getUser();

        Todo todo = getTodoCard(id);

        todo.completed(requestDto);

        return new TodoResponseDto(todo);
    }

    // 할일카드 존재 여부
    Todo getTodoCard(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("선택한 할일카드가 존재하지 않습니다.")
                );
    }

    // 회원 존재 여부
    User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 현재 로그인한 사용자의 정보를 가져오기
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("현재 로그인한 사용자를 찾을 수 없습니다."));
    }
}
