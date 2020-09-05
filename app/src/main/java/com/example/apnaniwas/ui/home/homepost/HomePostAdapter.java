package com.example.apnaniwas.ui.home.homepost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaniwas.R;

import java.util.ArrayList;

public class HomePostAdapter extends RecyclerView.Adapter<HomePostAdapter.MyHolder>{
    private OnPostItemListener onPostItemListener;
    private ArrayList<HomePostModel> allposts;
    private Context context;

    public HomePostAdapter(ArrayList<HomePostModel> allposts, Context context, OnPostItemListener onPostItemListener) {
        this.allposts = allposts;
        this.context = context;
        this.onPostItemListener = onPostItemListener;
    }

    public void setAllposts(ArrayList<HomePostModel> allposts) {
        this.allposts = allposts;
    }

    @NonNull
    @Override
    public HomePostAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.home_post_layout, parent, false);
        MyHolder holder = new MyHolder(view,onPostItemListener);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position){
        holder.postTitle.setText(allposts.get(position).getPostTitle());
        holder.postSummary.setText(allposts.get(position).getPostSummary());
        holder.postImage.setImageResource(allposts.get(position).getPostGradientImage());
    }


    @Override
    public int getItemCount() {
        if (allposts == null) {
            return 0;
        }
        return allposts.size();
    }




    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button gotopost;
        ImageView postImage;
        TextView postTitle, postSummary;
        OnPostItemListener onPostItemListener;
        public MyHolder(@NonNull View itemView,OnPostItemListener onPostItemListener) {
            super(itemView);
            postTitle = itemView.findViewById(R.id.postTitle);
            postSummary = itemView.findViewById(R.id.postSummary);
            postImage = itemView.findViewById(R.id.postImage);
            gotopost = itemView.findViewById(R.id.gotopost);
            this.onPostItemListener=onPostItemListener;
            gotopost.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onPostItemListener.onItemClicked(getAdapterPosition());
        }
    }
    public interface OnPostItemListener{
        void onItemClicked(int position);
    }
}
