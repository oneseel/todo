package com.sparta.todo.comment.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.sparta.todo.comment.dto.CommentRequestDto;
import com.sparta.todo.comment.dto.CommentUpdateRequestDto;
import com.sparta.todo.todo.entity.Todo;
import com.sparta.todo.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommentTest {

  @Test
  @DisplayName("Comment 엔터티 생성 - 성공")
  void createCommentEntity() {
    // given
    CommentRequestDto requestDto = new CommentRequestDto();
    requestDto.setComment("comment");

    User user = new User();
    user.setUsername("author");

    Todo todo = new Todo();

    // when
    Comment comment = new Comment(requestDto, user, todo);

    // then
    assertNotNull(comment);
    assertEquals(requestDto.getComment(), comment.getComment());
    assertEquals(user.getUsername(), comment.getAuthor());
    assertEquals(user, comment.getUser());
    assertEquals(todo, comment.getTodo());
  }

  @Test
  @DisplayName("Todo 엔터티 update")
  void updateCommentEntity() {

    // given
    CommentRequestDto requestDto = new CommentRequestDto();
    requestDto.setComment("comment");

    User user = new User();
    user.setUsername("author");

    Todo todo = new Todo();

    Comment comment = new Comment(requestDto, user, todo);

    CommentUpdateRequestDto updateRequestDto = new CommentUpdateRequestDto();
    updateRequestDto.setComment("comment update");

    // when
    comment.update(updateRequestDto);

    // then
    assertEquals(updateRequestDto.getComment(), comment.getComment());
  }

}