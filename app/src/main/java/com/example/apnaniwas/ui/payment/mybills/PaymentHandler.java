package com.example.apnaniwas.ui.payment.mybills;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apnaniwas.R;



public class PaymentHandler extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Make Payment");

        setContentView(R.layout.layout_payment_handler);
        TextView tvBillTitle = findViewById(R.id.tvBillTitle);
        TextView tvBillDueDate = findViewById(R.id.tvBillDueDate);
        TextView tvBillAmt = findViewById(R.id.tvBillAmt);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        tvBillTitle.setText(extras.getString("EXTRA_BILLTILE"));
        tvBillAmt.setText(extras.getString("EXTRA_BILLAMT"));
        tvBillDueDate.setText(extras.getString("EXTRA_BILLDUEDATE"));


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
