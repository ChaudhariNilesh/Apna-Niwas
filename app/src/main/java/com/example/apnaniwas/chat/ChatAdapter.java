package com.example.apnaniwas.chat;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaniwas.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<Message> chatList;
    ViewGroup parent;

    public ChatAdapter(List<Message> chatList) {
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_my_message,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message messages = chatList.get(position);

        Date complexDate = new Date(messages.getTime());
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        String date = sfd.format(complexDate);

        RelativeLayout.LayoutParams lpName = (RelativeLayout.LayoutParams) holder.name.getLayoutParams();
        RelativeLayout.LayoutParams lpMessage = (RelativeLayout.LayoutParams) holder.message.getLayoutParams();
        RelativeLayout.LayoutParams lpTime = (RelativeLayout.LayoutParams) holder.time.getLayoutParams();
        RelativeLayout.LayoutParams lpMsgMeta = (RelativeLayout.LayoutParams) holder.msgMeta.getLayoutParams();

        if(messages.getName().equals("Nikul")) {
            lpMsgMeta.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            lpMessage.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            lpMessage.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            lpMessage.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

            holder.time.setText(date.toString());
            holder.message.setText(messages.getMessage());
            holder.name.setVisibility(View.GONE);

            holder.message.setBackgroundResource(R.drawable.my_message);
            holder.message.setTextColor(Color.WHITE);

            holder.msgMeta.setLayoutParams(lpMsgMeta);
            holder.message.setLayoutParams(lpMessage);
        }else {
            lpMsgMeta.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            lpMessage.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            lpMessage.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            lpMessage.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

            holder.name.setVisibility(View.VISIBLE);
            holder.name.setText(messages.getName());
            holder.message.setText(messages.getMessage());
            holder.time.setText(date.toString());

            holder.name.setTextColor(Color.parseColor("#3D73DD"));
            holder.message.setBackgroundResource(R.drawable.their_message);
            holder.message.setTextColor(Color.BLACK);

            holder.msgMeta.setLayoutParams(lpMsgMeta);
            holder.message.setLayoutParams(lpMessage);
        }

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name,message,time;
        public RelativeLayout msgMeta;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.chat_name);
            message = itemView.findViewById(R.id.chat_message);
            time = itemView.findViewById(R.id.chat_time);
            msgMeta = itemView.findViewById(R.id.msg_meta);
        }
    }
}
