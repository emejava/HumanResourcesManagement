package com.humanresourcesmanagement.model.entity;


import com.fasterxml.jackson.annotation.*;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.ShiftWork;
import com.humanresourcesmanagement.model.entity.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor

@Entity(name = "recruitmentEntity")
@Table(name = "tb_recruitment")
@NamedQueries({
        @NamedQuery(
                name = "recruitment.findByFieldOfStudy",
                query = "SELECT oo from recruitmentEntity oo where oo.fieldOfStudy=:fieldOfStudy"),
        @NamedQuery(
                name = "recruitment.findByUniversity",
                query = "SELECT oo from recruitmentEntity oo where oo.educationPlace LIKE educationPlace"),
        @NamedQuery(
                name = "recruitment.findByStatus",
                query = "SELECT oo from recruitmentEntity oo where oo.status=:status")})
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("کد")
    private Long id;

    @JsonProperty("شخص")
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private Person person;

    @JsonProperty("تحصیلات")
    @NonNull
    @NotBlank(message = "میزان تحصیلات انتخاب نشده")
    @Column(name = "Education", columnDefinition = "NVARCHAR2(32)")
    private String education;

    @JsonProperty("رشته تحصیلی")
    @NonNull
    @NotBlank(message = "رشته ی تحصیلی انتخاب نشده")
    @Column(name = "field_Of_Study", columnDefinition = "NVARCHAR2(30)")
    private String fieldOfStudy;

    @JsonProperty("مرکز تحصیلی")
    @NonNull
    @NotBlank(message = "نام دانشگاه را وارد کنید")
    @Pattern(regexp = "[آ-ی\\s]*", message = "لطفا از حروف فارسی استفاده کنید")
    @Column(name = "education_place", columnDefinition = "NVARCHAR2(40)")
    private String educationPlace;

    @JsonProperty("تجربه کاری")
    @NonNull
    @Pattern(regexp = "[آ-ی\\s]*", message = "لطفا از حروف فارسی استفاده کنید")
    @Column(name = "work_experience", columnDefinition = "NVARCHAR2(100)")
    private String workExperience;

    @JsonProperty("کار قبلی")
    @NonNull
    @Pattern(regexp = "[آ-ی\\s]*", message = "لطفا از حروف فارسی استفاده کنید")
    @Column(name = "last_job", columnDefinition = "NVARCHAR2(20)")
    private String lastJob;

    @JsonProperty("دلیل خروج از محل کار قبلی")
    @NonNull
    @Pattern(regexp = "[آ-ی\\s]*", message = "لطفا از حروف فارسی استفاده کنید")
    @Column(name = "last_jb_exit_reason", columnDefinition = "NVARCHAR2(100)")
    private String lastJobExitReason;

    @JsonProperty("آدرس محل کار قبلی")
    @NonNull
    @Pattern(regexp = "[آ-ی\\s]*", message = "لطفا از حروف فارسی استفاده کنید")
    @Column(name = "last_job_address", columnDefinition = "NVARCHAR2(255)")
    private String lastJobAddress;

    @JsonProperty("شماره تماس کار قبلی")
    @NonNull
    @Pattern(regexp = "^\\d{11}$", message = "لطفا از اعداد انگلیسی استفاده کنید")
    @Column(name = "last_job_no", columnDefinition = "NVARCHAR2(11)")
    private String lastJobNo;

    @JsonProperty("هدف شغلی")
    @NonNull
    @NotBlank(message = "هدف شغلی وارد نشده")
    @Pattern(regexp = "[آ-ی\\s]*", message = "لطفا از حروف فارسی استفاده کنید")
    @Column(name = "job_goal", columnDefinition = "NVARCHAR2(100)")
    private String jobObjective;

    @JsonProperty("شیفت مورد نظر")
    @NonNull
//    @NotBlank(message = "شیفت کاری را انتخاب کنید")
    @Column(name = "shift_work", columnDefinition = "NVARCHAR2(15)")
    @Enumerated(EnumType.STRING)
    private ShiftWork shiftWork;

    @JsonProperty("حقوق درخواستی")
    @NonNull
    @Pattern(regexp = "^\\d{8}$", message = "لطفا از اعداد انگلیسی استفاده کنید")
    @NotBlank(message = "حقوق درخواستی وارد نشده")
    @Column(name = "requested_salary", columnDefinition = "NVARCHAR2(8)")
    private String requestedSalary;


    @JsonProperty("وضعیت")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "NVARCHAR2(10)")
    private Status status;

    public Recruitment(
            Long id,
            @NonNull Person person,
            @NonNull String education,
            @NonNull String fieldOfStudy,
            @NonNull String educationPlace,
            @NonNull String workExperience,
            @NonNull String lastJob,
            @NonNull String lastJobExitReason,
            @NonNull String lastJobAddress,
            @NonNull String lastJobNo,
            @NonNull String jobObjective,
            @NonNull ShiftWork shiftWork,
            @NonNull String requestedSalary) {
        this.id = id;
        this.person = person;
        this.education = education;
        this.fieldOfStudy = fieldOfStudy;
        this.educationPlace = educationPlace;
        this.workExperience = workExperience;
        this.lastJob = lastJob;
        this.lastJobExitReason = lastJobExitReason;
        this.lastJobAddress = lastJobAddress;
        this.lastJobNo = lastJobNo;
        this.jobObjective = jobObjective;
        this.shiftWork = shiftWork;
        this.requestedSalary = requestedSalary;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
