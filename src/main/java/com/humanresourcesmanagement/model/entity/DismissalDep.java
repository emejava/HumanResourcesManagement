package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.humanresourcesmanagement.model.entity.enums.Status;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity(name="DismissalDepEntity")
@Table(name="t_dismissaldep")
@NamedQueries({@NamedQuery(name = "FindByPersonNelCode", query = "select u from DismissalDepEntity u where personnelCode =:personnelCode "),
        @NamedQuery(name = "FindByPersonId" , query = "select oo from personEntity oo where id =:id")})
public class DismissalDep {
    @Id
    // @SequenceGenerator(name = "dismissalDepSeq",sequenceName = "dismissaldep_seq")
    // @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "dismissalDepSeq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonProperty("اخراج")
    @OneToOne
    @Column(columnDefinition = "nvarchar2(30)")
    private Person person;

    @Column(columnDefinition = "number")
    private Long personnelCode;

    @Column(columnDefinition = "nvarchar2(30)")
    private String reasonOfDismissal;

    @Column(columnDefinition = "nvarchar2(30)")
    private Timestamp timestamp;

    @Column(columnDefinition = "nvarchar2(30)")
    private Status status;

    @Column(columnDefinition = "number")
    private long checkOut;

    public DismissalDep() {
    }

    public long getId() {
        return id;
    }

    public DismissalDep setId(long id) {
        this.id = id;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public DismissalDep setPerson(Person person) {
        this.person = person;
        return this;
    }

    public Long getPersonnelCode() {
        return personnelCode;
    }

    public DismissalDep setPersonnelCode(Long personnelCode) {
        this.personnelCode = personnelCode;
        return this;
    }

    public String getReasonOfDismissal() {
        return reasonOfDismissal;
    }

    public DismissalDep setReasonOfDismissal(String reasonOfDismissal) {
        this.reasonOfDismissal = reasonOfDismissal;
        return this;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public DismissalDep setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public DismissalDep setStatus(Status status) {
        this.status = status;
        return this;
    }

    public long getCheckOut() {
        return checkOut;
    }

    public DismissalDep setCheckOut(long checkOut) {
        this.checkOut = checkOut;
        return this;
    }
}

