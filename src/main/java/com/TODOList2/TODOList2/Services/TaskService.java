package com.TODOList2.TODOList2.Services;

import com.TODOList2.TODOList2.model.TaskModel;
import com.TODOList2.TODOList2.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

     final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public TaskModel save(TaskModel taskModel) {
        return taskRepository.save(taskModel);
    }

    public boolean existsByTaskName(String taskName) {
        return taskRepository.existsByTaskName(taskName);
    }

    public List<TaskModel> findAll() {
        return taskRepository.findAll();
    }

    public Optional<TaskModel> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Transactional
    public void delete(TaskModel taskModel) {
        taskRepository.delete(taskModel);
    }
}
