package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor

@Entity(name = "resignationEntity")
@Table(name = "tb_resignation")
@NamedQueries({
        @NamedQuery(name="resignation.findByPersonnelCode",
                query = "select r from resignationEntity r where r.person.employment.personnelCode=:personnelCode"),
        @NamedQuery(name="resignation.findByDate",
                query = "select r from resignationEntity r where r.date=:date")})
public class Resignation {
    @Id
    @JsonProperty("کد")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("شخص")
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private Person person;

    @JsonProperty("تاریخ")
    @NonNull
    private LocalDate date;

    @JsonProperty("دلیل استعفا")
    @NonNull
    @NotBlank(message = "دلیل استعفا وارد نشده")
    @Column(name = "reason",columnDefinition = "CLOB")
    private String reason;

    @JsonProperty("وضعیت درخواست")
    @Enumerated(EnumType.STRING)
    @Column(name = "status",columnDefinition = "NVARCHAR2(10)")
    private Status status;

    @JsonProperty
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private Attachment attachment;

    @JsonProperty("آخرین فیش حقوقی")
    @NonNull
//    @NotBlank(message = "کد آخرین فیش حقوقی وارد نشده")
    @OneToOne(cascade = CascadeType.ALL)
    private Payment lastPayment;

    @PrePersist
    public void statusSet(){
        status = Status.Pending;
    }

    public Resignation(
            Long id,
            @NonNull Person person,
            @NonNull Attachment attachment,
            @NonNull Payment lastPayment) {
        this.id = id;
        this.person = person;
        this.attachment = attachment;
        this.lastPayment = lastPayment;
    }

    public Resignation(
            @NonNull Person person,
            @NonNull LocalDate date,
            @NonNull Attachment attachment) {
        this.person = person;
        this.date = date;
        this.attachment = attachment;
    }


    public Resignation(Long id, @NonNull Person person, Status status) {
        this.id = id;
        this.person = person;
        this.status = status;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}