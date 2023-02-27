package com.humanresourcesmanagement.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.EmploymentType;
import com.humanresourcesmanagement.model.entity.enums.Position;
import com.humanresourcesmanagement.model.entity.enums.ShiftWork;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity(name = "PersonEmploymentEntity")
@Table(name = "tb_personEmployment")
//@NamedNativeQuery(name = "PE.personnelCode", query = "Select personnelcode_seq.nextval from dual")
//@NamedQueries({@NamedQuery(name = "PE.UnitId", query = "Select un from unitEntity un where unit.name=:name")})
public class PersonEmployment implements Serializable {
    @Id
    @JsonProperty("کد پرسنلی")
    @NotBlank(message = "کد پرسنلی را وارد کنید")
    private long personnelCode;
    //    String code = + personEmploymentDa.findById(Unit.class,personEmployment.getUnit()).toString() +
    
    @JsonProperty("شخص")
    @NotBlank(message = "کد شخص مورد نظر را وارد کنید")
    @OneToOne
    private Person person;

    @JsonProperty("نوع استخدام")
    @NotBlank(message = "نوع استخدام راانتخاب کنید")
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @JsonProperty("بخش")
    @NotBlank(message = "بخش راانتخاب کنید")
    @OneToOne
    private Unit unit;

    @JsonProperty("وظایف")
    @NotBlank(message = "وظایف را وارد کنید")
    @OneToOne
    private List<Duty> duties;

    @JsonProperty("سمت")
    @NotBlank(message = "سمت به درستی انتخاب نشده")
    @Enumerated(EnumType.STRING)
    private Position position;

    @JsonProperty("تاریخ شروع به کار")
    @NotBlank(message = "تاریخ شروع به کار را وارد کنید")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp startWorkingDate;


    @JsonProperty("مجموع ساعت کاری روزانه")
    @NotBlank(message = "مجموع ساعت کاری روزانه را وارد کنید")
    private String workingTimePerDay;

    @JsonProperty("شیفت")
    @NotBlank(message = "شیفت کاری را انتخاب کنید")
    @Enumerated(EnumType.STRING)
    private ShiftWork shiftWork;


    public PersonEmployment(Person person, EmploymentType employmentType, Unit unit, List<Duty> duties, Position position, Timestamp startWorkingDate, String workingTimePerDay, ShiftWork shiftWork) {
        this.person = person;
        this.employmentType = employmentType;
        this.unit = unit;
        this.duties = duties;
        this.position = position;
        this.startWorkingDate = startWorkingDate;
        this.workingTimePerDay = workingTimePerDay;
        this.shiftWork = shiftWork;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
