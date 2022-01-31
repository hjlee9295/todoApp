package com.example.todoApp.service;

import com.example.todoApp.dto.CommentDto;
import com.example.todoApp.entity.Comment;
import com.example.todoApp.entity.Todo;
import com.example.todoApp.repository.CommentRepository;
import com.example.todoApp.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TodoRepository todoRepository;

    public List<CommentDto> comments(Long todoId) {
        //조회: 댓글 목록
//        List<Comment> comments = commentRepository.findByTodoId(todoId);

        //변환: 엔티티 -> DTO
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//
//        for (int i = 0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }

        //반환
        return commentRepository.findByTodoId(todoId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long todoId, CommentDto dto) {
        //게시글 조회 및 예외 발생
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("failed to add comments. item doesn't exist!"));

        //댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, todo);

        //댓글 엔티티 디비 저장
        Comment created = commentRepository.save(comment);

        // DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }
    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        //댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("comments doesn't exist"));

        //댓글 수정
        target.patch(dto);
        // 디비로 갱신
        Comment updated = commentRepository.save(target);
        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        //댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("comment doesn't exist"));
        //db에서 댓글 삭제
        commentRepository.delete(target);
        //삭제 댓글 DTO로 반환
        return CommentDto.createCommentDto(target);
    }
}
