package com.example.todoApp.repository;

import com.example.todoApp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //특정 게시글의 모든 댓글 조회
    @Query(value =
            "Select * from comment where todo_id = :todoId", nativeQuery = true)
    List<Comment> findByTodoId(Long todoId);

    // 특정 닉네임의 모든 댓글 조회
    // xml로 작성 META-INF orm.xml
    List<Comment> findByNickname(String nickname);
}
