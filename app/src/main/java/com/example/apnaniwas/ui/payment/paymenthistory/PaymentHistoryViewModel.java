package com.example.apnaniwas.ui.payment.paymenthistory;

public class PaymentHistoryViewModel {

    String title;
    String date;
    String amount;

    public PaymentHistoryViewModel(String title, String date, String amount) {
        this.title = title;
        this.date = date;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle() {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate() {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(){
        this.amount = amount;
    }
}
