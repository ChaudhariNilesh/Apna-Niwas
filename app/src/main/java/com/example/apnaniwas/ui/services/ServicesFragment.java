package com.example.apnaniwas.ui.services;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaniwas.R;

import java.util.ArrayList;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class ServicesFragment extends Fragment implements ServicesFragmentAdapter.CallClickEvent {
    int num;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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
        RecyclerView.Adapter adapter = new ServicesFragmentAdapter(getContext(), servicesDataList,this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void numberToPhone(int number) {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:8469647222")); //change this with number
            startActivity(callIntent);
        } catch (ActivityNotFoundException activityException) {
            Log.e("Calling a Phone Number", "Call failed", activityException);
        }
    }


    public static class services{
        static String[] mServiceName = {"Plumber Service","Electrician Service","Security Service","Gas Service",
                "Garbage Service","Complain","Gas Service","Garbage Service","Complain Service"};
        static Integer mServiceNum = R.drawable.blue_call;
        static Integer mServiceImg = R.drawable.circle_image;
        static Integer[] id ={0,1,2,3,4,5,6,7,8};
    }



}


