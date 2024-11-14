package com.cue.GestordeTareas.controllers;

import com.cue.GestordeTareas.domain.entities.Task;
import com.cue.GestordeTareas.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PostMapping("/create")
    public ResponseEntity<Task> create(@RequestBody String title){
        Task task = new Task.Builder()
                .id(1)
                .title("Titulo de tarea")
                .build();
        return ResponseEntity.ok(taskService.saveTask(task));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Task> update(@PathVariable int id,
                                       @RequestBody String title){
        return ResponseEntity.ok(taskService.updateTask(id, title));
    }
    @GetMapping("/get")
    public ResponseEntity<List<Task>> getAll(){
        return ResponseEntity.ok(taskService.getTasks());
    }
    @GetMapping("/byId/{id}")
    public ResponseEntity<Task> getById(@PathVariable int id){
        return ResponseEntity.ok(taskService.getTask(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        return ResponseEntity.ok(taskService.deleteTask(id));
    }
}
