package com.example.todoApp.repository;


import com.example.todoApp.entity.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    @Override
    ArrayList<Todo> findAll();
}
