package com.example.apnaniwas.ui.yourpost;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaniwas.R;
import com.example.apnaniwas.postuploader.AddNewPost;

import java.util.ArrayList;
import java.util.Objects;


public class YourPostFragment extends Fragment {
    YourPostAdapter mAdapter;
    RecyclerView mRecyclerView;

    ArrayList<YourPostViewModel> data = new ArrayList<>();

    public static String IMGS[] = {
            "https://images.unsplash.com/photo-1444090542259-0af8fa96557e?q=80&fm=jpg&w=1080&fit=max&s=4b703b77b42e067f949d14581f35019b",
            "https://images.unsplash.com/photo-1439546743462-802cabef8e97?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1441155472722-d17942a2b76a?q=80&fm=jpg&w=1080&fit=max&s=80cb5dbcf01265bb81c5e8380e4f5cc1",
            "https://images.unsplash.com/photo-1437651025703-2858c944e3eb?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1431538510849-b719825bf08b?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1434873740857-1bc5653afda8?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1439396087961-98bc12c21176?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1433616174899-f847df236857?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1438480478735-3234e63615bb?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1438027316524-6078d503224b?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_your_post, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        for (int i = 0; i < IMGS.length; i++) {

            YourPostViewModel imageModel = new YourPostViewModel();
            imageModel.setImgTitle("Image " + i);
            imageModel.setImgUrl(IMGS[i]);
            data.add(imageModel);
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerView.setHasFixedSize(true);


        mAdapter = new YourPostAdapter(getContext(), data);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
                new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(getContext(), DetailActivity.class);
                        intent.putParcelableArrayListExtra("data", data);
                        intent.putExtra("pos", position);
                        startActivity(intent);

                    }
                }));
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.new_post_pic_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.new_post_pic) {
            startActivity(new Intent(getContext(), AddNewPost.class));
            Objects.requireNonNull(getActivity()).finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

