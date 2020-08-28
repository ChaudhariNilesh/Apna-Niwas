package com.example.apnaniwas.notification;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaniwas.R;

import java.util.ArrayList;

public class NotificationBoardAdapter extends RecyclerView.Adapter<NotificationBoardAdapter.myViewHolder> {
    private ArrayList<NotificationViewModel> noticeSet;

    public NotificationBoardAdapter(ArrayList<NotificationViewModel> notice) {
        this.noticeSet = notice;
    }


    public static class myViewHolder extends RecyclerView.ViewHolder {

        TextView textViewSub;
        TextView textViewPostedBy;
        ImageButton imageButtonInfo;
        ImageButton imageButtonNoticeQry;

        public myViewHolder(View itemView) {
            super(itemView);
            this.textViewSub = itemView.findViewById(R.id.tv_notice_subject);
            this.textViewPostedBy = itemView.findViewById(R.id.tv_notice_by);
            this.imageButtonInfo = itemView.findViewById(R.id.ib_notice_details);
            this.imageButtonNoticeQry = itemView.findViewById(R.id.ib_NoticeQry);
        }
    }
        @NonNull
        @Override
        public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_row_layout, parent, false);
            myViewHolder myViewHolder = new myViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {
            final NotificationViewModel notificationViewModel = noticeSet.get(position);
            final TextView textViewSub = holder.textViewSub;
            TextView textViewPostedBy = holder.textViewPostedBy;
            ImageButton imageButtonInfo = holder.imageButtonInfo;
            ImageButton imageButtonNoticeQry = holder.imageButtonNoticeQry;

            textViewSub.setText(noticeSet.get(position).getSub());
            textViewPostedBy.setText(noticeSet.get(position).getPostedBy());
            imageButtonInfo.setImageResource(noticeSet.get(position).getImgInfo());
            imageButtonNoticeQry.setImageResource(noticeSet.get(position).getImgNoticeQry());

            holder.imageButtonInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(),"CLCIKED "+position,Toast.LENGTH_LONG).show();

                    loadInfoAlertBox(v,noticeSet.get(position).getSub());
                }
            });
        }

        @Override
        public int getItemCount() {
            return noticeSet.size();
        }

    private void loadInfoAlertBox(View v, String alertSub)
    {
        Log.d("TITLE",alertSub);
        ViewGroup viewGroup = v.findViewById(R.id.infoAlertBox);
        View noticeinfoview = LayoutInflater.from(v.getContext()).inflate(R.layout.layout_info_notice_alert, viewGroup ,false);
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

        TextView alertTitle = noticeinfoview.findViewById(R.id.alertTitle);
        alertTitle.setText(alertSub);


        builder.setView(noticeinfoview);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
