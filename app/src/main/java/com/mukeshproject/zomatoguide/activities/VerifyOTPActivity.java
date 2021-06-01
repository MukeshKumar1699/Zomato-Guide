package com.mukeshproject.zomatoguide.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mukeshproject.zomatoguide.R;

import java.util.concurrent.TimeUnit;

public class VerifyOTPActivity extends AppCompatActivity {

    private static final String TAG = "VerifyOTPActivity";

    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";

    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_CODE_SENT = 2;
    private static final int STATE_VERIFY_FAILED = 3;
    private static final int STATE_VERIFY_SUCCESS = 4;
    private static final int STATE_SIGNIN_FAILED = 5;
    private static final int STATE_SIGNIN_SUCCESS = 6;

    FirebaseAuth mAuth;

    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private EditText et_opt;
    private Button validate;
    private String phoneNumber, numberCode, otp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);
        initViews();
        getIntentData();
        startPhoneNumberVerification(phoneNumber);
    }

    private void startPhoneNumberVerification(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber) // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this) // Activity (for callback binding)
                        .setCallbacks(mCallbacks) // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

        mVerificationInProgress = true;
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .setForceResendingToken(token)     // ForceResendingToken from callbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            updateUI(STATE_SIGNIN_SUCCESS, user);
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                et_opt.setError("Invalid code.");
                            }
                            // Update UI
                            updateUI(STATE_SIGNIN_FAILED);
                        }
                    }
                });
    }

    private void updateUI(int uiState) {
        updateUI(uiState, mAuth.getCurrentUser(), null);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            updateUI(STATE_SIGNIN_SUCCESS, user);
        } else {
            updateUI(STATE_INITIALIZED);
        }
    }

    private void updateUI(int uiState, FirebaseUser user) {
        updateUI(uiState, user, null);
    }

    private void updateUI(int uiState, PhoneAuthCredential cred) {
        updateUI(uiState, null, cred);
    }

    private void updateUI(int uiState, FirebaseUser user, PhoneAuthCredential cred) {
        switch (uiState) {
            case STATE_INITIALIZED:
                // Initialized state, show only the phone number field and start button
//                enableViews(mBinding.buttonStartVerification, mBinding.fieldPhoneNumber);
//                disableViews(mBinding.buttonVerifyPhone, mBinding.buttonResend, mBinding.fieldVerificationCode);
//                mBinding.detail.setText(null);
                break;
            case STATE_CODE_SENT:
                // Code sent state, show the verification field, the
//                enableViews(mBinding.buttonVerifyPhone, mBinding.buttonResend, mBinding.fieldPhoneNumber, mBinding.fieldVerificationCode);
//                disableViews(mBinding.buttonStartVerification);
//                mBinding.detail.setText(R.string.status_code_sent);
                break;
            case STATE_VERIFY_FAILED:
                // Verification has failed, show all options
//                enableViews(mBinding.buttonStartVerification, mBinding.buttonVerifyPhone, mBinding.buttonResend, mBinding.fieldPhoneNumber,
//                        mBinding.fieldVerificationCode);
//                mBinding.detail.setText(R.string.status_verification_failed);
                break;
            case STATE_VERIFY_SUCCESS:
                // Verification has succeeded, proceed to firebase sign in
//                disableViews(mBinding.buttonStartVerification, mBinding.buttonVerifyPhone, mBinding.buttonResend, mBinding.fieldPhoneNumber,
//                        mBinding.fieldVerificationCode);
//                mBinding.detail.setText(R.string.status_verification_succeeded);

                // Set the verification text based on the credential
                if (cred != null) {
                    if (cred.getSmsCode() != null) {
                        et_opt.setText(cred.getSmsCode());
                    } else {
                        et_opt.setText(R.string.instant_validation);
                    }
                }

                break;
            case STATE_SIGNIN_FAILED:
                // No-op, handled by sign-in check
//                mBinding.detail.setText(R.string.status_sign_in_failed);
                break;
            case STATE_SIGNIN_SUCCESS:
                // Np-op, handled by sign-in check
                break;
        }

        if (user == null) {
            // Signed out
//            mBinding.phoneAuthFields.setVisibility(View.VISIBLE);
//            mBinding.signOutButton.setVisibility(View.GONE);
//
//            mBinding.status.setText(R.string.signed_out);
        } else {
            // Signed in
//            mBinding.phoneAuthFields.setVisibility(View.GONE);
//            mBinding.signOutButton.setVisibility(View.VISIBLE);
//
//            enableViews(mBinding.fieldPhoneNumber, mBinding.fieldVerificationCode);
//            mBinding.fieldPhoneNumber.setText(null);
            et_opt.setText(null);

//            mBinding.status.setText(R.string.signed_in);
//            mBinding.detail.setText(getString(R.string.firebase_status_fmt, user.getUid()));
        }
    }


    private void initViews() {
        et_opt = findViewById(R.id.et_otp);
        validate = findViewById(R.id.validateOTP);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp = et_opt.getText().toString();

                if (otp.length() == 6) {

                } else {
                    et_opt.setError("Invalid OTP");
                }
            }
        });
    }

    private void getIntentData() {

        if (getIntent().getStringExtra("phone_number") != null && getIntent().getStringExtra("number_code") != null) {
            phoneNumber = numberCode + getIntent().getStringExtra("phoneNumber");
        }
    }
}