package org.hardware.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sale {
    public enum SaleType { CASH, CREDIT }
    public enum Status { COMPLETED, CANCELLED }

    private int saleId;
    private String saleNo;
    private Integer customerId;     // nullable - anonymous walk-in
    private String customerName;
    private String customerType;
    private int userId;
    private String cashierName;
    private LocalDateTime saleDate;

    private SaleType saleType = SaleType.CASH;
    private BigDecimal subtotal = BigDecimal.ZERO;
    private BigDecimal discount = BigDecimal.ZERO;
    private BigDecimal totalAmount = BigDecimal.ZERO;
    private BigDecimal amountPaid = BigDecimal.ZERO;
    private BigDecimal balanceDue = BigDecimal.ZERO;
    private BigDecimal changeGiven = BigDecimal.ZERO;
    private Status status = Status.COMPLETED;
    private LocalDateTime createdAt;
    private List<SaleItem> items = new ArrayList<>();

    public int getSaleId() {
        return saleId;
    }
    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }
    public String getSaleNo() {
        return saleNo;
    }
    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerType() {
        return customerType;
    }
    public void setCustomerType(String customerName) {
        this.customerName = customerType;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getCashierName() {
        return cashierName;
    }
    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }
    public LocalDateTime getSaleDate() {
        return saleDate;
    }
    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }
    public SaleType getSaleType() {
        return saleType;
    }
    public void setSaleType(SaleType saleType) {
        this.saleType = saleType;
    }
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    public BigDecimal getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public BigDecimal getAmountPaid() {
        return amountPaid;
    }
    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }
    public BigDecimal getBalanceDue() {
        return balanceDue;
    }
    public void setBalanceDue(BigDecimal balanceDue) {
        this.balanceDue = balanceDue;
    }
    public BigDecimal getChangeGiven() {
        return changeGiven;
    }
    public void setChangeGiven(BigDecimal changeGiven) {
        this.changeGiven = changeGiven;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public List<SaleItem> getItems() {
        return items;
    }
    public void setItems(List<SaleItem> items) {
        this.items = items;
    }
}
