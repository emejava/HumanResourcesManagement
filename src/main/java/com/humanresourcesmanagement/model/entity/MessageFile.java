package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.Status;
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

@Entity(name = "messageFileDirectionEntity")
@Table(name = "tb_file")
@NamedQueries(
        @NamedQuery(name = "messageFileDirection.findAllActive",
        query = "SELECT m FROM messageFileDirectionEntity m WHERE m.status=:status"))
public class MessageFile {
    @Id
    @JsonProperty("کد")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("نام فرستنده")
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private User sender;

    @JsonProperty("آدرس فایل")
    @NonNull
    @Column(name = "file_path")
    private String filePath;

    @JsonProperty("پیام")
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private Message msg;

    @JsonProperty("وضعیت")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "NVARCHAR2(10)")
    private Status status;

    @JsonProperty("زمان بارگذاری")
    private Timestamp dateTime;


    @PrePersist
    public void uploadDateTimeAndSetStatus() {
        dateTime = Timestamp.valueOf(LocalDateTime.now());
        status = Status.Active;
    }


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
