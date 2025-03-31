package com.rest.springapp.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID payment_id;

    private UUID appointment_id; // No FK, just a UUID

    private BigDecimal amount;

    private String payment_method;

    @Temporal(TemporalType.DATE)
    private Date payment_date;

    private String status;

    // Default Constructor
    public Payment() {
    }

    // Parameterized Constructor
    public Payment(UUID payment_id, UUID appointment_id, BigDecimal amount, String payment_method, Date payment_date, String status) {
        this.payment_id = payment_id;
        this.appointment_id = appointment_id;
        this.amount = amount;
        this.payment_method = payment_method;
        this.payment_date = payment_date;
        this.status = status;
    }

    // Getters and Setters
    public UUID getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(UUID payment_id) {
        this.payment_id = payment_id;
    }

    public UUID getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(UUID appointment_id) {
        this.appointment_id = appointment_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
