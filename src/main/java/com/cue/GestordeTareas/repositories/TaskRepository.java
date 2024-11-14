package com.cue.GestordeTareas.repositories;

import com.cue.GestordeTareas.domain.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task,Integer> {
}
