package com.example.apnaniwas.postuploader;

import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.apnaniwas.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AddNewPostAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Uri> mImages;
    public AddNewPostAdapter(Context context,ArrayList<Uri> mImages) {
        this.mImages=mImages;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, int position) {
        View  LayoutInflater = inflater.inflate(R.layout.new_postimage_pager_item, container,false);
        final ImageView imageView = LayoutInflater.findViewById(R.id.image);

        Glide.with(context)
                .load(mImages.get(position))
                .into(imageView);

        container.addView(LayoutInflater);
        return LayoutInflater;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NotNull Object view) {
        container.removeView((View) view);
    }

    @Override
    public int getCount() {
        return mImages.size();
    }


    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
        return object == view;
    }
    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
