package com.example.apnaniwas.notification;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
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
import com.example.apnaniwas.notification.noticequeries.NoticeQueries;
import com.example.apnaniwas.util.NoticePref;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotificationBoardAdapter extends RecyclerView.Adapter<NotificationBoardAdapter.myViewHolder> {
    private final Context context;
    private ArrayList<NoticePrefModel> noticeSet;
    private int mRecentlyDeletedItemPosition;

    public NotificationBoardAdapter(ArrayList<NoticePrefModel> notice, Context applicationContext) {
        this.noticeSet = notice;
        this.context = applicationContext;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        TextView textViewMessage;
        ImageButton imageButtonInfo;
       // ImageButton imageButtonNoticeQry;
        ImageButton imageButtonNoticeDel;

        public myViewHolder(View itemView) {
            super(itemView);
            this.textViewTitle = itemView.findViewById(R.id.tv_notice_title);
            this.textViewMessage = itemView.findViewById(R.id.tv_notice_message);
            this.imageButtonInfo = itemView.findViewById(R.id.ib_notice_details);
//            this.imageButtonNoticeQry = itemView.findViewById(R.id.ib_NoticeQry);
            this.imageButtonNoticeDel = itemView.findViewById(R.id.ib_notice_del);
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
        final NoticePrefModel noticePrefModel = noticeSet.get(position);

        final TextView textViewTitle = holder.textViewTitle;
        TextView textViewMessage = holder.textViewMessage;
        ImageButton imageButtonInfo = holder.imageButtonInfo;
       // ImageButton imageButtonNoticeQry = holder.imageButtonNoticeQry;
        ImageButton imageButtonNoticeDel = holder.imageButtonNoticeDel;

        try {
            textViewTitle.setText(new JSONObject(noticePrefModel.getnoticeJsonData()).getJSONObject("data").getString("title"));
            String messageStr = new JSONObject(noticePrefModel.getnoticeJsonData()).getJSONObject("data").getString("message");

            if (messageStr.length() > 20) {
                StringBuilder longMessageStr = new StringBuilder(messageStr);
                messageStr = longMessageStr.substring(0, 10);
                textViewMessage.setText(messageStr+ "....");
            } else {
                textViewMessage.setText(messageStr);
            }
            imageButtonInfo.setImageResource(noticePrefModel.getImgInfo());
          //  imageButtonNoticeQry.setImageResource(noticePrefModel.getImgNoticeQry());
            imageButtonNoticeDel.setImageResource(noticePrefModel.getImgDel());

        } catch (JSONException e) {
            e.printStackTrace();
        }

       holder.imageButtonNoticeDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDelNotice(position,v);
            }
        });

        holder.imageButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                  //  Toast.makeText(context,String.valueOf(noticeSet.size()+"<><>"+position),Toast.LENGTH_SHORT).show();
                    String alertTitle = new JSONObject(noticeSet.get(position).getnoticeJsonData()).getJSONObject("data").getString("title");
                    String alertMessage = new JSONObject(noticeSet.get(position).getnoticeJsonData()).getJSONObject("data").getString("message");
                    loadInfoAlertBox(v, alertTitle, alertMessage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

/*        holder.imageButtonNoticeQry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NoticeQueries.class);
                context.startActivity(intent);
            }
        });*/
    }

    private void alertDelNotice(int position, View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(),R.style.MyAlertDialogStyle);
        builder.setMessage("Do you want to delete the notice ?").setTitle("Delete Notice");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                NoticePref.getInstance(context).removeNoticePref(noticeSet.get(position).getnoticeKey());
                mRecentlyDeletedItemPosition = position;
                noticeSet.remove(position);
                notifyItemRemoved(mRecentlyDeletedItemPosition);
                notifyItemRangeChanged(mRecentlyDeletedItemPosition, noticeSet.size());
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
        public int getItemCount() {
            return noticeSet.size();
        }

    private void loadInfoAlertBox(View v, String alertTitle,String alertMessage)
    {
        ViewGroup viewGroup = v.findViewById(R.id.infoAlertBox);
        View noticeinfoview = LayoutInflater.from(v.getContext()).inflate(R.layout.layout_info_notice_alert, viewGroup ,false);
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

        TextView tv_alertTitle = noticeinfoview.findViewById(R.id.alertTitle);
        TextView tv_alertMessage = noticeinfoview.findViewById(R.id.alertMsg);

        tv_alertTitle.setText(alertTitle);
        tv_alertMessage.setText(alertMessage);

        builder.setView(noticeinfoview);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    public ArrayList<NoticePrefModel> getData() {
        return noticeSet;
    }
}
