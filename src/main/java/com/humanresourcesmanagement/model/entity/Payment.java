package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor

@Entity(name = "paymentEntity")
@Table(name = "tb_payment")
@NamedQueries({
        @NamedQuery(
                name = "payment.findAllActive",
                query = "SELECT p FROM paymentEntity p WHERE p.status =:status"),
        @NamedQuery(
                name = "payment.findByNationalCode",
                query = "SELECT p FROM paymentEntity p WHERE p.person.nationalCode =:nationalCode"),
        @NamedQuery(
                name = "payment.findByPersonnelCode",
                query = "SELECT p FROM paymentEntity p WHERE p.person.employment.personnelCode =:personnelCode"),
        @NamedQuery(
                name = "payment.findByYear",
                query = "SELECT p FROM paymentEntity p WHERE p.year =:year"),
        @NamedQuery(
                name = "payment.findByYearAndMonth",
                query = "SELECT p FROM paymentEntity p WHERE p.year =:year AND p.from =:from AND p.till =:till"),
        @NamedQuery(
                name = "payment.findByPersonnelCodeAndEndTime",
                query = "SELECT p FROM paymentEntity p WHERE p.person.employment.personnelCode =:personnelCode AND p.till =:till")
})
public class Payment {
    @Id
    @JsonProperty("کد")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("سال")
    @NonNull
//    @NotBlank(message = "سال وارد نشده")
    private Year year;

    @JsonProperty("از تاریخ")
    @NonNull
//    @NotBlank(message = "تاریخ شروع وارد نشده")
    private LocalDate from;

    @JsonProperty("تا تاریخ")
    @NonNull
//    @NotBlank(message = "تاریخ پایان وارد نشده")
    private LocalDate till;

    @JsonProperty("شخص")
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private Person person;

    @JsonProperty("حقوق پایه")
    @NonNull
//    @NotBlank(message = "حقوق پایه وارد نشده")
//    @Pattern(regexp = "^\\d{10}$",message = "از اعداد انگلیسی استفاده کنید")
    @Column(name="basic_Salary",columnDefinition = "NUMBER(10)")
    private Double basicSalary;

    @JsonProperty("اضافه کاری")
    @NonNull
//    @NotBlank(message = "اضافه کاری وارد نشده")
    @Column(name="over_Time")
    private Long overTime;

    @JsonProperty("غیبت")
    @NonNull
//    @NotBlank(message = "زمان غیبت وارد نشده")
    @Column(name="absence_time")
    private Long absenceTime;

    @JsonProperty("کارکرد")
    @NonNull
//    @NotBlank(message = "کارکد وارد نشده")
    @Column(name="operation_time")
    private Long operationTime;

    @JsonProperty("مبلغ اضافه کاری")
    @NonNull
//    @NotBlank
    @Column(name="over_time_count",columnDefinition = "NUMBER(10)")
    private Double overTimePayCount;

    @JsonProperty("کسری غیبت")
    @NonNull
//    @NotBlank
//    @Pattern(regexp = "^\\d$")
    @Column(name="absence_count",columnDefinition = "NUMBER(10)")
    private Double absencePayCount;

    @JsonProperty("ملبغ کارکرد")
    @NonNull
//    @NotBlank
//    @Pattern(regexp = "^\\d$")
    @Column(name="operation_count",columnDefinition = "NUMBER(10)")
    private Double operationPayCount;

    @JsonProperty("حق مسکن")
    @NonNull
//    @NotBlank(message = "حق مسکن را وارد کنید")
//    @Pattern(regexp = "^\\d{10}$",message = "از اعداد انگلیسی استفاده کنید")
    @Column(name="housing",columnDefinition = "NUMBER(10)")
    private Double housing;

    @JsonProperty("مرخصی")
    @NonNull
    @OneToMany(cascade = CascadeType.ALL)
    private List<LeaveDays> leaveDays;

    @JsonProperty("مبلغ کسر بابت مرخصی")
    @NonNull
//    @Pattern(regexp = "^\\d{10}$",message = "از اعداد انگلیسی استفاده کنید")
    @Column(name="leave_days_pay_deduction",columnDefinition = "NUMBER(10)")
    private Double leaveDaysPayDeduction;

    @JsonProperty("مزایا")
    @NonNull
//    @Pattern(regexp = "^\\d{10}$",message = "از اعداد انگلیسی استفاده کنید")
    @Column(name="benefits",columnDefinition = "NUMBER(10)")
    private Double benefits;

    @JsonProperty("پاداش مدیرتی")
    @NonNull
//    @Pattern(regexp = "^\\d{10}$",message = "از اعداد انگلیسی استفاده کنید")
    @Column(name="management_bonus",columnDefinition = "NUMBER(10)")
    private Double managementBonus;

    @JsonProperty("حق فرزند")
    @NonNull
//    @NotBlank(message = "حق فرزند وارد نشده")
//    @Pattern(regexp = "^\\d{10}$",message = "از اعداد انگلیسی استفاده کنید")
    @Column(name="children_pay",columnDefinition = "NUMBER(10)")
    private Double childrenPay;

    @JsonProperty("سنوات")
    @NonNull
//    @NotBlank(message = "سنوات وارد نشده")
//    @Pattern(regexp = "^[\\d.]{10}$",message = "از اعداد انگلیسی استفاده کنید")
    @Column(name="severance_pay",columnDefinition = "NUMBER(10)")
    private Double severancePay;

    @JsonProperty("حق بیمه")
    @NonNull
//    @NotBlank(message = "حق بیمه وارد نشده")
//    @Pattern(regexp = "^\\d{10}$",message = "از اعداد انگلیسی استفاده کنید")
    @Column(name="insurance",columnDefinition = "NUMBER(10)")
    private Double insurance;

    @JsonProperty("مالیات")
    @NonNull
//    @NotBlank(message = "مالیات وارد نشده")
//    @Pattern(regexp = "^\\d{10}$",message = "از اعداد انگلیسی استفاده کنید")
    @Column(name="tax",columnDefinition = "NUMBER(10)")
    private Double tax;

    @JsonProperty("کسورات")
//    @NotBlank(message = "کسورات وارد نشده")
//    @Pattern(regexp = "^\\d{10}$",message = "از اعداد انگلیسی استفاده کنید")
    @Column(name="debt",columnDefinition = "NUMBER(10)")
    private Double debt;

    @JsonProperty("مجموع پرداختی")
    @Column(name="total_payment",columnDefinition = "NUMBER(10)")
    private Double totalPayment;

    @JsonProperty("شماره تراکنش")
//    @NotBlank
    @Column(name = "transaction_number",columnDefinition = "NUMBER(15)")
    private Long transactionNumber;

    @JsonProperty("وضعیت")
    @Enumerated(EnumType.STRING)
    @Column(name="status",columnDefinition = "NVARCHAR2(15)")
    private Status status;

    @JsonProperty("تاریخ سند")
    private LocalDateTime submitTime;

    @PrePersist
    private void setDateAndStatusAndCountDebtAndTotalPayment(){
        submitTime = LocalDateTime.now();
        status = Status.Pending;
        debt = absencePayCount + leaveDaysPayDeduction + insurance + tax;
        totalPayment = basicSalary + overTimePayCount + operationPayCount + housing + benefits + managementBonus + childrenPay + severancePay - debt;
    }

    public Payment(Long id, Long transactionNumber,Status status) {
        this.id = id;
        this.transactionNumber = transactionNumber;
        this.status = status;
    }

    public Payment(
            Long id,
            @NonNull Year year,
            @NonNull LocalDate from,
            @NonNull LocalDate till,
            @NonNull Person person,
            @NonNull Double basicSalary,
            @NonNull Long overTime,
            @NonNull Long absenceTime,
            @NonNull Long operationTime,
            @NonNull Double overTimePayCount,
            @NonNull Double absencePayCount,
            @NonNull Double operationPayCount,
            @NonNull Double housing,
            @NonNull List<LeaveDays> leaveDays,
            @NonNull Double leaveDaysPayDeduction,
            @NonNull Double benefits,
            @NonNull Double managementBonus,
            @NonNull Double childrenPay,
            @NonNull Double severancePay,
            @NonNull Double insurance,
            @NonNull Double tax,
            @NonNull Double debt,
            @NonNull Double totalPayment,
            Long transactionNumber,
            Status status) {
        this.id = id;
        this.year = year;
        this.from = from;
        this.till = till;
        this.person = person;
        this.basicSalary = basicSalary;
        this.overTime = overTime;
        this.absenceTime = absenceTime;
        this.operationTime = operationTime;
        this.overTimePayCount = overTimePayCount;
        this.absencePayCount = absencePayCount;
        this.operationPayCount = operationPayCount;
        this.housing = housing;
        this.leaveDays = leaveDays;
        this.leaveDaysPayDeduction = leaveDaysPayDeduction;
        this.benefits = benefits;
        this.managementBonus = managementBonus;
        this.childrenPay = childrenPay;
        this.severancePay = severancePay;
        this.insurance = insurance;
        this.tax = tax;
        this.debt = debt;
        this.totalPayment = totalPayment;
        this.transactionNumber = transactionNumber;
        this.status = status;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
