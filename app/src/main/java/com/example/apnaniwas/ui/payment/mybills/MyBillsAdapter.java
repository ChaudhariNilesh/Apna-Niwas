package com.example.apnaniwas.ui.payment.mybills;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaniwas.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyBillsAdapter extends RecyclerView.Adapter<MyBillsAdapter.ViewHolder> {

    private ArrayList<MyBillsViewModel> pendBill;
    private Context context;
    public MyBillsAdapter(Context context, ArrayList<MyBillsViewModel> listdata) {
        this.context = context;
        this.pendBill = listdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.tab_mybills_layout,parent,false);
        ViewHolder holder = new ViewHolder(listItem);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final MyBillsViewModel data = pendBill.get(position);
        final TextView pending_bill_title = holder.pending_bill_title;
        TextView pending_bill_dueDate = holder.pending_bill_dueDate;
        final TextView pending_bill_amount = holder.pending_bill_amount;
        pending_bill_title.setText(pendBill.get(position).getTitle());
        pending_bill_dueDate.setText(pendBill.get(position).getDueDate());
        pending_bill_amount.setText(pendBill.get(position).getAmount());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),"CLCIKED "+position,Toast.LENGTH_LONG).show();


                Intent intent = new Intent(context,PaymentHandler.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_BILLTILE",pendBill.get(position).getTitle());
                extras.putString("EXTRA_BILLAMT",pendBill.get(position).getAmount());
                extras.putString("EXTRA_BILLDUEDATE",pendBill.get(position).getDueDate());
                intent.putExtras(extras);
                context.startActivity(intent);

            }
        });

    }
    @Override
    public int getItemCount() {
        return pendBill.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView pending_bill_title;
        TextView pending_bill_dueDate;
        TextView pending_bill_amount;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.pending_bill_title = itemView.findViewById(R.id.pending_bill_title);
            this.pending_bill_dueDate = itemView.findViewById(R.id.pending_bill_dueDate);
            this.pending_bill_amount = itemView.findViewById(R.id.pending_bill_amount);
            this.cardView = itemView.findViewById(R.id.CV_bill_pending);
        }
    }

    // FOR DATE AND TIME
    public String getCurrentDate() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy ' \n ' h:mm:ss ");
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }
}

