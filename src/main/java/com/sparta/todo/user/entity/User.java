package com.sparta.todo.user.entity;

import com.sparta.todo.comment.entity.Comment;
import com.sparta.todo.timestamped.Timestamped;
import com.sparta.todo.todo.entity.Todo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends Timestamped {

  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Todo> todos;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Comment> comments;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
