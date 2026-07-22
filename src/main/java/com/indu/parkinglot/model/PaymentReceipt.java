package com.indu.parkinglot.model;

import java.time.LocalDateTime;

import com.indu.parkinglot.payment.PaymentStatus;
public class PaymentReceipt {
    private String receiptId;
    private Ticket ticket;
    private Vehicle vehicle;
    private double amount;
    private String paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentTime;

    public PaymentReceipt(String receiptId, Ticket ticket, Vehicle vehicle, double amount, String paymentMethod, PaymentStatus paymentStatus, LocalDateTime paymentTime) {

        this.receiptId = receiptId;
        this.ticket = ticket;
        this.vehicle = vehicle;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentTime = paymentTime;

    }

    public String getReceiptId() {
        return receiptId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }
}