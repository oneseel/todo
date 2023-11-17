package com.sparta.todo.controller;

import com.sparta.todo.dto.CommentRequestDto;
import com.sparta.todo.dto.CommentResponseDto;
import com.sparta.todo.dto.CommentUpdateRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
