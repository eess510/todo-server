package org.example.persist.entity;


import jakarta.persistence.*;
import lombok.*;
import org.example.constants.TaskStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;


@Getter
@Setter
@ToString
@Builder //파라미터 많을 때
@DynamicInsert //createAt updateAt 자동 데이터 생성 관리 위해
@DynamicUpdate
@Entity(name= "TASK")
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String title;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status; // string  될 수 있도록

    private Date dueDate;
    private String description;


    @CreationTimestamp
    @Column(insertable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(insertable = false, updatable = false)
    private Timestamp updatedAt;



}
