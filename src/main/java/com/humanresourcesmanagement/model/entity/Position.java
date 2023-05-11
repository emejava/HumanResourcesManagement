package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor

@Entity(name = "positionEntity")
@Table(name = "tb_position")
@NamedQueries({
        @NamedQuery(
                name = "position.findByPosition",
                query = "SELECT p FROM positionEntity p WHERE p.name=:name AND p.status =:status ")})
public class Position {
    @Id
    @JsonProperty("کد")
    @SequenceGenerator(name="positionSeq",sequenceName = "position_seq",initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "position_seq")
    private Long id;

    @JsonProperty("نام")
    @NonNull
    @NotBlank(message = "نام وارد نشده")
    @Pattern(regexp = "[آ-ی\\s]*", message = "لطفا از حروف فارسی استفاده کنید")
    @Column(name="name",columnDefinition = "NVARCHAR2(20)")
    private String name;

    @JsonProperty("وضعیت")
    @Column(name = "status",columnDefinition = "NVARCHAR2(10)")
    @Enumerated(EnumType.STRING)
    private Status status;

    @PrePersist
    public void StatusSet(){
        status = Status.Active;
    }

    public Position(
            Long id,
            @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
