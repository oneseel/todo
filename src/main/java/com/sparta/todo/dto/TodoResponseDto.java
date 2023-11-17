package com.sparta.todo.dto;

import com.sparta.todo.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoResponseDto {
    private Long id;
    private String title;
    private String author;
    private String contents;
    private LocalDateTime createdAt;
    private boolean completed;

    public TodoResponseDto(Todo saveTodo) {
        this.id = saveTodo.getId();
        this.title = saveTodo.getTitle();
        this.author = saveTodo.getAuthor();
        this.contents = saveTodo.getContents();
        this.createdAt = saveTodo.getCreatedAt();
        this.completed = saveTodo.isCompleted();
    }
}
