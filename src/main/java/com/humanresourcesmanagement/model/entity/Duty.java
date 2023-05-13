package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import com.humanresourcesmanagement.model.entity.enums.Status;
import oracle.jdbc.proxy.annotation.Pre;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor

@Entity(name = "dutyEntity")
@Table(name = "tb_duty")
@NamedQueries({
        @NamedQuery(
                name = "duty.findAllActive",
                query = "SELECT d FROM dutyEntity d WHERE d.status =:status "),
        @NamedQuery(
                name = "duty.findByPosition",
                query = "SELECT d FROM dutyEntity d WHERE d.position=:position AND d.status =:status ")})
public class Duty {
    @Id
    @JsonProperty("کد")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("سمت")
    @NonNull
//    @NotBlank(message = "سمت را انتخاب کنید")
    @OneToOne(cascade = CascadeType.ALL)
    private Position position;

    @JsonProperty("شرح وظیفه")
    @NonNull
    @NotBlank(message = "وظیفه را شرح دهید")
    @Pattern(regexp = "[،0-9آ-ی\\s]*", message = "لطفا از حروف فارسی استفاده کنید")
    @Column(name = "duty_explanation", columnDefinition = "NVARCHAR2(255)")
    private String dutyExplanation;

    @JsonProperty("وضعیت")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "NVARCHAR2(10)")
    private Status status;

    public Duty(Long id, @NonNull Position position, @NonNull String dutyExplanation) {
        this.id = id;
        this.position = position;
        this.dutyExplanation = dutyExplanation;
    }

    @PrePersist
    public void statusSet(){
        status = Status.Active;
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
