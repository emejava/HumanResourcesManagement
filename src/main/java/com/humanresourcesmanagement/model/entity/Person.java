package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.*;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.*;
import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor

@Entity(name = "personEntity")
@Table(name = "tb_person")
@NamedQueries({@NamedQuery(
        name = "person.findAllActive",query = "SELECT p FROM personEntity p WHERE p.status=:status"),
        @NamedQuery(
                name = "person.findByFirstAndLastName",query = "SELECT p FROM personEntity p WHERE p.firstName=:firstName AND p.lastName=:lastName"),
        @NamedQuery(
                name = "person.findByNationalCode",query = "SELECT p FROM personEntity p WHERE p.nationalCode=:nationalCode"),
        @NamedQuery(
                name = "person.findByPhoneNo",query = "SELECT p FROM personEntity p WHERE p.phoneNo=:phoneNo"),
        @NamedQuery(
                name = "person.findByBirthday",query = "SELECT p FROM personEntity p WHERE p.birthday=:birthDay"),
        @NamedQuery(
                name = "person.findByPersonnelCode",query = "SELECT p FROM personEntity p WHERE p.employment.personnelCode=:personnelCode")})
public class Person {
    @Id
    @JsonProperty("کد")
    @SequenceGenerator(name = "personSeq", sequenceName = "person_seq", initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeq")
    private Long id;

    @JsonProperty("نام")
    @NonNull
    @NotBlank(message = "نام وارد نشده")
    @Pattern(regexp = "[آ-ی\\s]*", message = "لطفا از حروف فارسی استفاده کنید")
    @Column(name = "first_name", columnDefinition = "NVARCHAR2(20)")
    private String firstName;

    @JsonProperty("نام خانوادگی")
    @NonNull
    @NotBlank(message = "نام خانوادگی وارد نشده")
    @Pattern(regexp = "[آ-ی\\s]*", message = "لطفا از حروف فارسی استفاده کنید")
    @Column(name = "last_name", columnDefinition = "NVARCHAR2(20)")
    private String lastName;

    @JsonProperty("کد ملی")
    @NonNull
    @NotBlank(message = "کد ملی وارد نشده")
    @Pattern(regexp = "^\\d{10}$", message = "لطفا از اعداد انگلیسی استفاده کنید")
    @Column(name = "national_Code", columnDefinition = "NUMBER(10)")
    private String nationalCode;

    @JsonProperty("شماره شناسنامه")
    @NonNull
    @NotBlank(message = "شماره شناسنامه وارد نشده")
    @Pattern(regexp = "^\\d{15}$", message = "لطفا از اعداد انگلیسی استفاده کنید")
    @Column(name = "birth_Certificate_Code", columnDefinition = "NUMBER(15)")
    private String birthCertificateCode;

    @JsonProperty("تاریخ تولد")
    @NonNull
//    @NotBlank(message = "تاریخ تولد وارد نشده")
    @Column(name = "birthday")
    private LocalDate birthday;

    @JsonProperty("جنسیت")
    @NonNull
//    @NotBlank(message = "جنسیت انتخاب نشده")
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonProperty("سن")
    @NonNull
//    @NotBlank(message = "")
//    @Pattern(regexp = "^\\d{2}$", message = "لطفا از اعداد انگلیسی استفاده کنید")
    @Column(name = "age", columnDefinition = "NVARCHAR2(2)")
    private Integer age;

    @JsonProperty("نام پدر")
    @NonNull
    @NotBlank(message = "نام پدر وارد نشده")
    @Pattern(regexp = "[آ-ی\\s]*", message = "لطفا از حروف فارسی استفاده کنید")
    @Column(name = "father", columnDefinition = "NVARCHAR2(15)")
    private String father;

    @JsonProperty("استان")
    @NonNull
    @NotBlank(message = "استان وارد نشده")
    @Column(name = "state", columnDefinition = "NVARCHAR2(15)")
    private String state;

    @JsonProperty("شهر")
    @NonNull
    @NotBlank(message = "شهر وارد نشده")
    @Column(name = "city", columnDefinition = "NVARCHAR2(15)")
    private String city;

    @JsonProperty("نشانی")
    @NonNull
    @NotBlank(message = "نشانی وارد نشده")
    @Pattern(regexp = "[، آ-ی\\s]*", message = "لطفا از حروف فارسی استفاده کنید")
    @Column(name = "address", columnDefinition = "NVARCHAR2(70)")
    private String address;

    @JsonProperty("کد پستی")
    @NonNull
    @NotBlank(message = "کد پستی وارد نشده")
    @Pattern(regexp = "^\\d{10}$", message = "لطفا از اعداد انگلیسی استفاده کنید")
    @Column(name = "post_Code", columnDefinition = "NVARCHAR2(10)")
    private String postCode;

    @JsonProperty("وضعیت تاهل")
    @NonNull
//    @NotBlank(message = "وضعیت تاهل انتخاب نشده")
    @Column(name = "marital_status")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @JsonProperty("تلفن همراه")
    @NonNull
    @NotBlank(message = "تلفن همراه وارد نشده")
    @Pattern(regexp = "^\\d{11}$", message = "لطفا از اعداد انگلیسی استفاده کنید")
    @Column(name = "phone_no",columnDefinition = "NVARCHAR2(11)")
    private String phoneNo;

    @JsonProperty("تلفن ثابت")
    @NonNull
    @NotBlank(message = "تلفن ثابت وارد نشده")
    @Pattern(regexp = "^\\d{11}$", message = "لطفا از اعداد انگلیسی استفاده کنید")
    @Column(name = "landLine_no",columnDefinition = "NVARCHAR2(11)")
    private String landLineNo;

    @JsonProperty("تعداد فرزند")
    @NonNull
//    @NotBlank(message = "تعداد فرزندان وارد نشده")
    @Column(name = "children", columnDefinition = "NUMBER(1)")
    private Short children;

    @JsonProperty("ایمیل")
    @NonNull
    @Email
    @Pattern(regexp = "/[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{10,40}/",message = "ایمیل به درستی وارد نشده")
    @Column(name = "email",columnDefinition = "NVARCHAR2(40)")
    private String email;

    @JsonProperty("عکس کارت ملی")
    @NonNull
//    @NotBlank
    @OneToOne(cascade = CascadeType.ALL)
    private Attachment nationalCardPicture;

    @JsonProperty("عکس پرسنلی")
    @NonNull
//    @NotBlank
    @OneToOne(cascade = CascadeType.ALL)
    private Attachment personnelPicture;

    @JsonProperty("تاریخ ثبت نام")
    private Timestamp signUp;

    @JsonProperty("وضعیت")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "NVARCHAR2(10)")
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    private Employment employment;


    @PrePersist
    public void setSignUpAndStatus(){
        signUp = Timestamp.valueOf(LocalDateTime.now());
        status = Status.Active;
    }

    public Person(
            Long id,
            @NonNull String firstName,
            @NonNull String lastName,
            @NonNull String nationalCode,
            @NonNull String birthCertificateCode,
            @NonNull LocalDate birthday,
            @NonNull Gender gender,
            @NonNull Integer age,
            @NonNull String father,
            @NonNull String state,
            @NonNull String city,
            @NonNull String address,
            @NonNull String postCode,
            @NonNull MaritalStatus maritalStatus,
            @NonNull String phoneNo,
            @NonNull String landLineNo,
            @NonNull Short children,
            @NonNull String email,
            @NonNull Attachment nationalCardPicture,
            @NonNull Attachment personnelPicture){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.birthCertificateCode = birthCertificateCode;
        this.birthday = birthday;
        this.gender = gender;
        this.age = age;
        this.father = father;
        this.state = state;
        this.city = city;
        this.address = address;
        this.postCode = postCode;
        this.maritalStatus = maritalStatus;
        this.phoneNo = phoneNo;
        this.landLineNo = landLineNo;
        this.children = children;
        this.email = email;
        this.nationalCardPicture = nationalCardPicture;
        this.personnelPicture = personnelPicture;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
