package org.hardware.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Purchase {
    public enum Status {
        PENDING,
        CONFIRMED,
        CANCELLED
    }

    private int purchaseId;
    private String purchaseNo;
    private int supplierId;
    private String supplierName;
    private int userId;
    private String userFullName;
    private LocalDateTime purchaseDate;
    private BigDecimal subtotal = BigDecimal.ZERO;
    private BigDecimal discount = BigDecimal.ZERO;
    private BigDecimal totalAmount = BigDecimal.ZERO;
    private BigDecimal amountPaid = BigDecimal.ZERO;
    private Status status = Status.PENDING;
    private String notes;
    private LocalDateTime createdAt;
    private List<PurchaseItem> items = new ArrayList<>();

    public int getPurchaseId() {
        return purchaseId;
    }
    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }
    public String getPurchaseNo() {
        return purchaseNo;
    }
    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = purchaseNo;
    }
    public int getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserFullName() {
        return userFullName;
    }
    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
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
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public List<PurchaseItem> getItems() {
        return items;
    }
    public void setItems(List<PurchaseItem> items) {
        this.items = items;
    }
}
