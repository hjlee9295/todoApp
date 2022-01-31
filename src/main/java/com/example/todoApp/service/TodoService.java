package com.example.todoApp.service;

import com.example.todoApp.dto.TodoForm;
import com.example.todoApp.entity.Todo;
import com.example.todoApp.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service // 서비스 선언 (서비스 객체를 스프링부트에 생성)
@Slf4j
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> index() {
        return todoRepository.findAll();
    }

    public Todo show(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    public Todo create(TodoForm dto) {
        Todo todo = dto.toEntity();
        if (todo.getId() != null) {
            return null;
        }
        return todoRepository.save(todo);

    }

    public Todo update(Long id, TodoForm dto) {

        //1.수정용 entity 생성
        Todo todo = dto.toEntity();
        log.info("id: {}, content: {}", id, todo.toString());

        //2. 대상 entity 찾기
        Todo target = todoRepository.findById(id).orElse(null);

        //3. 잘못된 용청 처리 (대상이 없거나, id가 다른경우)
        if (target == null || id != todo.getId()){
            log.info("wrong id! id: {}, content: {}", id, todo.toString());
            return null;
        }

        //4. 업데이트
        target.patch(todo);
        Todo updated = todoRepository.save(target);
        return updated;
    }

    public Todo delete(Long id) {
        Todo target = todoRepository.findById(id).orElse(null);
        //잘못된 경우

        if (target == null){
            return null;
        }
        //대상 삭제
        todoRepository.delete(target);

        //대상 반환
        return target;

    }
    @Transactional // 해당 method를 트랜스액션으로 묶는다!
    public List<Todo> createTodos(List<TodoForm> dtos) {
        // dto 묶음을 entity 묶음으로 변환
        List<Todo> todoList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        // entity 묶음을 DB로 저장
        todoList.stream()
                .forEach(todo -> todoRepository.save(todo));

        // 강제 예외 발생
        todoRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("transaction failed")
        );

        // 결과값 반환
        return todoList;

    }
}
