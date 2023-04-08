package com.humanresourcesmanagement.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.EmploymentType;
import com.humanresourcesmanagement.model.entity.enums.ShiftWork;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

@Entity(name = "employmentEntity")
@Table(name = "tb_employment")
public class Employment {
    @Id
    @JsonProperty("کد پرسنلی")
    @Column(name = "personnelCode",columnDefinition = "NUMBER(10)")
    private Long personnelCode;
    
    @JsonProperty("شخص")
    @NonNull
    @OneToOne
    private Person person;

    @JsonProperty("نوع استخدام")
    @NonNull
    @NotBlank(message = "نوع استخدام راانتخاب کنید")
    @Column(name = "employmentType", columnDefinition = "NVARCHAR2(15)")
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @JsonProperty("بخش")
    @NonNull
    @NotBlank(message = "بخش راانتخاب کنید")
    @OneToOne
    private Unit unit;

    @JsonProperty("وظایف")
    @NonNull
    @NotBlank(message = "وظایف را وارد کنید")
    @OneToOne
    private Duty duty;

    @JsonProperty("سمت")
    @NonNull
    @NotBlank(message = "سمت به درستی انتخاب نشده")
    @OneToOne
    private Position position;

    @JsonProperty("تاریخ آمادگی شروع به کار")
    @NonNull
    @NotBlank(message = "تاریخ شروع به کار را وارد کنید")
    private Timestamp startWorkingDate;

    @JsonProperty("شیفت")
    @NonNull
    @NotBlank(message = "شیفت کاری را انتخاب کنید")
    @Column(name = "shiftWork", columnDefinition = "NVARCHAR2(15)")
    @Enumerated(EnumType.STRING)
    private ShiftWork shiftWork;

    @JsonProperty("عکس قرارداد")
    @OneToOne
    private File CV;

    public Employment(
            Long personnelCode,
            @NonNull Person person,
            @NonNull EmploymentType employmentType,
            @NonNull Unit unit,
            @NonNull Duty duty,
            @NonNull Position position,
            @NonNull Timestamp startWorkingDate,
            @NonNull ShiftWork shiftWork) {
        this.personnelCode = personnelCode;
        this.person = person;
        this.employmentType = employmentType;
        this.unit = unit;
        this.duty = duty;
        this.position = position;
        this.startWorkingDate = startWorkingDate;
        this.shiftWork = shiftWork;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}