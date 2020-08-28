package com.example.apnaniwas.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.apnaniwas.R;
import com.example.apnaniwas.apnaniwasDB.model.signupresponse.MemberModel;

import com.example.apnaniwas.databinding.FragmentProfileBinding;
import com.example.apnaniwas.tmp.TmpProfile;
import com.example.apnaniwas.util.SharedPreference;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ProfileFragment extends Fragment  {
private TextView Usrname;
private FragmentProfileBinding binding;
private  MemberModel user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        Usrname = binding.tvUsername;


        //getting the current user
        user = SharedPreference.getInstance(getActivity()).getMember();
        Usrname.setText(user.getMemberName());
        binding.editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),user.getMemberName(),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), TmpProfile.class));
            }
        });
        return binding.getRoot();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.log_out_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logOut) {
            Objects.requireNonNull(getActivity()).finish();
            SharedPreference.getInstance(getActivity()).logout();
            FirebaseAuth.getInstance().signOut();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

