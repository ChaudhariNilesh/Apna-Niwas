package com.example.apnaniwas.notification.noticequeries;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apnaniwas.R;

import java.util.ArrayList;


public class NoticeQueryAdapter extends ArrayAdapter<NoticeQryViewModel> {

    ArrayList<NoticeQryViewModel> items;

    private int layoutId;

    public NoticeQueryAdapter(@NonNull Context context, int layoutId, ArrayList<NoticeQryViewModel> items) {
        super(context, layoutId, items);
        this.layoutId = layoutId;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        NoticeQryViewModel item = items.get(position);

        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layoutId, parent, false);

            viewHolder.usrProfileImg = convertView.findViewById(R.id.cir_profile_image);
            viewHolder.usrName = convertView.findViewById(R.id.tv_qry_username);
            viewHolder.usrQry = convertView.findViewById(R.id.tv_qry);
            viewHolder.usrTime = convertView.findViewById(R.id.tv_qry_time);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.usrProfileImg.setImageResource(item.getUsrProfileImage());
        viewHolder.usrName.setText(item.getUserName());
        viewHolder.usrQry.setText(item.getQry());
        viewHolder.usrTime.setText(item.getQryTime());

        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    public static class ViewHolder {
        ImageView usrProfileImg;
        TextView usrName;
        TextView usrQry;
        TextView usrTime;
    }


}