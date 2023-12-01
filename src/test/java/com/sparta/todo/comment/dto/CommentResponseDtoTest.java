package com.sparta.todo.comment.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.sparta.todo.comment.entity.Comment;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class CommentResponseDtoTest {

  @Test
  void commentResponseDtoTest() {

    // given
    Comment comment = new Comment();
    comment.setId(1L);
    comment.setComment("comment");
    comment.setAuthor("author");
    comment.setCreatedAt(LocalDateTime.now());

    // when
    CommentResponseDto responseDto = new CommentResponseDto(comment);

    // then
    assertEquals(1L, responseDto.getId());
    assertEquals("comment", responseDto.getComment());
    assertEquals("author", responseDto.getAuthor());
    assertNotNull(responseDto.getCreatedAt());
  }
}