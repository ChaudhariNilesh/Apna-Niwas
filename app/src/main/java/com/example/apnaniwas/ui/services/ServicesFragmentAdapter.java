package com.example.apnaniwas.ui.services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apnaniwas.R;
import java.util.ArrayList;


public class ServicesFragmentAdapter extends RecyclerView.Adapter<ServicesFragmentAdapter.ViewHolder> {

    private ArrayList<ServicesViewModel> mServiceList;
    private Context context;

    public ServicesFragmentAdapter(Context context, ArrayList<ServicesViewModel> mServiceList) {
        this.context = context;
        this.mServiceList = mServiceList;

    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.servies_row_design, parent, false);
        ViewHolder ViewHolder = new ViewHolder(view);
        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ServicesFragmentAdapter.ViewHolder holder, final int position) {
        final ServicesViewModel data = mServiceList.get(position);

        final TextView mServiceTitle = holder.mServiceTitle;
        final ImageButton mServiceNum = holder.mServiceNum;
        final ImageView mServiceImg = holder.mServiceImg;

        mServiceTitle.setText(mServiceList.get(position).getmServiceName());
        mServiceNum.setImageResource(mServiceList.get(position).getmServiceNum());
        mServiceImg.setImageResource(mServiceList.get(position).getmServiceImg());

/*        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),"CLCIKED "+position,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, PaymentHandler.class);
                intent.putExtra("BILL", new String[] {getCurrentDate(),pendBill.get(position).getTitle()});
                context.startActivity(intent);

            }
        });*/

    }
    @Override
    public int getItemCount() {
        return mServiceList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mServiceTitle;
        ImageButton mServiceNum;
        ImageView mServiceImg;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mServiceTitle = itemView.findViewById(R.id.tvServiceTitle);
            this.mServiceNum = itemView.findViewById(R.id.ibServiceNum);
            this.mServiceImg = itemView.findViewById(R.id.imServiceImg);

        }
    }
}
