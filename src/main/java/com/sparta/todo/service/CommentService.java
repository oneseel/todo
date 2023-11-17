package com.sparta.todo.service;

import com.sparta.todo.dto.CommentRequestDto;
import com.sparta.todo.dto.CommentResponseDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.entity.Comment;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import com.sparta.todo.repository.CommentRepository;
import com.sparta.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final TodoService todoService;

    // 댓글 작성
    public CommentResponseDto createComment(CommentRequestDto requestDto, Long todo_id) {
        User user = todoService.getUser(); // 회원 확인
        Todo todo = todoService.getTodoCard(todo_id); // 할일카드 확인

        Comment comment = new Comment(requestDto, user, todo);

        Comment saveComment = commentRepository.save(comment);

        return new CommentResponseDto(saveComment);
    }
}
