package com.example.apnaniwas.signup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apnaniwas.apnaniwasDB.connection.APIService;
import com.example.apnaniwas.apnaniwasDB.connection.RestClient;
import com.example.apnaniwas.apnaniwasDB.model.CommonResponse;
import com.example.apnaniwas.login.Login;
import com.example.apnaniwas.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;

public class MobileRegistration extends AppCompatActivity {



    private EditText editTextMobile;
    private  int STATUS_CODE;
    private static APIService apiService = RestClient.createService(APIService.class);
    private CompositeDisposable disposable = new CompositeDisposable();
    String mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_reg);

        editTextMobile = findViewById(R.id.editMobileReg);

        findViewById(R.id.btnMobileVerify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mobile = editTextMobile.getText().toString().trim();

                if(mobile.isEmpty() || mobile.length() < 10){
                    editTextMobile.setError("Enter a valid mobile");
                    editTextMobile.requestFocus();
                    return;
                }

                isMobileExist(mobile);
            }
        });
    }

    public void isMobileExist(String mobile)
    {
        disposable.add(
                apiService.fetchMobile("fetch_mobile",mobile)
                        .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleSuccess, this::handleError));
    }


    private void handleSuccess(CommonResponse commonResponse) {
        STATUS_CODE = commonResponse.getStatus();
        if(STATUS_CODE == 200)
        {
            Toast.makeText(this,"Account already exists, Please Login.",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MobileRegistration.this, Login.class);
            intent.putExtra("Exist Mobile", mobile);
            startActivity(intent);
            finish();
        }
        else if(STATUS_CODE == 201)
        {
            Intent intent = new Intent(MobileRegistration.this, MobileVerification.class);
            intent.putExtra("New Mobile", mobile);
            startActivity(intent);
            finish();
        }

    }
    private void handleError(Throwable e) {
        Log.e("FETCH ERROR  ", "onError: " + e.getMessage());

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

