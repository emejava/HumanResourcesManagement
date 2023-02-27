package com.humanresourcesmanagement.model.entity;

import com.humanresourcesmanagement.model.entity.enums.Status;
import lombok.*;

import jakarta.persistence.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity(name="userEntity")
@Table(name="t_user")
@NamedQueries(@NamedQuery(name="User.isValidate",query = "SELECT u FROM userEntity u WHERE u.userName =:username and u.password =:password"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(columnDefinition = "NVARCHAR2(20)")
    private String userName;

    @Column(columnDefinition = "VARCHAR2(16)")
    private String password;

    @OneToOne
    private Person person;

    @Column(columnDefinition = "CHAR(4)")
    private String accessLevel;

    @Enumerated(EnumType.STRING)
    private Status status;


}

