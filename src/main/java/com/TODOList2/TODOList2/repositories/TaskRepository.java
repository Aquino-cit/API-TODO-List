package com.TODOList2.TODOList2.repositories;

import com.TODOList2.TODOList2.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long>{
    boolean existsByTaskName(String TaskName);
}
