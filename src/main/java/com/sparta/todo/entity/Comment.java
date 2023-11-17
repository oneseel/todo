package com.sparta.todo.entity;

import com.sparta.todo.dto.CommentRequestDto;
import com.sparta.todo.dto.CommentUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class Comment extends Timestamped{

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String comment;

    @Column(nullable = false, length = 10)
    String author;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;

    public Comment(CommentRequestDto requestDto, User user, Todo todo) {
        this.comment = requestDto.getComment();
        this.author = user.getUsername();
        this.user = user;
        this.todo = todo;
    }

    public void update(CommentUpdateRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }
}
