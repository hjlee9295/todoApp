package com.example.todoApp.controller;

import com.example.todoApp.dto.TodoForm;
import com.example.todoApp.entity.Todo;
import com.example.todoApp.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j //로깅을 위한 annotation
public class todoController {

    @Autowired // springboot가 미리 생성해놓은 객체를 자동으로 연결
    private TodoRepository todoRepository;

    @GetMapping("/todo/newTodo")
    public String newTodo(Model model) {
        return "todo/newTodo";
    }

    @PostMapping("/todo/create")
    public String createTodo(TodoForm form){
//        System.out.println(form.toString());
        log.info(form.toString());


        // 1. DTO to Entity
        Todo todo = form.toEntity();
//        System.out.println(todo.toString());
        log.info(todo.toString());

        // 2. Repository에게 Entity를 DB안에 저장
        Todo saved = todoRepository.save(todo);
//        System.out.println(saved.toString());
        log.info(saved.toString());
        return "";
    }
}
