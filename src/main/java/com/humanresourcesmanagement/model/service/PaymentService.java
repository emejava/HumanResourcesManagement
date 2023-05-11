package com.humanresourcesmanagement.model.service;

import com.humanresourcesmanagement.model.entity.Log;
import com.humanresourcesmanagement.model.entity.Payment;
import com.humanresourcesmanagement.model.entity.User;
import com.humanresourcesmanagement.model.entity.enums.Action;
import com.humanresourcesmanagement.model.entity.enums.Status;
import com.humanresourcesmanagement.model.repository.CrudRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentService {
    //  ---------SINGLETON---------------------------------------------------------
    private static final PaymentService paymentService = new PaymentService();

    private PaymentService() {
    }

    public static PaymentService getPaymentService() {
        return paymentService;
    }

    //  ---------INSERT-DATA--------------------------------------------------------
    public Payment save(Payment payment, User user) throws Exception {
        try (CrudRepository<Payment, Long> paymentDa = new CrudRepository<>()) {
            payment = paymentDa.save(payment);
            Log log = new Log(Action.Insert, payment.toString(), user);
            LogService.getLogService().save(log);
            return payment;
        }
    }

    //  ---------UPDATE-DATA--------------------------------------------------------
    public Payment edit(Payment payment, User user) throws Exception {
        try (CrudRepository<Payment, Long> paymentDa = new CrudRepository<>()) {
            payment = paymentDa.edit(payment);
            Log log = new Log(Action.Update, payment.toString(), user);
            LogService.getLogService().save(log);
            return payment;
        }
    }

    //  ---------DELETE-------------------------------------------------------------
    public Payment delete(Long id, User user) throws Exception {
        try (CrudRepository<Payment, Long> paymentDa = new CrudRepository<>()) {
            Payment payment = paymentDa.delete(Payment.class, id);
            Log log = new Log(Action.Delete, payment.toString(), user);
            LogService.getLogService().save(log);
            return payment;
        }
    }

    //  ---------LOGICAL-DELETE-----------------------------------------------------
    public Payment deactivate(Long id, User user) throws Exception {
        try (CrudRepository<Payment, Long> paymentDa = new CrudRepository<>()) {
            Payment payment = paymentDa.findById(Payment.class, id);
            payment.setStatus(Status.Inactive);
            paymentDa.edit(payment);
            Log log = new Log(Action.Deactivate, payment.toString(), user);
            LogService.getLogService().save(log);
            return payment;
        }
    }

    //  ---------ACTIVE-STATUS------------------------------------------------------
    public Payment activate(Long id, User user) throws Exception {
        try (CrudRepository<Payment, Long> paymentDa = new CrudRepository<>()) {
            Payment payment = paymentDa.findById(Payment.class, id);
            payment.setStatus(Status.Active);
            paymentDa.edit(payment);
            Log log = new Log(Action.Activate, payment.toString(), user);
            LogService.getLogService().save(log);
            return payment;
        }
    }

    //  ---------SELECT-ALL---------------------------------------------------------
    public List<Payment> findAll(User user) throws Exception {
        try (CrudRepository<Payment, Long> paymentDa = new CrudRepository<>()) {
            List<Payment> paymentList = paymentDa.findAll(Payment.class);
            Log log = new Log(Action.Search, "All Payments", user);
            LogService.getLogService().save(log);
            return paymentList;
        }
    }

    //  ---------SELECT-ALL-ACTIVE---------------------------------------------------
    public List<Payment> findAllActive(User user) throws Exception {
        try (CrudRepository<Payment, Long> paymentDa = new CrudRepository<>()) {
            Map<String,Object> params = new HashMap<>();
            Status status = Status.Active;
            params.put("status",status);
            List<Payment> paymentList = paymentDa.executeQuery("payment.findAllActive", params);
            Log log = new Log(Action.Search, "All Active Payments", user);
            LogService.getLogService().save(log);
            return paymentList;
        }
    }

    //  ---------SELECT-BY-ID-------------------------------------------------------
    public Payment findById(Long id, User user) throws Exception {
        try (CrudRepository<Payment, Long> paymentDa = new CrudRepository<>()) {
            Payment payment = paymentDa.findById(Payment.class, id);
            Log log = new Log(Action.Search, payment.toString(), user);
            LogService.getLogService().save(log);
            return payment;
        }
    }

    //  ---------SELECT-BY-NATIONAL-CODE----------------------------------------------
    public List<Payment> findByNationalCode(String nationalCode, User user) throws Exception {
        try (CrudRepository<Payment, Long> paymentDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("nationalCode", nationalCode);
            List<Payment> payments = paymentDa.executeQuery("payment.findByNationalCode", params);
            Log log = new Log(Action.Search, "Payments By NationalCode", user);
            LogService.getLogService().save(log);
            return payments;
        }
    }

    //  ---------SELECT-BY-PERSONNEL-CODE---------------------------------------------
    public List<Payment> findByPersonnelCode(Long personnelCode, User user) throws Exception {
        try (CrudRepository<Payment, Long> paymentDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("personnelCode", personnelCode);
            List<Payment> payments = paymentDa.executeQuery("payment.findByPersonnelCode", params);
            Log log = new Log(Action.Search, "Payments By PersonnelCode", user);
            LogService.getLogService().save(log);
            return payments;
        }
    }

    //  ---------SELECT-BY-YEAR------------------------------------------------------
    public List<Payment> findByYear(Year year, User user) throws Exception {
        try (CrudRepository<Payment, Long> paymentDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("year", year);
            List<Payment> payments = paymentDa.executeQuery("payment.findByYear", params);
            Log log = new Log(Action.Search, "Payments By Year", user);
            LogService.getLogService().save(log);
            return payments;
        }
    }
    //  ---------SELECT-BY-YEAR-AND-MONTH---------------------------------------------
    public Payment findByYearAndMonth(Year year, LocalDate from, LocalDate till, User user) throws Exception {
        try (CrudRepository<Payment, Long> paymentDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("year", year);
            params.put("from", from);
            params.put("till", till);
            List<Payment> payments = paymentDa.executeQuery("payment.findByYearAndMonth", params);
            Log log = new Log(Action.Search, payments.toString(), user);
            LogService.getLogService().save(log);
            if (payments.size() == 1) {
                return payments.get(0);
            } else {
                return null;
            }
        }
    }

    //  ---------SELECT-BY-END-TIME-OF-PAYMENT--------------------------------------------
    public Payment findByPersonnelCodeAndEndTime(Long personnelCode,LocalDate till, User user) throws Exception {
        try (CrudRepository<Payment, Long> paymentDa = new CrudRepository<>()) {
            Map<String, Object> params = new HashMap<>();
            params.put("personnelCode", personnelCode);
            params.put("till", till);
            List<Payment> payments = paymentDa.executeQuery("payment.findByPersonnelCodeAndEndTime", params);
            Log log = new Log(Action.Search, payments.toString(), user);
            LogService.getLogService().save(log);
            if (payments.size() == 1) {
                return payments.get(0);
            } else {
                return null;
            }
        }
    }
}
