package com.example.apnaniwas.ui.home;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.apnaniwas.notification.NotificationBoard;
import com.example.apnaniwas.R;
import com.example.apnaniwas.ui.home.homepost.HomePostAdapter;
import com.example.apnaniwas.ui.home.homepost.HomePostModel;
import com.example.apnaniwas.postuploader.AddNewPost;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment implements HomePostAdapter.OnPostItemListener {
    private NavController navController;
    private RecyclerView rv;
    HomePostAdapter padapter;
    public static ArrayList<HomePostModel> posts=new ArrayList<HomePostModel>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        rv = root.findViewById(R.id.homerecylerview);
        FloatingActionButton mFloatingActionButton = (FloatingActionButton) root.findViewById(R.id.fabAddPost);
        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);
        posts.add(new HomePostModel(R.drawable.gradient_background,"Increasing Gym Timing","So in today's meeting, we all decided to increase our Gym's timing watching everyone's concern about it"));
        posts.add(new HomePostModel(R.drawable.gradient_background, "Maha ShivRatri's Antakshri Competition Results","The #1 Prize goes to Nikul Prajapati "));
        padapter = new HomePostAdapter(posts, getActivity(), this);
        rv.setAdapter(padapter);


        Toolbar toolbar = root.findViewById(R.id.toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);

       rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && mFloatingActionButton.getVisibility() == View.VISIBLE) {
                    mFloatingActionButton.hide();
                } else if (dy < 0 && mFloatingActionButton.getVisibility() != View.VISIBLE) {
                    mFloatingActionButton.show();
                }
            }
        });
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        FloatingActionButton fab = view.findViewById(R.id.fabAddPost);
        fab.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), AddNewPost.class);
            startActivity(i);
        });
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.notification_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.notice_board) {
            Intent intent = new Intent(getContext(), NotificationBoard.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
   @Override
    public void onItemClicked(int position) {
        HomeFragmentDirections.ActionNavigationHomeToPostFragment action = HomeFragmentDirections.actionNavigationHomeToPostFragment();
        action.setPosition(position);
        navController.navigate(action);

    }
    @Override
    public void onStart() {
        super.onStart();
        padapter.notifyDataSetChanged();
    }
}