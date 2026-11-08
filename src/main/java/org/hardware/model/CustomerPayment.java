package org.hardware.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CustomerPayment {
    public enum PaymentMethod { CASH, MOBILE_MONEY, BANK_TRANSFER, CHEQUE }

    private int paymentId;
    private String paymentNo;
    private int customerId;
    private String customerName;
    private Integer saleId;      // optional link to invoice
    private String saleNo;
    private int userId;
    private String receivedBy;
    private LocalDateTime paymentDate;
    private BigDecimal amount;
    private PaymentMethod paymentMethod = PaymentMethod.CASH;
    private String notes;

    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    public String getPaymentNo() {
        return paymentNo;
    }
    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }
    public int getCustomerId() { return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId; }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public Integer getSaleId() {
        return saleId;
    }
    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }
    public String getSaleNo() {
        return saleNo;
    }
    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getReceivedBy() {
        return receivedBy;
    }
    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
