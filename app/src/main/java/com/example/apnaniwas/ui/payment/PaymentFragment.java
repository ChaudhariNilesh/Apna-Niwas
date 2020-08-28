package com.example.apnaniwas.ui.payment;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import com.example.apnaniwas.R;

//import app.apnaniwas.ui.payment.payment_tabs.payment_tab_activity;

public class PaymentFragment extends Fragment {
    Activity context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        context=getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        TabLayout tabLayout =  view.findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setText("My Bills"));
        tabLayout.addTab(tabLayout.newTab().setText("Payment History"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager =view.findViewById(R.id.payment_viewpager);
        PaymentTabAdapter tabsAdapter = new PaymentTabAdapter(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
}


