package com.example.apnaniwas.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.apnaniwas.apnaniwasDB.connection.APIService;
import com.example.apnaniwas.apnaniwasDB.connection.RestClient;
import com.example.apnaniwas.apnaniwasDB.model.loginresponse.LoginResponse;
import com.example.apnaniwas.BottomNavigationBar;
import com.example.apnaniwas.R;
import com.example.apnaniwas.signup.MobileRegistration;
import com.example.apnaniwas.util.FirebaseTokenPref;
import com.example.apnaniwas.util.SharedPreference;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;


public class Login extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener, View.OnTouchListener {

    private static APIService apiService = RestClient.createService(APIService.class);
    private CompositeDisposable disposable = new CompositeDisposable();
    String mLoginId, mPassword;
    private EditText etLoginId, etPassword;
    private ProgressDialog progressDialog;
    private  String deviceToken="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginId = findViewById(R.id.et_loginId);
        etPassword =  findViewById(R.id.et_loginPassword);


        Button mLogIn = findViewById(R.id.btnLogin);
        TextView mSignUp = findViewById(R.id.tvClickSignUp);
        TextView mforgetPassword = findViewById(R.id.tvForgetPswd);

        // GET EXIST MOBILE FROM MOBILE REGISTRATION
        Intent intent = getIntent();
        etLoginId.setText(intent.getStringExtra("Exist Mobile"));

        mLogIn.setOnClickListener(this);
        mSignUp.setOnClickListener(this);
        etPassword.setOnEditorActionListener(this);
        etPassword.setOnTouchListener(this);
        mforgetPassword.setOnClickListener(this);

        // GOOGLE SIGN IN BUTTON code in scratch_1
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_DONE)
        {
            login(deviceToken);
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            if(sentTokenToServer()){
                login(deviceToken);
            }
            else{
                Toast.makeText(getApplicationContext(), "Failed to register device.", Toast.LENGTH_LONG).show();
            }

        }
        else if(v.getId() == R.id.tvClickSignUp){
            startActivity(new Intent(Login.this, MobileRegistration.class));
        }
        else if(v.getId() == R.id.tvForgetPswd)
        {
            startActivity(new Intent(Login.this, ForgotPassword.class));
        }
    }

    private void login(String deviceToken) {
        setError();
        mLoginId = Objects.requireNonNull(etLoginId.getText()).toString().trim();
        mPassword = Objects.requireNonNull(etPassword.getText()).toString().trim();
        boolean err = false;
        if(mLoginId.isEmpty() || mLoginId.length() < 10) {
            err = true;
            etLoginId.setError("Enter a valid mobile");
            etLoginId.requestFocus();
        }
        if (mPassword.isEmpty()) {

            err = true;
            etPassword.setError("Password should not be empty !");
            etLoginId.requestFocus();
        }
        if (!err) {
            auth_loginDetails(mLoginId,mPassword,deviceToken);
          //  mProgressBar.setVisibility(View.VISIBLE);
        } else {
            showSnackBarMessage("Enter Valid Details !");
        }
    }

    private void auth_loginDetails(String mem_LoginId, String mem_Password, String deviceToken) {

        disposable.add(
                apiService.authLogin("verify_login", mem_LoginId, mem_Password,deviceToken)
                        .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleSuccess, this::handleError));
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

    private void handleSuccess(LoginResponse loginResponse) {
        if(loginResponse.getStatus() != 200)
        {
            etLoginId.requestFocus();
            Toast.makeText(this,loginResponse.getMesssage(),Toast.LENGTH_LONG).show();
        }
        else
        {

            etLoginId.setText(null);
            etPassword.setText(null);

            SharedPreference.getInstance(getApplicationContext()).userLogin(loginResponse);
            SharedPreference.getInstance(getApplicationContext()).setFirstTimeLaunch(true);
            Intent i=new Intent(Login.this,
                    BottomNavigationBar.class);
            startActivity(i);
            finish();
        }

    }
    private boolean sentTokenToServer() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering Device...");
        progressDialog.show();

        deviceToken = FirebaseTokenPref.getInstance(getApplicationContext()).getDeviceToken();

        if (deviceToken == null) {
            progressDialog.dismiss();
            //     Toast.makeText(this, "Token not generated.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private void showSnackBarMessage(String message) {
        View parentLayout = findViewById(R.id.LayLoginParent);
        if (parentLayout != null) {

            Snackbar.make(parentLayout,message,Snackbar.LENGTH_SHORT).show();
        }
    }

    private void setError() {

        etLoginId.setError(null);
        etPassword.setError(null);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;

        if(event.getAction() == MotionEvent.ACTION_UP) {
            if(event.getRawX() >= (etPassword.getRight() - etPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                if(etPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance()))
                {
                    etPassword.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_password_eye,0);
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    etPassword.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_password_eye_off,0);
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

                return true;
            }
        }
        return false;
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
