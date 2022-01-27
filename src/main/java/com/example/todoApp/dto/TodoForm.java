package com.example.todoApp.dto;

import com.example.todoApp.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString

public class TodoForm {

    private Long id;
    private String title;
    private String content;

    public Todo toEntity() {
        return new Todo(id, title, content);
    }
}
