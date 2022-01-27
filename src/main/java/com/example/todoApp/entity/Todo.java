package com.example.todoApp.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity //DB가 해당 객체를 인식 가능
@AllArgsConstructor
@ToString
public class Todo {

    @Id // 대표값을 지정
    @GeneratedValue // 자동 생성 annotation
    private Long id;

    @Column
    private String title;

    @Column
    private String content;
}