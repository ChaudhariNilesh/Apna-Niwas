package com.example.apnaniwas.ui.payment.mybills;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaniwas.R;

import java.util.ArrayList;

public class MyBills extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recyclerview_layout, container, false);



        ArrayList<MyBillsViewModel> pendBillData = new ArrayList<MyBillsViewModel>();

        for(int i=0;i<pendingBills.id.length; i++)
        {
            pendBillData.add(new MyBillsViewModel(
                    pendingBills.billTitle[i],
                    pendingBills.billDueDate[i],
                    pendingBills.billAmount[i]
            ));

        }

        RecyclerView recyclerView =  view.findViewById(R.id.recyclerView);
        MyBillsAdapter adapter = new MyBillsAdapter(getContext(),pendBillData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    public static class pendingBills{
        static String[] billTitle={"Electricity Bill","Water Bill","Maintenance Bill","Newspaper Bill","Cable Bill","Misc. Bill"};
        static String[] billDueDate={"01-jan-2020","01-jan-201","01-jan-2020","01-jan-201","01-jan-2020","01-jan-201"};
        static String[] billAmount ={"4500","1500","1200","500","900","120"};
        static Integer[] id ={0,1,2,3,4,5};
    }


}
