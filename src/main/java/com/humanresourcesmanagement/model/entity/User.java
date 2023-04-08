package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor

@Entity(name = "userEntity")
@Table(name = "tb_user")
@NamedQueries({
        @NamedQuery(
                name = "user.findAllActive",
                query = "SELECT u FROM userEntity u WHERE u.status =:Active "),
        @NamedQuery(
                name = "user.isValidate",
                query = "SELECT u FROM userEntity u WHERE u.userName =:username AND u.password =:password AND u.status =:Active"),
        @NamedQuery(
                name = "user.hasAccess",
                query = "SELECT u FROM userEntity u WHERE u.userName=:username AND u.password=:password AND u.status =:Active AND u.accessLevel=:title")})
public class User {
    @Id
    @JsonProperty("نام کاربری")
    @NonNull
    @NotBlank(message = "نام کاربری وارد نشده")
    @Pattern(regexp = "[@_.&][a-z A-Z][0-9]", message = "کاراکتر های مجاز: (اعداد و حروف انگلیسی)(@)(_)(.)(&)")
    @Column(columnDefinition = "NVARCHAR2(20)")
    private String userName;

    @JsonProperty("رمز عبور")
    @NonNull
    @NotBlank(message = "رمز عبور وارد نشده")
    @Pattern(regexp = "[@_.&][a-z A-Z][0-9]{8,20}", message = "کاراکتر های مجاز: (اعداد و حروف انگلیسی)(@)(_)(.)(&) و حداقل 8 کاراکتر")
    @Column(columnDefinition = "NVARCHAR2(20)")
    private String password;

    @JsonProperty("شخص")
    @OneToOne
    @NonNull
    private Person person;

    @JsonProperty("نوع کاربری")
    @NonNull
    @OneToOne
    private Role role;

    @JsonProperty("سطح دسترسسی")
    @NonNull
    @OneToOne
    private AccessLevel accessLevel;

    @JsonProperty("آخرین بازدید")
    private Timestamp lastVisited;

    @JsonProperty("وضعیت")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "NVARCHAR2(10)")
    private Status status;

    @PrePersist
    public void userStatus() {
        status = Status.Active;
    }

    public User(
            @NonNull String userName,
            @NonNull String password,
            @NonNull Person person,
            @NonNull AccessLevel accessLevel) {
        this.userName = userName;
        this.password = password;
        this.person = person;
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

