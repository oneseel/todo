package com.sparta.todo.comment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

import com.sparta.todo.comment.dto.CommentResponseDto;
import com.sparta.todo.comment.dto.CommentUpdateRequestDto;
import com.sparta.todo.comment.entity.Comment;
import com.sparta.todo.comment.repository.CommentRepository;
import com.sparta.todo.todo.entity.Todo;
import com.sparta.todo.todo.service.TodoService;
import com.sparta.todo.user.entity.User;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateCommentTest {

  @Mock
  private CommentRepository commentRepository;

  @Mock
  private TodoService todoService;

  @InjectMocks
  private CommentService commentService;

  @Test
  @DisplayName("댓글 수정 - 성공")
  void updateCommentTestSuccess() {

    // given
    Comment comment = new Comment();
    User user = new User();
    Todo todo = new Todo();
    todo.setId(1L);
    comment.setId(1L);
    comment.setComment("comment");
    comment.setAuthor(user.getUsername());

    CommentUpdateRequestDto requestDto = new CommentUpdateRequestDto();
    requestDto.setComment("comment update");

    given(todoService.getTodoCard(1L)).willReturn(todo);
    given(commentRepository.findById(comment.getId())).willReturn(Optional.of(comment));

    // when
    CommentResponseDto responseDto = commentService.updateComment(
        requestDto, todo.getId(), comment.getId());

    // then
    assertNotNull(responseDto);
    assertEquals("comment update", responseDto.getComment());
  }

}