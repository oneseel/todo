package com.sparta.todo.dto;

import com.sparta.todo.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

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
