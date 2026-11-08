package org.hardware.model;

import java.math.BigDecimal;

public class PurchaseItem {
    private int purchaseItemId;
    private int purchaseId;
    private int productId;
    private String productName;
    private String sku;
    private int quantity;
    private BigDecimal unitCost;
    private BigDecimal lineTotal;

    public PurchaseItem() {}

    public PurchaseItem(int productId, int quantity, BigDecimal unitCost) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.lineTotal = unitCost.multiply(BigDecimal.valueOf(quantity));
    }

    public int getPurchaseItemId() {
        return purchaseItemId;
    }
    public void setPurchaseItemId(int purchaseItemId) {
        this.purchaseItemId = purchaseItemId;
    }
    public int getPurchaseId() {
        return purchaseId;
    }
    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
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
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getUnitCost() {
        return unitCost;
    }
    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }
    public BigDecimal getLineTotal() {
        return lineTotal;
    }
    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }
}
