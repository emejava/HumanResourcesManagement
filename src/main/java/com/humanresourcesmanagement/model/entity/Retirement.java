package com.humanresourcesmanagement.model.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor


@Entity(name = "retirementEntity")
@Table(name = "tb_retirement")
@NamedQueries({
        @NamedQuery(name="retirement.findByPersonnelCode",
                query = "select r from retirementEntity r where r.person.employment.personnelCode=:personnelCode"),
        @NamedQuery(name="retirement.findByDate",
                query = "select r from retirementEntity r where r.date=:date")})
public class Retirement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("کد")
    private Long id;

    @JsonProperty("شخص")
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private Person person;

    @JsonProperty("تاریخ")
//    @NotBlank(message = "تاریخ اعلام بازنشتگی وارد نشده")
    @NonNull
    private LocalDate date;

    @JsonProperty("آخرین فیش حقوقی")
    @NonNull
//    @NotBlank(message = "کد آخرین فیش حقوقی وارد نشده")
    @OneToOne(cascade = CascadeType.ALL)
    private Payment lastPayment;

    @JsonProperty("استعفا نامه")
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private Attachment attachment;

    public Retirement(Long id,
                      @NonNull Person person,
                      @NonNull LocalDate date,
                      Payment lastPayment) {
        this.id = id;
        this.person = person;
        this.date = date;
        this.lastPayment = lastPayment;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
