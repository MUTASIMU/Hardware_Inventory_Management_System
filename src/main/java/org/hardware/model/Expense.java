package org.hardware.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Expense {
    public enum PaymentMethod {
        CASH,
        MOBILE_MONEY,
        BANK_TRANSFER
    }

    private int expenseId;
    private String expenseNo;
    private int expenseCategoryId;
    private String categoryName;
    private String description;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private int userId;
    private String recordedBy;
    private PaymentMethod paymentMethod = PaymentMethod.CASH;
    private LocalDateTime createdAt;

    public int getExpenseId() {
        return expenseId;
    }
    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }
    public String getExpenseNo() {
        return expenseNo;
    }
    public void setExpenseNo(String expenseNo) {
        this.expenseNo = expenseNo;
    }
    public int getExpenseCategoryId() {
        return expenseCategoryId;
    }
    public void setExpenseCategoryId(int expenseCategoryId) {
        this.expenseCategoryId = expenseCategoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public LocalDate getExpenseDate() {
        return expenseDate;
    }
    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getRecordedBy() {
        return recordedBy;
    }
    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
