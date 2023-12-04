package com.sparta.todo.comment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.sparta.todo.comment.dto.CommentRequestDto;
import com.sparta.todo.comment.dto.CommentResponseDto;
import com.sparta.todo.comment.entity.Comment;
import com.sparta.todo.comment.repository.CommentRepository;
import com.sparta.todo.todo.entity.Todo;
import com.sparta.todo.todo.service.TodoService;
import com.sparta.todo.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateCommentTest {

  @Mock
  private CommentRepository commentRepository;

  @Mock
  private TodoService todoService;

  @InjectMocks
  private CommentService commentService;

  @Test
  @DisplayName("댓글 작성 - 성공")
  void createCommentTestSuccess() {

    // given
    CommentRequestDto requestDto = new CommentRequestDto();
    User loginUser = new User();
    Todo todo = new Todo();
    requestDto.setComment("comment");
    requestDto.setAuthor(loginUser.getUsername());
    todo.setId(1L);

    Comment comment = new Comment();
    comment.setId(1L);
    comment.setComment(requestDto.getComment());
    comment.setAuthor(requestDto.getAuthor());

    given(commentRepository.save(any(Comment.class))).willReturn(comment);

    // when
    CommentResponseDto saveComment = commentService.createComment(requestDto, todo.getId(), loginUser);

    // then
    assertNotNull(saveComment);
    assertNotNull(saveComment.getId());
    assertEquals(requestDto.getComment(), saveComment.getComment());
    assertEquals(requestDto.getAuthor(), saveComment.getAuthor());
  }

}