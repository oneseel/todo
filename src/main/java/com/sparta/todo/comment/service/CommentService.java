package com.sparta.todo.comment.service;

import com.sparta.todo.comment.dto.CommentRequestDto;
import com.sparta.todo.comment.dto.CommentResponseDto;
import com.sparta.todo.comment.dto.CommentUpdateRequestDto;
import com.sparta.todo.comment.entity.Comment;
import com.sparta.todo.comment.repository.CommentRepository;
import com.sparta.todo.todo.entity.Todo;
import com.sparta.todo.todo.service.TodoService;
import com.sparta.todo.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;

  private final TodoService todoService;

  // 댓글 작성
  @Transactional
  public CommentResponseDto createComment(CommentRequestDto requestDto, Long todoId,
      User loginUser) {
    Todo todo = todoService.getTodoCard(todoId);
    Comment comment = new Comment(requestDto, loginUser, todo);
    Comment saveComment = commentRepository.save(comment);
    return new CommentResponseDto(saveComment);
  }

  // 댓글 수정
  @Transactional
  public CommentResponseDto updateComment(CommentUpdateRequestDto requestDto, Long todoId, Long commentId) {
    todoService.getTodoCard(todoId);
    Comment comment = getComment(commentId);
    comment.update(requestDto);
    return new CommentResponseDto(comment);
  }

  // 댓글 삭제
  public void deleteComment(Long todoId, Long commentId) {
    todoService.getTodoCard(todoId);
    Comment comment = getComment(commentId);
    commentRepository.delete(comment);
  }

  public Long getAuthorIdByCommentId(Long commentId) {
    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다."));
    return comment.getUser().getId();
  }

  public Comment getComment(Long commentId) {
    return commentRepository.findById(commentId)
        .orElseThrow(() -> new IllegalArgumentException("선택한 댓글이 존재하지 않습니다."));
  }
}
