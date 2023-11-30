package com.sparta.todo.todo.repository;

import com.sparta.todo.todo.entity.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

  List<Todo> findAllByOrderByCreatedAtDesc(); // 만든날짜 순으로 내림차순으로 정렬
}
