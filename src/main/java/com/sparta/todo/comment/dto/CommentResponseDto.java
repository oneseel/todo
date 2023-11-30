package com.sparta.todo.comment.dto;

import com.sparta.todo.comment.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentResponseDto {

  private long id;
  private String comment;
  private String author;
  private LocalDateTime createdAt;

  public CommentResponseDto(Comment saveComment) {
    this.id = saveComment.getId();
    this.comment = saveComment.getComment();
    this.author = saveComment.getAuthor();
    this.createdAt = saveComment.getCreatedAt();
  }
}
