package com.humanresourcesmanagement.model.entity;


import com.humanresourcesmanagement.model.entity.enums.Action;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Persister;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity(name = "logEntity")
@Table(name = "t_log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Action action;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String data;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestamp;

    @OneToOne
    private User user;

    public Log(Action action, String data, User user) {
        this.action = action;
        this.data = data;
        this.user = user;
    }


    @PrePersist
    public void logTimeSetter() {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    @PostPersist
    public void showLog() {
        System.out.printf("JakartaEE [LOG] at [%s] - Action : [%s] - on Data : [%s] \n",
                this.timestamp,
                this.action,
                this.data);
    }
}
