package com.example.apnaniwas.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.apnaniwas.login.ForgotPassword;
import com.example.apnaniwas.login.VerifyResetCode;
import com.example.apnaniwas.notification.FCM.TempLayout;
import com.example.apnaniwas.tmp.TmpProfile;
import com.example.apnaniwas.util.SharedPreference;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ProfileFragment extends Fragment  {
private TextView Usrname;
private FragmentProfileBinding binding;
private  MemberModel user;
private TextView tvFlatno,tvabout;
    private TextView GotoAccount;
    final String MYPREFS="DETAILS";
    final String FLATNO="FLATNO";
    final String BLOCK ="BLOCK";
    final String TOTALMEMBERS ="TOTALMEMBERS";
    final String MEM1NAME="MEM1NAME";
    final String MEM2NAME="MEM2NAME";
    final String MEM3NAME="MEM3NAME";
    final String MEM4NAME="MEM4NAME";
    final String MEM5NAME="MEM5NAME";
    final String MEM6NAME="MEM6NAME";
    final String MEM1RELATION="MEM1RELATION";
    final String MEM2RELATION="MEM2RELATION";
    final String MEM3RELATION="MEM3RELATION";
    final String MEM4RELATION="MEM4RELATION";
    final String MEM5RELATION="MEM5RELATION";
    final String MEM6RELATION="MEM6RELATION";


    SharedPreferences sharedpreferences;
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

        tvFlatno=view.findViewById(R.id.tvFlatnoBlock);
        tvabout=view.findViewById(R.id.tvabout);

        Usrname = binding.tvUsername;
        GotoAccount = binding.gotoAcount;


        //getting the current user
        user = SharedPreference.getInstance(getActivity()).getMember();
        Usrname.setText(user.getMemberName());
/*
        binding.editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),user.getMemberName(),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), TmpProfile.class));
            }
        });
*/

        GotoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
            }
        });
            return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedpreferences = getActivity().getSharedPreferences(MYPREFS, Context.MODE_PRIVATE);
        String flatno = sharedpreferences.getString(FLATNO,"");
        String block = sharedpreferences.getString(BLOCK,"");
        int totalmembers = sharedpreferences.getInt(TOTALMEMBERS,0);
        tvFlatno.setText(block+" "+flatno);
        String s="Members in the house \n";
        if(totalmembers>=1)
        {

            s+="Member 1 Name : "+sharedpreferences.getString(MEM1NAME,"")+"\tRelation : "+sharedpreferences.getString(MEM1RELATION,"")+"\n";
            if(totalmembers>=2)
            {
                s+="Member 2 Name : "+sharedpreferences.getString(MEM2NAME,"")+"\tRelation : "+sharedpreferences.getString(MEM2RELATION,"")+"\n";
                if(totalmembers>=3)
                {
                    s+="Member 3 Name : "+sharedpreferences.getString(MEM3NAME,"")+"\tRelation : "+sharedpreferences.getString(MEM3RELATION,"")+"\n";
                    if(totalmembers>=4)
                    {
                        s+="Member 4 Name : "+sharedpreferences.getString(MEM4NAME,"")+"\tRelation : "+sharedpreferences.getString(MEM4RELATION,"")+"\n";
                        if(totalmembers>=5)
                        {
                            s+="Member 5 Name : "+sharedpreferences.getString(MEM5NAME,"")+"\tRelation : "+sharedpreferences.getString(MEM5RELATION,"")+"\n";
                            if(totalmembers>=6)
                            {
                                s+="Member 6 Name : "+sharedpreferences.getString(MEM6NAME,"")+"\tRelation : "+sharedpreferences.getString(MEM6RELATION,"")+"\n";
                            }
                        }
                    }
                }
            }
        }
        tvabout.setText(s);
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

