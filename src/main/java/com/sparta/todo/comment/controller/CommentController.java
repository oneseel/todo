package com.sparta.todo.comment.controller;

import com.sparta.todo.comment.dto.CommentRequestDto;
import com.sparta.todo.comment.dto.CommentResponseDto;
import com.sparta.todo.comment.dto.CommentUpdateRequestDto;
import com.sparta.todo.comment.service.CommentService;
import com.sparta.todo.security.UserDetailsImpl;
import com.sparta.todo.todo.controller.TodoController;
import com.sparta.todo.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo/{todoId}/comment")
public class CommentController {

  private final CommentService commentService;

  // 댓글 작성
  @PostMapping
  public ResponseEntity<CommentResponseDto> createComment(
      @PathVariable Long todoId,
      @RequestBody CommentRequestDto requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    User loginUser = userDetails.getUser();
    CommentResponseDto responseDto = commentService.createComment(requestDto, todoId, loginUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  // 댓글 수정
  @PatchMapping("/{commentId}")
  public ResponseEntity<CommentResponseDto> updateComment(
      @PathVariable Long todoId,
      @PathVariable Long commentId,
      @RequestBody CommentUpdateRequestDto requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    User user = userDetails.getUser();
    if (!haveModifyAuthorization(user, commentId)) {
      throw new IllegalArgumentException("수정, 삭제 권한이 없습니다.");
    }

    CommentResponseDto responseDto = commentService.updateComment(requestDto, todoId, commentId);
    return ResponseEntity.ok(responseDto);
  }

  // 댓글 삭제
  @DeleteMapping("/{commentId}")
  public ResponseEntity<Void> deleteComment(
      @PathVariable Long todoId,
      @PathVariable Long commentId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    User user = userDetails.getUser();
    if (!haveModifyAuthorization(user, commentId)) {
      throw new IllegalArgumentException("수정, 삭제 권한이 없습니다.");
    }

    commentService.deleteComment(todoId, commentId);
    return ResponseEntity.noContent().build();
  }

  public boolean haveModifyAuthorization(User loginUser, Long id) {
    Long authorId = commentService.getAuthorIdByTodoId(id);
    return loginUser.getId().equals(authorId);
  }
}
