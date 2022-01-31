package com.example.todoApp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.security.Identity;

@Entity //DB가 해당 객체를 인식 가능
@AllArgsConstructor
@ToString
@NoArgsConstructor //디폴트 생성자를 추가
@Getter
public class Todo {

    @Id // 대표값을 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성 annotation
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Todo todo) {
        if (todo.title != null)
            this.title = todo.title;
        if (todo.content != null)
            this.content = todo.content;
    }
}
