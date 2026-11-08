package org.hardware.model;

import java.time.LocalDateTime;

public class StockAdjustment {
    public enum AdjustmentType {
        DAMAGE,
        LOSS,
        COUNT_CORRECTION,
        OTHER }

    private int adjustmentId;
    private String adjustmentNo;
    private int productId;
    private String productName;
    private String sku;
    private AdjustmentType adjustmentType;
    private int quantityBefore;
    private int quantityChange;
    private int quantityAfter;
    private String reason;
    private int userId;
    private String requestedBy;
    private Integer approvedBy;
    private String approvedByName;
    private LocalDateTime adjustmentDate;

    public int getAdjustmentId() {
        return adjustmentId;
    }
    public void setAdjustmentId(int adjustmentId) {
        this.adjustmentId = adjustmentId;
    }
    public String getAdjustmentNo() {
        return adjustmentNo;
    }
    public void setAdjustmentNo(String adjustmentNo) {
        this.adjustmentNo = adjustmentNo;
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
    public AdjustmentType getAdjustmentType() {
        return adjustmentType;
    }
    public void setAdjustmentType(AdjustmentType adjustmentType) {
        this.adjustmentType = adjustmentType;
    }
    public int getQuantityBefore() {
        return quantityBefore;
    }
    public void setQuantityBefore(int quantityBefore) {
        this.quantityBefore = quantityBefore;
    }
    public int getQuantityChange() {
        return quantityChange;
    }
    public void setQuantityChange(int quantityChange) {
        this.quantityChange = quantityChange;
    }
    public int getQuantityAfter() {
        return quantityAfter;
    }
    public void setQuantityAfter(int quantityAfter) {
        this.quantityAfter = quantityAfter;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getRequestedBy() {
        return requestedBy;
    }
    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }
    public Integer getApprovedBy() {
        return approvedBy;
    }
    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }
    public String getApprovedByName() {
        return approvedByName;
    }
    public void setApprovedByName(String approvedByName) {
        this.approvedByName = approvedByName;
    }
    public LocalDateTime getAdjustmentDate() {
        return adjustmentDate;
    }
    public void setAdjustmentDate(LocalDateTime adjustmentDate) {
        this.adjustmentDate = adjustmentDate;
    }
}

