package org.hardware.model;

import java.time.LocalDateTime;

public class Receipt {
    private int receiptId;
    private String receiptNo;
    private int saleId;
    private LocalDateTime generatedAt;
    private String filePath;

    public Receipt() {}

    public int getReceiptId() {
        return receiptId;
    }
    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }
    public String getReceiptNo() {
        return receiptNo;
    }
    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }
    public int getSaleId() {
        return saleId;
    }
    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }
    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
