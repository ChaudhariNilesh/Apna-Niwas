package com.example.apnaniwas.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apnaniwas.R;
import com.example.apnaniwas.apnaniwasDB.connection.APIService;
import com.example.apnaniwas.apnaniwasDB.connection.RestClient;
import com.example.apnaniwas.apnaniwasDB.model.CommonResponse;
import com.example.apnaniwas.apnaniwasDB.model.forgotpasswordresponse.ForgotPasswordResponse;
import com.example.apnaniwas.apnaniwasDB.model.loginresponse.LoginResponse;
import com.example.apnaniwas.databinding.ActivityForgotPasswordBinding;
import com.example.apnaniwas.signup.MobileRegistration;
import com.example.apnaniwas.signup.MobileVerification;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {
    private EditText etNewPassword;
    private ActivityForgotPasswordBinding binding;
    private static APIService apiService = RestClient.createService(APIService.class);
    private CompositeDisposable disposable = new CompositeDisposable();
    String mobile;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

       binding.btnForgetSend.setOnClickListener(this);
       binding.imgBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        mobile =  binding.etFogetPswdMob.getText().toString().trim();

        if(mobile.isEmpty() || mobile.length() < 10){
            binding.etFogetPswdMob.setError("Enter a valid mobile");
            binding.etFogetPswdMob.requestFocus();
            return;
        }
        if(v.getId() == R.id.btnForgetSend)
        {
            isMobileExist(mobile);
        }
        else if(v.getId() == R.id.imgBack) {
            finish();
        }
    }

    private void isMobileExist(String mobile) {
        disposable.add(
                apiService.forgotPassword("forgot_password",mobile)
                        .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleSuccess, this::handleError));
    }

    private void handleSuccess(ForgotPasswordResponse forgotPasswordResponse) {
        int STATUS_CODE = forgotPasswordResponse.getStatus();
        final int member_id =  forgotPasswordResponse.getMemberId();
        if(STATUS_CODE == 200)
        {
            Intent intent = new Intent(ForgotPassword.this, VerifyResetCode.class);
            Bundle extras = new Bundle();
            extras.putString("Exist Mobile", mobile);
            extras.putInt("memberId",member_id);
            intent.putExtras(extras);
            startActivity(intent);
            finish();
        }
        else if (STATUS_CODE == 404)
        {
            Intent intent = new Intent(ForgotPassword.this, Login.class);
            Toast.makeText(this,forgotPasswordResponse.getMessage(),Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this,"Please Try Again.",Toast.LENGTH_SHORT).show();
        }
    }

    private void handleError(Throwable error) {
        if (error instanceof HttpException) {
            Gson gson = new GsonBuilder().create();
            try {
                String errorBody = ((HttpException) error).response().errorBody().string();
                LoginResponse loginResponse = gson.fromJson(errorBody,LoginResponse.class);
                showSnackBarMessage(loginResponse.getMesssage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showSnackBarMessage("Network Error !");
        }
        Log.e("FETCH ERROR  ", "onError: " + error.getMessage());
    }
    private void showSnackBarMessage(String message) {
        View parentLayout = findViewById(R.id.relLayForgot);
        if (parentLayout != null) {
            Snackbar.make(parentLayout,message,Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
        protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
