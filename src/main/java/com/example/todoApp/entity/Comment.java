package com.example.todoApp.entity;


import com.sun.javafx.geom.transform.Identity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

import com.example.todoApp.dto.CommentDto;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //해당 댓글 엔티티 여러개가, 하나의 아티클에 연관된다!
    @JoinColumn(name="todo_id") // "todo_id" 컬럼에 Todo의 대표값을 저장!
    private Todo todo;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Todo todo) {
        //예외 처리
        if (dto.getId() != null)
            throw new IllegalArgumentException("Comments failed. New comments shouldn't have id");

        if (dto.getTodoId() != todo.getId())
            throw new IllegalArgumentException("Comments failed. Items id is not correct");

        //엔티티 생성 처리 및 반환

        return new Comment(
                dto.getId(),
                todo,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        //예외 발생
        if (this.id != dto.getId())
            throw new IllegalArgumentException("wrong id for comments");
        //객체 갱신
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if (dto.getBody() != null)
            this.body = dto.getBody();
    }
}
