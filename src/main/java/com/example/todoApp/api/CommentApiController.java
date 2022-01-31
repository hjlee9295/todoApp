package com.example.todoApp.api;

import com.example.todoApp.dto.CommentDto;
import com.example.todoApp.entity.Comment;
import com.example.todoApp.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    //댓글 목록 조회
    @GetMapping("api/todos/{todoId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long todoId) {
        // 서비스에게 위임
        List<CommentDto> dtos = commentService.comments(todoId);
        log.info(todoId.toString());
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    //댓글 생성

    @PostMapping("api/todos/{todoId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long todoId,
                                             @RequestBody CommentDto dto){

        //서비스에게 위임
        CommentDto createdDto = commentService.create(todoId, dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    //댓글 수정

    @PatchMapping("api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto){

        //서비스에게 위임
        CommentDto createdDto = commentService.update(id, dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    //댓글 삭제
    @DeleteMapping("api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){

        //서비스에게 위임
        CommentDto deletedDto = commentService.delete(id);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }


}
