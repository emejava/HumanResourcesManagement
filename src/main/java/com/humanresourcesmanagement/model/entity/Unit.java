package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.Position;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity(name="unitEntity")
@Table(name="tb_Unit")
public class Unit {

    @JsonProperty("کد")
    @Id
    @SequenceGenerator(name = "UnitSeq",sequenceName = "unit_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "UnitSeq")
    private Long id;

    @JsonProperty("نام بخش")
    @NotBlank(message = "نام بخش را وارد کنید")
    private String name;

    @JsonProperty("بخش های مرتبط")
    private String relatedUnits;

    @JsonProperty("شهر")
    @NotBlank(message = "شهر مربوط به بخش را وارد کنید")
    private String city;

    @JsonProperty("وظایف")
    @OneToMany(targetEntity = Duty.class)
    private List<Duty> duties;

    public Unit(String name, String relatedUnits, String city, List<Duty> duties) {
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
