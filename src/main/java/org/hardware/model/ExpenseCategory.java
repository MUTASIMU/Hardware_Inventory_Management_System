package org.hardware.model;

public class ExpenseCategory {
    private int expenseCategoryId;
    private String categoryName;

    public ExpenseCategory() {}
    public ExpenseCategory(int id, String name) {
        this.expenseCategoryId = id;
        this.categoryName = name;
    }

    public int getExpenseCategoryId() {
        return expenseCategoryId;
    }
    public void setExpenseCategoryId(int expenseCategoryId) {
        this.expenseCategoryId = expenseCategoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
