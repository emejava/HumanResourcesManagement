package com.humanresourcesmanagement.model.entity;

import com.humanresourcesmanagement.model.entity.enums.Gender;
import com.humanresourcesmanagement.model.entity.enums.MaritalStatus;
import com.humanresourcesmanagement.model.entity.enums.Status;
import lombok.*;

import jakarta.persistence.*;

import java.sql.Timestamp;




@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity(name = "personEntity")
@Table(name = "t_person")
public class Person {
    @Id
    @SequenceGenerator(name="personId",sequenceName = "Person_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "personId")
    private Long id;
    @Column(name = "firstName", columnDefinition = "NVARCHAR2(40)")
    private String firstName;

    @Column(name = "lastName", columnDefinition = "NVARCHAR2(40)")
    private String lastName;

    @Column(name = "nationalCode")
    private String nationalCode;

    @Column(name = "birthday")
    private Timestamp birthday;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "fatherName")
    private String fatherName;

    @Column(name = "address")
    private String address;

    @Column(name = "maritalStatus")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Column(name = "phoneNo")
    private String phoneNo;

    @Column(name = "landLineNo")
    private String landLineNo;

    @Column(name = "children", columnDefinition = "NUMBER(1)")
    private String children;


}
