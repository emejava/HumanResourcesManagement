package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor

@Entity(name = "leaveDaysEntity")
@Table(name = "tb_leaveDays")
@NamedQueries({
        @NamedQuery(
                name = "leaveDays.findByPersonnelCode",
                query = "SELECT l FROM leaveDaysEntity l WHERE l.person.employment.personnelCode=:personnelCode"),
        @NamedQuery(
                name = "leave.findByAccepted",
                query = "SELECT l FROM leaveDaysEntity l WHERE l.accepted=true"),
        @NamedQuery(
                name = "leaveDays.findByPersonnelCodeAndBetweenTime",
                query = "SELECT l FROM leaveDaysEntity l WHERE " +
                        "l.person.employment.personnelCode =:personnelCode" +
                        " AND l.accepted=:accepted AND l.from BETWEEN :from AND :till ")})
public class LeaveDays {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("کد")
    private Long id;

    @JsonProperty("شخص")
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private Person person;

    @JsonProperty("از تاریخ")
    @NonNull
    private LocalDate from;

    @JsonProperty("تا تاریخ")
    @NonNull
    private LocalDate till;

    @JsonProperty("تعداد روز درخواستی")
    @NonNull
//    @Pattern(regexp = "^\\d{2}$", message = "لطفا از عداد استفاده کنید")
    @Column(name = "days-count",columnDefinition = "NUMBER(2)")
    private Byte daysCount;

    @JsonProperty("تقاضا")
    @NotBlank(message = "درخواست شما وارد نشده است")
    @Column(columnDefinition = "NVARCHAR2(250)")
    @NonNull
    private String request;

    @JsonProperty("تاریخ درخواست")
    private LocalDate date;

    @JsonProperty("پذیرفته شده")
    @Column(name = "accepted_status")
    private Boolean accepted;

    @PrePersist
    public void setDate() {
        date = LocalDate.now();
    }

    public LeaveDays(
            Long id,
            @NonNull Person person,
            @NonNull LocalDate from,
            @NonNull LocalDate till,
            @NonNull Byte daysCount,
            @NonNull String request,
            Boolean accepted) {
        this.id = id;
        this.person = person;
        this.from = from;
        this.till = till;
        this.daysCount = daysCount;
        this.request = request;
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
