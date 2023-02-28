package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.Position;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity(name = "dutyEntity")
@Table(name = "tb_duty")
public class Duty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Position position;

    @JsonProperty("وظیفه")
    @NotBlank(message = "وظیفه را شرح دهید")
    private String duty;

    public Duty(Position position, String duty) {
        this.position = position;
        this.duty = duty;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
