package org.example.service;

import org.example.constants.TaskStatus;
import org.example.persist.TaskRepository;
import org.example.persist.entity.TaskEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.sql.Timestamp;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //mockito extension을 활성하기위한 어노테이션
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks //Inject 어노테이션 -> mock 객체 뿐 아니라 모든 의존성을 해당객체에 주입해줌
    private TaskService taskService;


    @Test
    @DisplayName("할 일 추가 기능")
    void add() {
        var title = "test";
        var description = "test.description";
        var dueDate = LocalDate.now();

        when(taskRepository.save(any(TaskEntity.class)))
                .thenAnswer(invocation -> {
                    var e = (TaskEntity) invocation.getArgument(0);
                    e.setId(1L);
                    e.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    e.setUpdatedAt(new Timestamp((System.currentTimeMillis())));
                    return e;
                });

        var actual = taskService.add(title, description , dueDate);

        verify(taskRepository, times(1)).save(any());

        assertEquals(1L, actual.getId());
        assertEquals(title , actual.getTitle());
        assertEquals(dueDate.toString() , actual.getDueDate());
        assertEquals(TaskStatus.TODO, actual.getStatus());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());








    }
}