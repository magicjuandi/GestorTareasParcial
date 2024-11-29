package com.cue.GestordeTareas.services.impl;

import com.cue.GestordeTareas.domain.entities.Task;
import com.cue.GestordeTareas.repositories.TaskRepository;
import com.cue.GestordeTareas.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(int id, String taskTitle) {
        taskRepository.findById(id);
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(taskTitle);
        return taskRepository.save(task);
    }

    @Override
    public String deleteTask(int id) {
        taskRepository.deleteById(id);
        return "Task Deleted";
    }

    @Override
    public Optional<Task> getTask(int id) {
        return taskRepository.findById(id);
    }
}
