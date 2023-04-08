package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor


@Entity(name = "resignationEntity")
@Table(name = "tb_resignation")
public class Resignation {
    @Id
    @JsonProperty("کد")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("شخص")
    @NonNull
    @OneToOne
    private Person person;

    @JsonProperty("تاریخ")
    @NonNull
    private LocalDate date;

    @JsonProperty("وضعیت درخواست")
    @Enumerated(EnumType.STRING)
    @Column(name = "status",columnDefinition = "NVARCHAR2(10)")
    private Status status;

    @JsonProperty
    @NonNull
    @OneToOne
    private File attachment;

    @PrePersist
    public void statusSet(){
        status = Status.Pending;
    }

    public Resignation(@NonNull Person person, @NonNull LocalDate date, @NonNull File attachment) {
        this.person = person;
        this.date = date;
        this.attachment = attachment;
    }

    public Resignation(Long id, @NonNull Person person, @NonNull LocalDate date, @NonNull File attachment) {
        this.id = id;
        this.person = person;
        this.date = date;
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}