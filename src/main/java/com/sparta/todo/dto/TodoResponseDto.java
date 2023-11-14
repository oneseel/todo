package com.sparta.todo.dto;

import com.sparta.todo.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoResponseDto {
    private Long id;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public TodoResponseDto(Todo saveTodo) {
        this.id = saveTodo.getId();
        this.title = saveTodo.getTitle();
        this.contents = saveTodo.getContents();
        this.createdAt = saveTodo.getCreatedAt();
        this.modifiedAt = saveTodo.getModifiedAt();
    }
}
