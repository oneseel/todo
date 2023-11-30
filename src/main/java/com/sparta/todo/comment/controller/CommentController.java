package com.sparta.todo.comment.controller;

import com.sparta.todo.comment.dto.CommentRequestDto;
import com.sparta.todo.comment.dto.CommentResponseDto;
import com.sparta.todo.comment.dto.CommentUpdateRequestDto;
import com.sparta.todo.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo/{todo_id}/comment")
public class CommentController {

  private final CommentService commentService;

  // 댓글 작성
  @PostMapping
  public ResponseEntity<CommentResponseDto> createComment(
      @PathVariable Long todo_id,
      @RequestBody CommentRequestDto requestDto) {
    CommentResponseDto responseDto = commentService.createComment(requestDto, todo_id);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  // 댓글 수정
  @PatchMapping("/{comment_id}")
  public ResponseEntity<CommentResponseDto> updateComment(
      @PathVariable Long todo_id,
      @PathVariable Long comment_id,
      @RequestBody CommentUpdateRequestDto requestDto) {
    CommentResponseDto responseDto = commentService.updateComment(requestDto, todo_id, comment_id);
    return ResponseEntity.ok(responseDto);
  }

  // 댓글 삭제
  @DeleteMapping("/{comment_id}")
  public ResponseEntity<Void> deleteComment(
      @PathVariable Long todo_id,
      @PathVariable Long comment_id) {
    commentService.deleteComment(todo_id, comment_id);
    return ResponseEntity.noContent().build();
  }
}
