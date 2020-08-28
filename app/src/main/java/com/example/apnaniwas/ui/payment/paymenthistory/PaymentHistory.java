package com.example.apnaniwas.ui.payment.paymenthistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaniwas.R;
import com.example.apnaniwas.ui.payment.mybills.MyBills;
import com.example.apnaniwas.ui.payment.mybills.MyBillsViewModel;

import java.util.ArrayList;

public class PaymentHistory extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recyclerview_layout, container, false);

        ArrayList<PaymentHistoryViewModel> payHistData = new ArrayList<PaymentHistoryViewModel>();
        for(int i = 0; i< billHistory.id.length; i++)
        {
            payHistData.add(new PaymentHistoryViewModel(
                    PaymentHistory.billHistory.billTitle[i],
                    PaymentHistory.billHistory.billPaidDate[i],
                    PaymentHistory.billHistory.billAmount[i]
            ));

        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        PaymentHistoryAdapter adapter = new PaymentHistoryAdapter(payHistData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    public static class billHistory{
        static String[] billTitle={"Electricity Bill","Water Bill","Maintenance Bill","Newspaper Bill","Cable Bill","Misc. Bill"};
        static String[] billPaidDate={"01-jan-2020","01-jan-201","01-jan-2020","01-jan-201","01-jan-2020","01-jan-201"};
        static String[] billAmount={"4500","1500","1200","500","900","120"};
        static Integer[] id ={0,1,2,3,4,5};
    }
}