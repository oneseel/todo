package com.sparta.todo.comment.entity;

import com.sparta.todo.comment.dto.CommentRequestDto;
import com.sparta.todo.comment.dto.CommentUpdateRequestDto;
import com.sparta.todo.timestamped.Timestamped;
import com.sparta.todo.todo.entity.Todo;
import com.sparta.todo.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends Timestamped {

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
