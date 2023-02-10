package com.TODOList2.TODOList2.controller;

import com.TODOList2.TODOList2.Services.TaskService;
import com.TODOList2.TODOList2.dtos.TaskDto;
import com.TODOList2.TODOList2.model.TaskModel;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/todoList")
public class TaskController {
    final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Object> saveTask(@RequestBody TaskDto taskDto){
        if(taskService.existsByTaskName(taskDto.getTaskName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This task is already in the list");
        }
        var taskModel = new TaskModel();
        BeanUtils.copyProperties(taskDto, taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(taskModel));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<TaskModel>> getAllTask(){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTask(@PathVariable(value = "id") Long id){
        Optional<TaskModel> taskModelOptional = taskService.findById(id);
        if(taskModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(taskModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable(value = "id") Long id){
        Optional<TaskModel> taskModelOptional = taskService.findById(id);
        if(taskModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }
        taskService.delete(taskModelOptional.get());
        return  ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully.");
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateTask(@PathVariable(value = "id") Long id, @RequestBody TaskDto taskDto) {
        Optional<TaskModel> taskModelOptional = taskService.findById(id);
        if (taskModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }
        var taskModel = taskModelOptional.get();
        taskModel.setTaskName(taskDto.getTaskName());
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(taskModel));
    }
}
