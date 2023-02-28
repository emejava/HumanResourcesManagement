package com.humanresourcesmanagement.model.entity;

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

//persistence
@Entity(name = "RecruitmentSupplyDepEntity")
@Table(name = "tb_RecruitmentSupplyDep")
@NamedQueries({@NamedQuery(name = "findByPerson_id", query = "SELECT oo from RecruitmentSupplyDepEntity oo where oo.person=:id"),
        @NamedQuery(name = "findByFieldOfStudy", query = "SELECT oo from RecruitmentSupplyDepEntity oo where oo.fieldOfStudy=:fieldOfStudy"),
        @NamedQuery(name = "findByUniversity", query = "SELECT oo from RecruitmentSupplyDepEntity oo where oo.university=:university")})
public class RecruitmentSupplyDep {
    @Id
    @SequenceGenerator(name = "RecruitmentSupplyDepSeq", sequenceName = "RecruitmentSupplyDep_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RecruitmentSupplyDepSeq")
    private long id;

    @OneToOne
    @Column(name = "Person_id")
    private Person person;

    @JsonProperty("تحصیلات")
    @Column(name = "Education", columnDefinition = "NVARCHAR2(32)")
    private String education;
    @JsonProperty("رشته تحصیلی")
    @Column(name = "field Of Study", columnDefinition = "NVARCHAR2(30)")
    private String fieldOfStudy;
    @JsonProperty("شرایط سنی")
    @Column(name = "Age Condition", columnDefinition = "NVARCHAR2(2)")
    private int ageCondition;
    @JsonProperty("دانشگاه")
    @Column(name = "University", columnDefinition = "NVARCHAR2(40)")
    private String university;
    @JsonProperty("تجربه کاری")
    @Column(name = "Work Experience", columnDefinition = "NVARCHAR2(100)")
    private String workExperience;
    @JsonProperty("شغل قبلی")
    @Column(name = "Last Job", columnDefinition = "NVARCHAR2(20)")
    private String lastJob;
    @JsonProperty("دلیل خروج از شغل قبلی")
    @Column(name = "Last Job Exit Reason", columnDefinition = "NVARCHAR2(100)")
    private String lastJobExitReason;
    @JsonProperty("آدرس شغل قبلی")
    @Column(name = "Last Job Address", columnDefinition = "NVARCHAR2(255)")
    private String lastJobAddress;
    @JsonProperty("شماره شغل قبلی")
    @Column(name = "Last Job No", columnDefinition = "NVARCHAR2(11)")
    private String lastJobNo;
    @JsonProperty("هدف شقلی")
    @Column(name = "Job Objective", columnDefinition = "NVARCHAR2(45)")
    private String jobObjective;
    @JsonProperty("حقوق درخواستی")
    @Column(name = "Requested Salary", columnDefinition = "NVARCHAR2(8)")
    private String requestedSalary;

    //No Person.ID Constructor


    public RecruitmentSupplyDep(Person person, String education, String fieldOfStudy, int ageCondition, String university, String workExperience, String lastJob, String lastJobExitReason, String lastJobAddress, String lastJobNo, String jobObjective, String requestedSalary) {
        this.person = person;
        this.education = education;
        this.fieldOfStudy = fieldOfStudy;
        this.ageCondition = ageCondition;
        this.university = university;
        this.workExperience = workExperience;
        this.lastJob = lastJob;
        this.lastJobExitReason = lastJobExitReason;
        this.lastJobAddress = lastJobAddress;
        this.lastJobNo = lastJobNo;
        this.jobObjective = jobObjective;
        this.requestedSalary = requestedSalary;
    }

    //Gson
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
