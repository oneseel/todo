package com.sparta.todo.comment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import com.sparta.todo.comment.entity.Comment;
import com.sparta.todo.comment.repository.CommentRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetCommentTest {

  @Mock
  private CommentRepository commentRepository;

  @InjectMocks
  private CommentService commentService;

  @Test
  @DisplayName("댓글 조회 - 성공")
  void getCommentTest() {

    // given
    Long commentId = 1L;
    Comment comment = new Comment();
    comment.setId(commentId);

    given(commentRepository.findById(commentId)).willReturn(Optional.of(comment));

    // when
    Comment getComment = commentService.getComment(commentId);

    // then
    assertEquals(comment, getComment);
  }

}