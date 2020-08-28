package com.example.apnaniwas.ui.services;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaniwas.R;

import java.util.ArrayList;

public class ServicesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_services, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<ServicesViewModel> servicesDataList = new ArrayList<>();

        for(int i = 0; i< ServicesFragment.services.id.length; i++)
        {
            servicesDataList.add(new ServicesViewModel(
                    ServicesFragment.services.mServiceName[i],
                    ServicesFragment.services.mServiceNum,
                    ServicesFragment.services.mServiceImg,
                    ServicesFragment.services.id[i]
            ));
        }
        RecyclerView.Adapter adapter = new ServicesFragmentAdapter(getContext(), servicesDataList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public static class services{
        static String[] mServiceName = {"Plumber Service","Electrician Service","Security Service","Gas Service",
                "Garbage Service","Complain","Gas Service","Garbage Service","Complain Service"};
        static Integer mServiceNum = R.drawable.blue_call;
        static Integer mServiceImg = R.drawable.circle_image;
        static Integer[] id ={0,1,2,3,4,5,6,7,8};
    }
}


