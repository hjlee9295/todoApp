package com.example.todoApp.dto;

import com.example.todoApp.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString

public class CommentDto {

    private Long id;
    @JsonProperty("todo_id")
    private Long todoId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getTodo().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
