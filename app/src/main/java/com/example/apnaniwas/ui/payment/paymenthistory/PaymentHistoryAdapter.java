package com.example.apnaniwas.ui.payment.paymenthistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaniwas.R;

import java.util.ArrayList;

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.ViewHolder> {

    private ArrayList<PaymentHistoryViewModel> payHist;

    public PaymentHistoryAdapter(ArrayList<PaymentHistoryViewModel> listdata) {
        this.payHist = listdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.tab_paymenthistory_layout, parent, false);
        ViewHolder holder = new ViewHolder(listItem);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PaymentHistoryViewModel myData = payHist.get(position);
        TextView bill_history_title = holder.bill_history_title;
        TextView bill_history_date = holder.bill_history_date;
        TextView bill_history_amount = holder.bill_history_amount;


        bill_history_title.setText(payHist.get(position).getTitle());
        bill_history_date.setText(payHist.get(position).getDate());
        bill_history_amount.setText(payHist.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return payHist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bill_history_title;
        TextView bill_history_date;
        TextView bill_history_amount;
        public ViewHolder(View itemView) {
            super(itemView);
            this.bill_history_title = itemView.findViewById(R.id.bill_history_title);
            this.bill_history_date = itemView.findViewById(R.id.bill_history_date);
            this.bill_history_amount = itemView.findViewById(R.id.bill_history_amount);
        }
    }
}

