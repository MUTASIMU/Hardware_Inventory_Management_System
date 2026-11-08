package org.hardware.model;

public class Unit {
    private int unitId;
    private String unitName;
    private String unitSymbol;
    private boolean active;

    public Unit() {}

    public Unit(String unitName, String unitSymbol) {
        this.unitName = unitName;
        this.unitSymbol = unitSymbol;
        this.active = true;
    }

    public int getUnitId() { return unitId; }
    public void setUnitId(int unitId) { this.unitId = unitId; }
    public String getUnitName() { return unitName; }
    public void setUnitName(String unitName) { this.unitName = unitName; }
    public String getUnitSymbol() { return unitSymbol; }
    public void setUnitSymbol(String unitSymbol) { this.unitSymbol = unitSymbol; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() { return unitName + " (" + unitSymbol + ")"; }
}
