package org.hardware.model;

import java.math.BigDecimal;

public class SaleItem {
    private int saleItemId;
    private int saleId;
    private int productId;
    private String productName;
    private String sku;
    private String unitSymbol;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal lineTotal;
    private int availableStock; // transient, used only in cart UI, not persisted

    public SaleItem() {}

    public SaleItem(int productId, String productName, String sku, String unitSymbol,
                    int quantity, BigDecimal unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.sku = sku;
        this.unitSymbol = unitSymbol;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public int getSaleItemId() {
        return saleItemId;
    }
    public void setSaleItemId(int saleItemId) {
        this.saleItemId = saleItemId;
    }
    public int getSaleId() {
        return saleId;
    }
    public void setSaleId(int saleId) {
        this.saleId = saleId;
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
    public String getUnitSymbol() {
        return unitSymbol;
    }
    public void setUnitSymbol(String unitSymbol) {
        this.unitSymbol = unitSymbol;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        recalculate();
    }
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        recalculate();
    }
    public BigDecimal getLineTotal() { return lineTotal; }
    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }
    public int getAvailableStock() {
        return availableStock;
    }
    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    private void recalculate() {
        if (unitPrice != null) {
            //Converting quantity to BigDecimal first
            this.lineTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }
}
