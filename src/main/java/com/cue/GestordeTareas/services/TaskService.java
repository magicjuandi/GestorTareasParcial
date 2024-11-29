package com.cue.GestordeTareas.services;

import com.cue.GestordeTareas.domain.entities.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task saveTask(Task task);
    Task updateTask(int id, String taskTitle);
    String deleteTask(int id);
    Optional<Task> getTask(int id);
}
