package org.hardware.model;

import java.time.LocalDateTime;

public class AuditLog {
    private long auditId;
    private Integer userId;
    private String username;
    private String action;
    private String entityType;
    private Integer entityId;
    private String details;
    private String ipAddress;
    private LocalDateTime createdAt;

    public long getAuditId() {
        return auditId;
    }
    public void setAuditId(long auditId) {
        this.auditId = auditId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public String getEntityType() {
        return entityType;
    }
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
    public Integer getEntityId() {
        return entityId;
    }
    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
