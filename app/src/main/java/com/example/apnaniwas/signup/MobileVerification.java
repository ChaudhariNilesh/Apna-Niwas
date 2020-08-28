package com.example.apnaniwas.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apnaniwas.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class MobileVerification extends AppCompatActivity {
    private String mVerificationID;
    private EditText editTextOTP;
    private FirebaseAuth mAuth;
    String Testmobile = "505554567";
    String TestsmsCode="123456";
    private String mobile;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private TextView textViewOtpTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_verify);

        editTextOTP = findViewById(R.id.editTextCode);
        //textViewOtpTimer = findViewById(R.id.textViewOtpTimer);
        TextView tvMobileDisp = findViewById(R.id.tvMobileDisp);

        mAuth = FirebaseAuth.getInstance();
     //   FirebaseAuthSettings firebaseAuthSettings = mAuth.getFirebaseAuthSettings(); // FOR TESTING DISABLE LATER

        Intent intent = getIntent();
        mobile = intent.getStringExtra("New Mobile");
        tvMobileDisp.setText(mobile);

        // mobile = intent.getStringExtra("mobile");


     //   firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(Testmobile, TestsmsCode); // FOR TESTING DISABLE LATER
        sendVerificationCode(mobile);


        findViewById(R.id.btnOTPDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mOTPCode = editTextOTP.getText().toString().trim();
                if(mOTPCode.isEmpty() || mOTPCode.length() <6) {
                    editTextOTP.setError("Enter valid code");
                    editTextOTP.requestFocus();
                    return;
                }
                verifyVerificationCode(mOTPCode);     //MANUAL OTP BY USER

            }
        });

        findViewById(R.id.btnOTPResend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resendOtpRequest(mobile,mResendToken);

            }
        });
    }
   /* public void otpTimer()
    {
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                textViewOtpTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                textViewOtpTimer.setText("done!");
            }
        }.start();
    }*/

    private void resendOtpRequest(String mobile, PhoneAuthProvider.ForceResendingToken mResendToken) {
    //    otpTimer();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks,
                mResendToken);
    }


    private void sendVerificationCode(String mobile) {
    //    otpTimer();
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
                        editTextOTP.setText(mOTPCode);
                        verifyVerificationCode(mOTPCode);
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(MobileVerification.this,e.getMessage(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCodeSent(@NotNull String s, @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);

                    //storing the verification id that is sent to the user
                    mVerificationID = s;
                    mResendToken = forceResendingToken;

                }
            };


    private void verifyVerificationCode(String mOTPCode) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationID,mOTPCode);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(MobileVerification.this,
                new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Intent intent = new Intent(getApplicationContext(), Signup.class);
                            intent.putExtra("VERIFIED MOBILENO", mobile);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            
                            String message = "Somthing is wrong, Try Again...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }

                            Snackbar snackbar = Snackbar.make(findViewById(R.id.relParent), message, Snackbar.LENGTH_LONG);
                            snackbar.setAction("Dismiss", v -> startActivity(new Intent(MobileVerification.this,MobileRegistration.class)));
                            snackbar.show();

                        }
                    }
                });
            }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}
