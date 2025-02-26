package org.example.web.vo;


import lombok.Getter;
import lombok.ToString;
import org.example.constants.TaskStatus;
import org.example.model.Task;

@Getter
@ToString
public class TaskStatusRequest {

    private TaskStatus status;
}
