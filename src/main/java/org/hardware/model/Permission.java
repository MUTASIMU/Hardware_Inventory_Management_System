package org.hardware.model;

public class Permission {
    private int permissionId;
    private String permissionCode;
    private String module;
    private String description;

    public Permission() {}

    public int getPermissionId() {
        return permissionId;
    }
    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
    public String getPermissionCode() {
        return permissionCode;
    }
    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
    public String getModule() {
        return module;
    }
    public void setModule(String module) {
        this.module = module;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
