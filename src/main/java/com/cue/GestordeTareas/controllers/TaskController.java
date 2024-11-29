package com.cue.GestordeTareas.controllers;

import com.cue.GestordeTareas.domain.entities.Task;
import com.cue.GestordeTareas.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PostMapping("/create")
    public ResponseEntity<Task> create(@RequestBody String title){
        Task task = new Task.Builder()
                .title(title)
                .build();
        return ResponseEntity.ok(taskService.saveTask(task));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Task> update(@PathVariable int id,
                                       @RequestBody String title){
        return ResponseEntity.ok(taskService.updateTask(id, title));
    }
    @GetMapping("/byId/{id}")
    public ResponseEntity<Optional<Task>> getById(@PathVariable int id){
        return ResponseEntity.ok(taskService.getTask(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        return ResponseEntity.ok(taskService.deleteTask(id));
    }
}
