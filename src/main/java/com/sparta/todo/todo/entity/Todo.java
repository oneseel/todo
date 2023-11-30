package com.sparta.todo.todo.entity;

import com.sparta.todo.comment.entity.Comment;
import com.sparta.todo.timestamped.Timestamped;
import com.sparta.todo.todo.dto.TodoCompletedRequestDto;
import com.sparta.todo.todo.dto.TodoRequestDto;
import com.sparta.todo.todo.dto.TodoUpdateRequestDto;
import com.sparta.todo.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "todo")
public class Todo extends Timestamped {

  @Id
  @Column(name = "todo_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(nullable = false, length = 1000)
  String contents;

  @Column(nullable = false)
  String author;

  @Column(nullable = false)
  boolean completed;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
  private List<Comment> comments;

  public Todo(TodoRequestDto requestDto, User user) {
    this.title = requestDto.getTitle();
    this.contents = requestDto.getContents();
    this.author = user.getUsername();
    this.user = user;
    this.completed = isCompleted();
  }

  public void update(TodoUpdateRequestDto requestDto) {
    this.title = requestDto.getTitle();
    this.contents = requestDto.getContents();
  }

  public void completed(TodoCompletedRequestDto requestDto) {
    this.completed = requestDto.isCompleted();
  }
}
