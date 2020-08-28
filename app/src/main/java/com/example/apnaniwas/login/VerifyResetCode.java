package com.example.apnaniwas.login;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.apnaniwas.R;
import com.example.apnaniwas.databinding.ActivityVerifyResetCodeBinding;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.TimeUnit;

public class VerifyResetCode extends AppCompatActivity implements View.OnClickListener {
    private String mobile;
    private FirebaseAuth mAuth;
    private EditText etResetCode;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationID;
    private int member_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.apnaniwas.databinding.ActivityVerifyResetCodeBinding binding = ActivityVerifyResetCodeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if( extras != null) {
            mobile = extras.getString("Exist Mobile");
            member_id = extras.getInt("memberId");
        }
        else
        {
            Toast.makeText(this,"Sorry, Try Again.",Toast.LENGTH_SHORT).show();
            finish();
        }
        etResetCode = binding.etResetCode;
        mAuth = FirebaseAuth.getInstance();
        sendVerificationCode(mobile);


        binding.btnResetCodeSend.setOnClickListener(this);
        binding.imgBack.setOnClickListener(this);
        binding.tvOTPResend.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imgBack)
        {
            finish();
        }
        else if(v.getId() == R.id.btnResetCodeSend){

            String mOTPCode = etResetCode.getText().toString().trim();
            if(mOTPCode.isEmpty() || mOTPCode.length() <6) {
                etResetCode.setError("Enter valid code");
                etResetCode.requestFocus();
                return;
            }
            verifyVerificationCode(mOTPCode);
        }
        else if(v.getId() == R.id.tvOTPResend ){
            resendOtpRequest(mobile, mResendToken);
        }

    }

    private void resendOtpRequest(String mobile, PhoneAuthProvider.ForceResendingToken mResendToken) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks,
                mResendToken);
    }


    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String mOTPCode = phoneAuthCredential.getSmsCode();

                    if (mOTPCode != null) {
                        etResetCode.setText(mOTPCode);
                        verifyVerificationCode(mOTPCode);
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(VerifyResetCode.this,e.getMessage(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCodeSent(@NotNull String s, @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    mVerificationID = s;
                    mResendToken = forceResendingToken;

                }
            };


    private void verifyVerificationCode(String mOTPCode) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationID,mOTPCode);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(VerifyResetCode.this,
                task -> {
                    if (task.isSuccessful()){

                        Intent intent = new Intent(getApplicationContext(),NewPassword.class);
                        Bundle extras = new Bundle();
                        extras.putInt("memberId",member_id);
                        intent.putExtras(extras);
                        startActivity(intent);
                        finish();
                    }
                    else{

                        String message = "Somthing is wrong, Try Again...";

                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            message = "Invalid code entered...";
                        }

                        Snackbar snackbar = Snackbar.make(findViewById(R.id.relParent), message, Snackbar.LENGTH_LONG);
                        snackbar.setAction("Dismiss", v -> startActivity(new Intent(VerifyResetCode.this, Login.class)));
                        snackbar.show();

                    }
                });
    }
}




