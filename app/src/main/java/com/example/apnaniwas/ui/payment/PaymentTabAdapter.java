package com.example.apnaniwas.ui.payment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.apnaniwas.ui.payment.mybills.MyBills;
import com.example.apnaniwas.ui.payment.paymenthistory.PaymentHistory;


public class PaymentTabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PaymentTabAdapter(@NonNull FragmentManager fm, int NoofTabs) {
        super(fm);
        this.mNumOfTabs=NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                MyBills bills = new MyBills();
                return bills;
            case 1:
                PaymentHistory history = new PaymentHistory();
                return history;
            default:
                return null;
        }
    }
}

