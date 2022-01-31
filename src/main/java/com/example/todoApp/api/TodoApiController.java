package com.example.todoApp.api;

import com.example.todoApp.dto.TodoForm;
import com.example.todoApp.entity.Todo;
import com.example.todoApp.repository.TodoRepository;
import com.example.todoApp.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // RestAPI 용 컨트롤러! 데이터 (json)을 반환
@Slf4j
public class TodoApiController {

    @Autowired // DI, 생성 객체를 가져와 연결
    private TodoService todoService;

    //get
    @GetMapping("/api/todos")
    public List<Todo> index() {
        return todoService.index();
    }

    @GetMapping("/api/todos/{id}")
    public Todo show(@PathVariable Long id) {
        return todoService.show(id);
    }

    // post
    @PostMapping ("/api/todos")
    public ResponseEntity<Todo> create(@RequestBody TodoForm dto) {
        Todo created = todoService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //patch
    @PatchMapping("/api/todos/{id}")
    public ResponseEntity<Todo> update(@PathVariable Long id,
                                       @RequestBody TodoForm dto){
        Todo updated = todoService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    //delete
    @DeleteMapping("/api/todos/{id}")
    public ResponseEntity<Todo> delete(@PathVariable Long id) {
        Todo deleted = todoService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    //transaction
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Todo>> transactionTest(@RequestBody List<TodoForm> dtos){
        List<Todo> createdList = todoService.createTodos(dtos);

        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
