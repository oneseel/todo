package com.sparta.todo.comment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import com.sparta.todo.comment.entity.Comment;
import com.sparta.todo.comment.repository.CommentRepository;
import com.sparta.todo.user.entity.User;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAuthorIdByCommentIdTest {

  @Mock
  private CommentRepository commentRepository;

  @InjectMocks
  private CommentService commentService;

  @Test
  @DisplayName("댓글 작성자 ID 조회 - 성공")
  void getAuthorIdByCommentIdTestSuccess() {

    // given
    Long commentId = 1L;
    User user = new User();
    user.setId(10L);

    Comment comment = new Comment();
    comment.setId(commentId);
    comment.setUser(user);

    given(commentRepository.findById(commentId)).willReturn(Optional.of(comment));

    // when
    Long authorId = commentService.getAuthorIdByCommentId(commentId);

    // then
    assertEquals(user.getId(), authorId);
  }

}