package com.sparta.todo.comment.dto;

import com.sparta.todo.comment.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {

  private Long id;
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
