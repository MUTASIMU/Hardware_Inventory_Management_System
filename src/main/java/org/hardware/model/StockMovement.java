package org.hardware.model;

import java.time.LocalDateTime;

public class StockMovement {
    public enum MovementType {
        PURCHASE_IN,
        SALE_OUT,
        ADJUSTMENT_IN,
        ADJUSTMENT_OUT,
        DAMAGE_OUT,
        LOSS_OUT,
        COUNT_CORRECTION
    }
    public enum ReferenceType {
        PURCHASE,
        SALE,
        ADJUSTMENT,
        STOCK_COUNT
    }

    private int movementId;
    private int productId;
    private String productName;
    private String sku;
    private MovementType movementType;
    private ReferenceType referenceType;
    private int referenceId;
    private int quantityChange;
    private int stockBefore;
    private int stockAfter;
    private int userId;
    private String userFullName;
    private LocalDateTime movementDate;
    private String notes;

    public int getMovementId() {
        return movementId;
    }
    public void setMovementId(int movementId) {
        this.movementId = movementId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public MovementType getMovementType() {
        return movementType;
    }
    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }
    public ReferenceType getReferenceType() {
        return referenceType;
    }
    public void setReferenceType(ReferenceType referenceType) {
        this.referenceType = referenceType;
    }
    public int getReferenceId() {
        return referenceId;
    }
    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }
    public int getQuantityChange() { return quantityChange; }
    public void setQuantityChange(int quantityChange) {
        this.quantityChange = quantityChange;
    }
    public int getStockBefore() {
        return stockBefore;
    }
    public void setStockBefore(int stockBefore) {
        this.stockBefore = stockBefore;
    }
    public int getStockAfter() {
        return stockAfter;
    }
    public void setStockAfter(int stockAfter) {
        this.stockAfter = stockAfter;
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
    public LocalDateTime getMovementDate() {
        return movementDate;
    }
    public void setMovementDate(LocalDateTime movementDate) {
        this.movementDate = movementDate;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
