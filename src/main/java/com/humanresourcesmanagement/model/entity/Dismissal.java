package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

@Entity(name="dismissalEntity")
@Table(name="tb_dismissal")
@NamedQueries({
        @NamedQuery(
                name = "dismissal.findByPersonnelCode", query = "select d from dismissalEntity d where d.person.employment.personnelCode=:personnelCode "),
        @NamedQuery(
                name = "dismissal.findByDate" , query = "select d from dismissalEntity d where d.date=:date")})
public class Dismissal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("کد")
    private long id;

    @JsonProperty("شخص")
    @NonNull
    @OneToOne
    private Person person;

    @JsonProperty("دلیل")
    @NonNull
    @NotBlank(message = "دلیل اخراج وارد نشده")
    @Pattern(regexp = "[0-9][آ-ی\\s]", message = "لطفا از حروف فارسی و اعداد انگلیسی استفاده کنید")
    @Column(columnDefinition = "nvarchar2(255)")
    private String reason;

    @JsonProperty("تاریخ")
    @NonNull
    @NotBlank(message = "تاریخ اخراج وارد نشده")
    @Column(columnDefinition = "nvarchar2(30)")
    private LocalDate date;

    @JsonProperty("آخرین فیش حقوقی")
    @NonNull
    @NotBlank(message = "کد آخرین فیش حقوقی وارد نشده")
    @OneToOne
    private Payment lastPayment;

    @JsonProperty("ضمیمه")
    @NonNull
    @NotBlank(message = "فایل ضمیمه آپلود نشده")
    @OneToOne
    private File attachment;

    public Dismissal(
            Long id,
            @NonNull Person person,
            @NonNull String reason,
            @NonNull LocalDate date,
            Payment payment) {
        this.id = id;
        this.person = person;
        this.reason = reason;
        this.date = date;
        this.payment = payment;
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

