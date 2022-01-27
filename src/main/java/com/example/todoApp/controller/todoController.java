package com.example.todoApp.controller;

import com.example.todoApp.dto.TodoForm;
import com.example.todoApp.entity.Todo;
import com.example.todoApp.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j //로깅을 위한 annotation
public class todoController {

    @Autowired // springboot가 미리 생성해놓은 객체를 자동으로 연결
    private TodoRepository todoRepository;

    @GetMapping("/todos/newTodo")
    public String newTodo(Model model) {
        return "todos/newTodo";
    }

    @PostMapping("/todos/create")
    public String createTodo(TodoForm form) {
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
        return "redirect:/todos/" + saved.getId();
    }

    @GetMapping("todos/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        //1. ID로 데이터 가져옴
        Todo todoEntity = todoRepository.findById(id).orElse(null );

        //2. 가져온 데이터 모델에 등록
        model.addAttribute("todo", todoEntity);

        //3. 보여줄 페이지 설정
        return "todos/show";
    }
    @GetMapping("/todos")
    public String index(Model model){

        //1. 모든 데이터 가져오기
        List<Todo> todoEntityList = todoRepository.findAll();
        //2. 가져온 데이터를 뷰로 전달
        model.addAttribute("todoList", todoEntityList);
        //3. 뷰 페이지 설정
        return "todos/index";
    }

    @GetMapping("/todos/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정 데이터 가져오기
        Todo todoEntity = todoRepository.findById(id).orElse(null);

        //모델에 데이터 등록
        model.addAttribute("todo",todoEntity);

        //뷰페이지 설정
        return "todos/edit";
    }

    @PostMapping("todos/update")
    public String update(TodoForm form){
        //1. dto를 entity로 변환
        Todo todoEntity = form.toEntity();
        log.info(todoEntity.toString());

        //2. entity를 DB로 저장

        //2-1. db에서 기존 데이터 가져오기
        Todo target = todoRepository.findById(todoEntity.getId()).orElse(null);

        //2-2. 기존 데이터에 값을 갱신한다
        if (target != null){
            todoRepository.save(todoEntity);
        }

        //3. 수정결과 페이지로 리다이렉트
        return "redirect:/todos/" + todoEntity.getId();
    }

    @GetMapping("/todos/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("delte request");

        //1. 삭제 대상을 가져오기
        Todo target = todoRepository.findById(id).orElse(null);

        //2. 삭제하기
        if (target != null){
            todoRepository.delete(target);
            rttr.addFlashAttribute("msg", "item deleted");
        }

        //3. 결과 페이지로 리다이렉트
        return "redirect:/todos";
    }
}
