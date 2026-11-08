package org.hardware.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Customer {
    public enum CustomerType {
        WALK_IN,
        REGISTERED
    }

    private int customerId;
    private String customerName;
    private CustomerType customerType = CustomerType.REGISTERED;
    private String phone;
    private String email;
    private String address;
    private BigDecimal creditLimit = BigDecimal.ZERO;
    private boolean active;
    private LocalDateTime createdAt;

    // Not a DB column - computed on demand from sales/payments (see DebtorService)
    private BigDecimal outstandingBalance = BigDecimal.ZERO;

    public Customer() {}

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public CustomerType getCustomerType() {
        return customerType;
    }
    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public BigDecimal getCreditLimit() {
        return creditLimit;
    }
    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public BigDecimal getOutstandingBalance() {
        return outstandingBalance;
    }
    public void setOutstandingBalance(BigDecimal outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    @Override
    public String toString() { return customerName; }
}
