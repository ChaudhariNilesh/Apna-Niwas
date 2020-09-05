package com.example.apnaniwas.ui.home.homepost;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.apnaniwas.R;
import com.example.apnaniwas.apnaniwasDB.connection.VariableBag;
import com.example.apnaniwas.ui.home.HomeFragment;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HomePostFragment extends Fragment {


    public static HomePostFragment newInstance() {
        return new HomePostFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }
    

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = HomePostFragmentArgs.fromBundle(getArguments()).getPosition();
        ImageView postImage = view.findViewById(R.id.insidePostImage);
        TextView postTitle = view.findViewById(R.id.insidePostTitle);
        TextView postDescription = view.findViewById(R.id.insidePostDesc);
        ArrayList<HomePostModel> post= HomeFragment.posts;

/*        Glide.with(getContext())
                .load(R.drawable.gradient_background)
                .into(postImage);*/

        postTitle.setText(post.get(position).getPostTitle());
        postDescription.setText(post.get(position).getPostSummary());
        postImage.setImageBitmap(post.get(position).getPostImgBitmap());
    }
}
