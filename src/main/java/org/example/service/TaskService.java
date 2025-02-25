package org.example.service;

import lombok.RequiredArgsConstructor; // 개발 편의 위해
import lombok.extern.slf4j.Slf4j; // 로그사용 위해
import org.example.model.Task;
import org.example.constants.TaskStatus;
import org.example.persist.entity.TaskEntity;
import org.example.persist.TaskRepository;
import org.springframework.stereotype.Service; //service 클래스라서 service 어노테이션

import java.sql.Date;
import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor

public class TaskService {
    private final TaskRepository taskRepository;
    public Task add(String title , String description , LocalDate dueDate ) {
        var e = TaskEntity.builder()
                .title(title)
                .description(description)
                .dueDate(Date.valueOf(dueDate))
                .status(TaskStatus.TODO)
                .build();

       var saved = this.taskRepository.save(e); //생성된 테스크를 repository에 저장
       return entityToObject(saved);
    }

    private Task entityToObject(TaskEntity e){
        return  Task.builder()
                .id(e.getId())
                .title(e.getTitle())
                .description(e.getDescription())
                .status(e.getStatus())
                .dueDate(e.getDueDate().toString())
                .createdAt(e.getCreatedAt().toLocalDateTime())
                .updatedAt(e.getUpdatedAt().toLocalDateTime())
                .build();



    }

}
