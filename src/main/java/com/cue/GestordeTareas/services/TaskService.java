package com.cue.GestordeTareas.services;

import com.cue.GestordeTareas.domain.entities.Task;

import java.util.List;

public interface TaskService {
    Task saveTask(Task task);
    Task updateTask(int id, String taskTitle);
    String deleteTask(int id);
    List<Task> getTasks();
    Task getTask(int id);
}
