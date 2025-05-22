
package com.vms.DTO;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

/**
 * Data Transfer Object (DTO) for Payment details.
 */
public class PaymentDTO {

    private int paymentId; // Unique identifier for the payment

    @NotNull(message = "{payment.mode.notnull}")
    @Size(min = 1, message = "{payment.mode.size}")
    @Pattern(regexp = "^(credit card|debit card|net banking|upi)$", message = "{payment.mode.pattern}")
    private String paymentMode; // Mode of payment (Credit Card, Debit Card, etc.)

    @NotNull(message = "{payment.date.notnull}")
    private LocalDate paymentDate; // Date of payment transaction

    private String paymentStatus; // Payment status (Pending, Completed, Failed)

    @Min(value = 1, message = "{payment.bookingId.min}")
    @Max(value = 10000, message = "{payment.bookingId.max}")
    private int bookingId; // Associated booking ID

    @NotNull(message = "{payment.vehicleId.notnull}")
    @Min(value = 1, message = "{payment.vehicleId.min}")
    @Max(value = 10000, message = "{payment.vehicleId.max}")
    private int vehicleId; // Associated vehicle ID

    @Min(value = 100, message = "{payment.totalCost.min}")
    private double totalCost; // Total amount paid for the booking

    @NotNull(message = "{payment.transactionId.notnull}")
    @Size(min = 1, message = "{payment.transactionId.size}")
    @Pattern(regexp = "^TX\\d{9}$", message = "{payment.transactionId.pattern}")
    private String transactionId; // Unique transaction identifier (e.g., TX123456789)

    // Default constructor
    public PaymentDTO() {
    }

    /**
     * Constructor without paymentId (typically for request payloads).
     */
    public PaymentDTO(String paymentMode, LocalDate paymentDate, String paymentStatus,
                      int bookingId, int vehicleId, double totalCost, String transactionId) {
        this.paymentMode = paymentMode;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.bookingId = bookingId;
        this.vehicleId = vehicleId;
        this.totalCost = totalCost;
        this.transactionId = transactionId;
    }

    /**
     * Constructor with paymentId (typically for response objects).
     */
    public PaymentDTO(int paymentId, String paymentMode, LocalDate paymentDate, String paymentStatus,
                      int bookingId, int vehicleId, double totalCost, String transactionId) {
        this.paymentId = paymentId;
        this.paymentMode = paymentMode;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.bookingId = bookingId;
        this.vehicleId = vehicleId;
        this.totalCost = totalCost;
        this.transactionId = transactionId;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}








