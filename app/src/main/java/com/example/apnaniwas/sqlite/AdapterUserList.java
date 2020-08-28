/*
package com.example.apnaniwas.SQLite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apnaniwas.R;

import java.util.List;

// LIST ADAPTER FOR DISPLAYING USER DETAILS FROM SQLITE DB

public class AdapterUserList extends ArrayAdapter<UserDetailsModel> {

        List<UserDetailsModel> items;

        private int layoutId;

        public AdapterUserList(@NonNull Context context, int layoutId, List<UserDetailsModel> items) {
            super(context, layoutId, items);
            this.layoutId = layoutId;
            this.items = items;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            UserDetailsModel Item = items.get(position);

            ViewHolder viewHolder;

            if (convertView == null) {

                viewHolder = new ViewHolder();

                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layoutId, parent, false);

                viewHolder.usrname = convertView.findViewById(R.id.dispname);
                viewHolder.usrpswd = convertView.findViewById(R.id.disppassword);
                viewHolder.usremail = convertView.findViewById(R.id.dispemail);
                viewHolder.usrmob = convertView.findViewById(R.id.dispmobile);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.usrname.setText(Item.getUsrname());
            viewHolder.usrpswd.setText(Item.getUsrpswd());
            viewHolder.usremail.setText(Item.getUsremail());
            viewHolder.usrmob.setText(Item.getUsrmob());

          Button delButton = convertView.findViewById(R.id.deleteUserDetail);
          Button editButton = convertView.findViewById(R.id.editUserDetail);

            delButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DbHandler db = new DbHandler(getContext());
                    db.deleteUserDetails(new UserDetailsModel());


                }
            });

            return convertView;
        }

        @Override
        public int getCount() {
            return super.getCount();
        }

        public static class ViewHolder {
            TextView usrname;
            TextView usrpswd;
            TextView usremail;
            TextView usrmob;
        }


    }


*/
