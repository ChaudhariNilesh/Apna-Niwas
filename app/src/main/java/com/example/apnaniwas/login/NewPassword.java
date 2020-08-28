package com.example.apnaniwas.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apnaniwas.R;
import com.example.apnaniwas.apnaniwasDB.connection.APIService;
import com.example.apnaniwas.apnaniwasDB.connection.RestClient;
import com.example.apnaniwas.apnaniwasDB.model.CommonResponse;
import com.example.apnaniwas.apnaniwasDB.model.signupresponse.MemberModel;
import com.example.apnaniwas.databinding.ActivityNewPasswordBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NewPassword extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private String strNewPswd, strCfrmPswd;
    private EditText etNewPswd,etCfrmPswd;
    private static APIService apiService = RestClient.createService(APIService.class);
    private CompositeDisposable disposable = new CompositeDisposable();
    private List<MemberModel> dataList = new ArrayList<>();
    private int member_id;
    private ActivityNewPasswordBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null){
            member_id = extras.getInt("memberId");
        }
        else {
            Toast.makeText(this,"Sorry, Try Again.",Toast.LENGTH_SHORT).show();
            finish();
        }

        etNewPswd = binding.etNewPassword;
        etCfrmPswd = binding.etConfmNewPassword;

        binding.btnNewPswdSend.setOnClickListener(this);
        etCfrmPswd.setOnTouchListener(this);
        etNewPswd.setOnTouchListener(this);
        binding.imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnNewPswdSend) {
            passwordValidate();
        }
        else  if(v.getId() == R.id.imgBack) {
            finish();
        }
        else {
            etCfrmPswd.setError("Not Matched");
            etCfrmPswd.setText(null);
        }
    }

    private void uppdatePassword(String newPswd) {
        disposable.add(apiService.resetPassword("reset_password",newPswd,member_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess,this::handleError));
    }

    private void handleSuccess(CommonResponse commonResponse) {
        int STATUS_CODE = commonResponse.getStatus();
        if(STATUS_CODE == 200) {
            Toast.makeText(getApplicationContext(), "Password reset succesfully.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(NewPassword.this,Login.class));
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Please try again.", Toast.LENGTH_SHORT).show();

        }
    }
    private void handleError(Throwable e) {
        Log.e("ERROR", "onError: " + e.getMessage());
    }

    private void passwordValidate() {

        setError();
        strNewPswd = etNewPswd.getText().toString();
        strCfrmPswd = etCfrmPswd.getText().toString();

        boolean err = false;

        if(strNewPswd.isEmpty()){
            err = true;
            etNewPswd.setError("Empty password is not allowed");
            etNewPswd.requestFocus();
        }
        if( (strNewPswd.length() <8 || strNewPswd.length() > 12)){
            etCfrmPswd.setText(null);
            err = true;
            binding.tvPswdDisc.setVisibility(View.VISIBLE);
            etNewPswd.requestFocus();
        }
        if(!strCfrmPswd.equals(strNewPswd)) {
            err = true;
            etCfrmPswd.setText(null);
            etCfrmPswd.setError("Not Matched !");
            etCfrmPswd.requestFocus();
        }
        if (!err) {
             uppdatePassword(strNewPswd);
            //  mProgressBar.setVisibility(View.VISIBLE);
        }
    }
    private void setError() {
        binding.tvPswdDisc.setVisibility(View.INVISIBLE);
        etNewPswd.setError(null);
        etCfrmPswd.setError(null);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;

        if (v.getId() == R.id.etNewPassword) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (etNewPswd.getRight() - etNewPswd.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    if (etNewPswd.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                        etNewPswd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_password_eye, 0);
                        etNewPswd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        etNewPswd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_password_eye_off, 0);
                        etNewPswd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    return true;
                }
            }

        } else if (v.getId() == R.id.et_confmNewPassword) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (etCfrmPswd.getRight() - etCfrmPswd.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    if (etCfrmPswd.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                        etCfrmPswd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_password_eye, 0);
                        etCfrmPswd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        etCfrmPswd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_password_eye_off, 0);
                        etCfrmPswd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
