package com.humanresourcesmanagement.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.Action;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor

@Entity(name = "logEntity")
@Table(name = "tb_log")
public class Log {
    @Id
    @JsonProperty("کد")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("نوع عملیات")
    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "action", columnDefinition = "NVARCHAR2(15)")
    private Action action;

    @JsonProperty("توضیحات")
    @NonNull
    @Column(columnDefinition = "VARCHAR2(150)")
    private String data;

    @JsonProperty("تاریخ")
    private Timestamp timestamp;

    @JsonProperty("عامل")
    @OneToOne(cascade = CascadeType.ALL)
    @NonNull
    private User user;

    @PostPersist
    public void setTimeStamp(){
        timestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
