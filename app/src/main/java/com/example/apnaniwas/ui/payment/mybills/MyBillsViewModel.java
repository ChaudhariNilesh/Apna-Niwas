package com.example.apnaniwas.ui.payment.mybills;

public class MyBillsViewModel {

    String title;
    String dueDate;
    String amount;

    public MyBillsViewModel(String title, String dueDate, String amount) {
        this.title = title;
        this.dueDate = dueDate;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}


