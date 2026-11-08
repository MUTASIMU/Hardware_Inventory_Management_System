package org.hardware.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private int productId;
    private String sku;
    private String productName;
    private int categoryId;
    private String categoryName;   // joined for display
    private int unitId;
    private String unitName;       // joined for display
    private String unitSymbol;
    private String description;
    private BigDecimal costPrice = BigDecimal.ZERO;
    private BigDecimal sellingPrice = BigDecimal.ZERO;
    private int reorderLevel = 10;
    private int currentStock;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product() {}

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public int getUnitId() {
        return unitId;
    }
    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }
    public String getUnitName() {
        return unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    public String getUnitSymbol() {
        return unitSymbol;
    }
    public void setUnitSymbol(String unitSymbol) {
        this.unitSymbol = unitSymbol;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getCostPrice() {
        return costPrice;
    }
    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }
    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }
    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    public int getReorderLevel() {
        return reorderLevel;
    }
    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
    public int getCurrentStock() {
        return currentStock;
    }
    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
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
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isLowStock() {
        return currentStock <= reorderLevel && currentStock > 0;
    }
    public boolean isOutOfStock() {
        return currentStock <= 0;
    }

    @Override
    public String toString() {
        return productName + " (" + sku + ")";
    }
}
