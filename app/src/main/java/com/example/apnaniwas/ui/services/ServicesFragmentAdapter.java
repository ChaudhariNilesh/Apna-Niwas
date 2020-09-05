package com.example.apnaniwas.ui.services;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaniwas.R;

import java.util.ArrayList;


public class ServicesFragmentAdapter extends RecyclerView.Adapter<ServicesFragmentAdapter.ViewHolder> {
    private ArrayList<ServicesViewModel> mServiceList;
    private Context context;
    //  private CallClickEvent callClickEvent;
    ServicesViewModel data;
    ArrayList<ServiceContactModel> contactData;

    public ServicesFragmentAdapter(Context context, ArrayList<ServicesViewModel> mServiceList, ArrayList<ServiceContactModel> serviceContactData) {
        this.context = context;
        this.mServiceList = mServiceList;
        this.contactData = serviceContactData;
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
        data = mServiceList.get(position);

        final TextView mServiceTitle = holder.mServiceTitle;
        final ImageButton mServiceNum = holder.mServiceNum;
        final ImageView mServiceImg = holder.mServiceImg;

        mServiceTitle.setText(mServiceList.get(position).getmServiceName());
        mServiceNum.setImageResource(mServiceList.get(position).getmServiceNum());
        mServiceImg.setImageResource(mServiceList.get(position).getmServiceImg());
        mServiceNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberToPhone(contactData.get(position).service_contact_no);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mServiceList.size();
    }

/*    @Override
    public void onClick(View v) {
        callClickEvent.numberToPhone(contactData.get(postion));
    }*/

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
/*    public interface CallClickEvent{
        void numberToPhone(int number);
    }*/


    public void numberToPhone(String number) {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + number)); //change this with number
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            context.startActivity(callIntent);
        } catch (ActivityNotFoundException activityException) {
            Log.e("Calling a Phone Number", "Call failed", activityException);
        }
    }
}
