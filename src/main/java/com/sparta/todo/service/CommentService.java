package com.sparta.todo.service;

import com.sparta.todo.dto.CommentRequestDto;
import com.sparta.todo.dto.CommentResponseDto;
import com.sparta.todo.dto.CommentUpdateRequestDto;
import com.sparta.todo.entity.Comment;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import com.sparta.todo.repository.CommentRepository;
import com.sparta.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final TodoService todoService;

    // 댓글 작성
    public CommentResponseDto createComment(CommentRequestDto requestDto, Long todo_id) {
        User user = todoService.getUser(); // 회원 확인

        Todo todo = todoService.getTodoCard(todo_id); // 할일카드 확인

        Comment comment = new Comment(requestDto, user, todo);

        Comment saveComment = commentRepository.save(comment);

        return new CommentResponseDto(saveComment);
    }

    // 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(CommentUpdateRequestDto requestDto, Long todo_id, Long comment_id) {
        todoService.getUser(); // 회원 확인

        Todo todo = todoService.getTodoCard(todo_id); // 할일카드 확인

        Comment comment = getComment(comment_id); // 댓글 확인

        comment.update(requestDto);

        return new CommentResponseDto(comment);
    }

    // 댓글 존재 여부
    Comment getComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("선택한 할일카드가 존재하지 않습니다.")
                );
    }

    // 댓글 삭제 여부
    public void deleteComment(Long todo_id, Long comment_id) {
        todoService.getUser(); // 회원 확인

        Todo todo = todoService.getTodoCard(todo_id); // 할일카드 확인

        Comment comment = getComment(comment_id); // 댓글 확인

        commentRepository.delete(comment);
    }
}
