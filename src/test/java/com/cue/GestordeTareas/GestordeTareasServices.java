package com.cue.GestordeTareas;

import com.cue.GestordeTareas.domain.entities.Task;
import com.cue.GestordeTareas.repositories.TaskRepository;
import com.cue.GestordeTareas.services.TaskService;
import com.cue.GestordeTareas.services.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GestordeTareasServices {
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskServiceImpl taskService;
    private Task task;

    @Test
    public void testSaveTask(){
        Task task = new Task(1,"New Task");
        when(taskRepository.save(any())).thenReturn(task);
        Task result = taskService.saveTask(task);
        assertNotNull(result);
        verify(taskRepository,times(1)).save(any());
    }
    @Test
    public void testUpdateTask(){
        Task task = new Task(1,"New Task");
        when(taskRepository.findById(anyInt())).thenReturn(Optional.of(task));
        when(taskRepository.save(any())).thenReturn(task);
        Task result = taskService.updateTask(task.getId(),"New Task Title");
        assertNotNull(result);
        verify(taskRepository,times(1)).save(any());
    }
    @Test
    public void testFindTask(){
        Task task = new Task(1,"New Task");
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));
        Task result = taskService.getTask(task.getId()).orElseThrow();
        assertNotNull(result);
        verify(taskRepository,times(1)).findById(task.getId());
    }
    @Test
    public void testDeleteTask(){
        int taskId = 1;
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(new Task(taskId,"Existing Task")));
        doNothing().when(taskRepository).deleteById(taskId);
        String result = taskService.deleteTask(taskId);
        assertEquals("Task Deleted", result);
        verify(taskRepository,times(1)).deleteById(taskId);
    }
}
