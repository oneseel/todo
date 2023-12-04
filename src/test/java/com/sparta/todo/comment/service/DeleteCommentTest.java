package com.sparta.todo.comment.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
class DeleteCommentTest {

  @Mock
  private CommentRepository commentRepository;

  @Mock
  private TodoService todoService;

  @InjectMocks
  private CommentService commentService;

  @Test
  @DisplayName("댓글 삭제 - 성공")
  void deleteCommentTestSuccess() {

    // given
    Comment comment = new Comment();
    User user = new User();
    Todo todo = new Todo();
    todo.setId(1L);
    comment.setId(1L);
    comment.setComment("comment");
    comment.setAuthor(user.getUsername());

    given(todoService.getTodoCard(todo.getId())).willReturn(todo);
    given(commentRepository.findById(comment.getId())).willReturn(Optional.of(comment));

    // when
    commentService.deleteComment(todo.getId(), comment.getId());

    // then
    verify(commentRepository, times(1)).delete(comment);
  }

}