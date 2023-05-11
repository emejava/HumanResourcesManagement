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
@NoArgsConstructor(force = true)
@RequiredArgsConstructor

@Entity(name = "userEntity")
@Table(name = "tb_user")
@NamedQueries({
        @NamedQuery(
                name = "user.findAllActive",
                query = "SELECT u FROM userEntity u WHERE u.status =:status "),
        @NamedQuery(
                name = "user.findByUsername",
                query = "SELECT u FROM userEntity u WHERE u.username=:username "),
        @NamedQuery(
                name = "user.isValidate",
                query = "SELECT u FROM userEntity u WHERE u.username =:username AND u.password =:password AND u.status =:status"),
        @NamedQuery(
                name = "user.hasAccess",
                query = "SELECT u FROM userEntity u WHERE u.username=:username AND u.password=:password AND u.status =:status AND u.accessLevel=:title")})
public class User {
    @Id
    @JsonProperty("نام کاربری")
    @NonNull
    @NotBlank(message = "نام کاربری وارد نشده")
    @Pattern(regexp = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$", message = "کاراکتر های مجاز: (اعداد و حروف انگلیسی)(@)(_)(.)(&)")
    @Column(columnDefinition = "NVARCHAR2(20)")
    private String username;

    @JsonProperty("رمز عبور")
    @NonNull
    @NotBlank(message = "رمز عبور وارد نشده")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "کاراکتر های مجاز: (اعداد و حروف انگلیسی)(@)(_)(.)(&) و حداقل 8 کاراکتر")
    @Column(columnDefinition = "NVARCHAR2(20)")
    private String password;

    @JsonProperty("شخص")
    @OneToOne(cascade = CascadeType.ALL)
    @NonNull
    private Person person;

    @JsonProperty("نوع کاربری")
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private Role role;

    @JsonProperty("سطح دسترسسی")
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
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
            @NonNull String username,
            @NonNull String password,
            @NonNull Person person,
            @NonNull AccessLevel accessLevel) {
        this.username = username;
        this.password = password;
        this.person = person;
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

