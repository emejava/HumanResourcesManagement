package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import jakarta.persistence.*;
import lombok.*;

//lombok
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@RequiredArgsConstructor
//persistence
@Entity(name = "RecruitmentSupplyDepEntity")
@Table(name = "tb_RecruitmentSupplyDep")
@NamedQueries({@NamedQuery(name = "findByPersonId", query = "SELECT oo from RecruitmentSupplyDepEntity oo where oo.person=:id"),
        @NamedQuery(name = "findByFieldOfStudy", query = "SELECT oo from RecruitmentSupplyDepEntity oo where oo.fieldOfStudy=:fieldOfStudy"),
        @NamedQuery(name = "findByUniversity", query = "SELECT oo from RecruitmentSupplyDepEntity oo where oo.university=:university")})
public class RecruitmentSupplyDep {
    @Id
    @SequenceGenerator(name = "RecruitmentSupplyDepSeq", sequenceName = "RecruitmentSupplyDep_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RecruitmentSupplyDepSeq")
    private long id;

    @OneToOne
    @NonNull
    @Column(name = "Person_id")
    private Person person;

    @NonNull
    @JsonIgnore(value = true)
    @JsonProperty("تحصیلات")
    @Column(name = "Education", columnDefinition = "NVARCHAR2(32)")
    private String education;

    @NonNull
    @JsonIgnore(value = true)
    @JsonProperty("رشته تحصیلی")
    @Column(name = "field Of Study", columnDefinition = "NVARCHAR2(30)")
    private String fieldOfStudy;

    @NonNull
    @JsonProperty("شرایط سنی")
    @Column(name = "Age Condition", columnDefinition = "NVARCHAR2(2)")
    private int ageCondition;

    @NonNull
    @JsonIgnore(value = true)
    @JsonProperty("دانشگاه")
    @Column(name = "University", columnDefinition = "NVARCHAR2(40)")
    private String university;

    @NonNull
    @JsonIgnore(value = true)
    @JsonProperty("تجربه کاری")
    @Column(name = "Work Experience", columnDefinition = "NVARCHAR2(100)")
    private String workExperience;

    @NonNull
    @JsonProperty("معرفی کار قبلی")
    @Column(name = "Last Job", columnDefinition = "NVARCHAR2(20)")
    private String lastJob;

    @NonNull
    @JsonProperty("دلیل خروج از محل کار قبلی")
    @Column(name = "Last Job Exit Reason", columnDefinition = "NVARCHAR2(100)")
    private String lastJobExitReason;

    @NonNull
    @JsonProperty("آدرس محل کار قبلی")
    @Column(name = "Last Job Address", columnDefinition = "NVARCHAR2(255)")
    private String lastJobAddress;

    @NonNull
    @JsonProperty("شماره تماس کار قبلی")
    @Column(name = "Last Job No", columnDefinition = "NVARCHAR2(11)")
    private String lastJobNo;

    @NonNull
    @JsonIgnore(value = true)
    @JsonProperty("هدف شقلی")
    @Column(name = "Job Objective", columnDefinition = "NVARCHAR2(45)")
    private String jobObjective;

    @NonNull
    @JsonIgnore(value = true)
    @JsonProperty("حقوق درخواستی")
    @Column(name = "Requested Salary", columnDefinition = "NVARCHAR2(8)")
    private String requestedSalary;

    //Gson
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
