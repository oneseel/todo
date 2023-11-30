package com.sparta.todo.comment.service;

import com.sparta.todo.comment.dto.CommentRequestDto;
import com.sparta.todo.comment.dto.CommentResponseDto;
import com.sparta.todo.comment.dto.CommentUpdateRequestDto;
import com.sparta.todo.comment.entity.Comment;
import com.sparta.todo.comment.repository.CommentRepository;
import com.sparta.todo.todo.entity.Todo;
import com.sparta.todo.todo.service.TodoService;
import com.sparta.todo.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;

  private final TodoService todoService;

  // 댓글 작성
  public CommentResponseDto createComment(CommentRequestDto requestDto, Long todo_id) {
    User user = todoService.getUser(); // 회원 확인

    Todo todo = todoService.getTodoCard(todo_id); // 할일카드 확인

    Comment comment = new Comment(requestDto, user, todo);

    Comment saveComment = commentRepository.save(comment);

    return new CommentResponseDto(saveComment);
  }

  // 댓글 수정
  @Transactional
  public CommentResponseDto updateComment(CommentUpdateRequestDto requestDto, Long todo_id,
      Long comment_id) {
    Comment comment = validateCommentAuthorization(todo_id, comment_id);

    comment.update(requestDto);

    return new CommentResponseDto(comment);
  }

  // 댓글 삭제 여부
  public void deleteComment(Long todo_id, Long comment_id) {
    Comment comment = validateCommentAuthorization(todo_id, comment_id);

    commentRepository.delete(comment);
  }

  // 댓글 존재 여부
  Comment getComment(Long id) {
    return commentRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("선택한 할일카드가 존재하지 않습니다.")
        );
  }

  // 권한 확인
  private Comment validateCommentAuthorization(Long todo_id, Long comment_id) {
    User user = todoService.getUser(); // 회원 확인

    todoService.getTodoCard(todo_id); // 할일카드 확인

    Comment comment = getComment(comment_id); // 댓글 확인

    // 현재 로그인한 회원과 댓글의 작성자가 일치하는지 확인
    if (!comment.getUser().equals(user)) {
      throw new IllegalArgumentException("권한이 없습니다.");
    }
    return comment;
  }
}
