package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor


@Entity(name="unitEntity")
@Table(name="tb_Unit")
@NamedQueries({
        @NamedQuery(
                name = "unit.findAllActive",
                query = "SELECT u FROM unitEntity u WHERE u.status =:status "),
        @NamedQuery(
                name = "unit.findByName",
                query = "SELECT u FROM unitEntity u WHERE u.name=:name"),
        @NamedQuery(
                name = "unit.findByCity",
                query = "SELECT u FROM unitEntity u WHERE u.city=:city ")})
public class Unit {

    @JsonProperty("کد")
    @Id
    @SequenceGenerator(name = "UnitSeq",sequenceName = "unit_seq",initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "UnitSeq")
    private Long id;

    @JsonProperty("نام بخش")
    @NonNull
    @NotBlank(message = "نام بخش را وارد کنید")
    @Pattern(regexp = "[آ-ی\\s]*", message = "لطفا از حروف فارسی استفاده کنید")
    @Column(name = "name", columnDefinition = "NVARCHAR2(20)")
    private String name;

    @JsonProperty("بخش های مرتبط")
//    @NotBlank(message = "بخش های مربوطه را انتخاب کنید")
    @OneToMany
    private List<Unit> relatedUnits;

    @JsonProperty("شهر")
    @NonNull
    @NotBlank(message = "شهر مربوط به بخش را وارد کنید")
    @Column(name = "city", columnDefinition = "NVARCHAR2(20)")
    private String city;

    @JsonProperty("وظایف")
    @NonNull
//    @NotBlank(message = "وظایف مربوطه را انتخاب کنید")
    @OneToMany(targetEntity = Duty.class)
    private List<Duty> duties;

    @JsonProperty("وضعیت")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "NVARCHAR2(10)")
    private Status status;

    @PrePersist
    public void statusSet(){
        status = Status.Active;
    }

    public Unit(
            Long id,
            @NonNull String name,
            @NonNull List<Unit> relatedUnits,
            @NonNull String city,
            @NonNull List<Duty> duties) {
        this.id = id;
        this.name = name;
        this.relatedUnits = relatedUnits;
        this.city = city;
        this.duties = duties;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
