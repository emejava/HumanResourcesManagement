package com.humanresourcesmanagement.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

@Entity(name = "leaveDaysEntity")
@Table(name = "tb_leaveDays")
public class LeaveDays {
    @Id
    private Long id;
}
