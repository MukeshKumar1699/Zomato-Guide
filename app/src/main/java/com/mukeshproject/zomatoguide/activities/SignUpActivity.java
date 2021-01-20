package com.mukeshproject.zomatoguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mukeshproject.zomatoguide.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_Email_signUp, et_Password_signUp, et_cnfmPassword_signUp;
    private Button btn_createAccount;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    private void initViews() {

        mAuth = FirebaseAuth.getInstance();

        et_Email_signUp = findViewById(R.id.et_Email_signUp);
        et_Password_signUp = findViewById(R.id.et_Password_signUp);
        et_cnfmPassword_signUp = findViewById(R.id.et_ConfirmPassword_signUp);

        btn_createAccount = findViewById(R.id.btn_createAccount_signUp);
        btn_createAccount.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {

            case R.id.btn_createAccount_signUp:

                String email = et_Email_signUp.getText().toString().trim();
                String password = et_Password_signUp.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    et_Email_signUp.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    et_Password_signUp.setError("Password is Required");
                    return;
                }

                if (password.length() < 6) {
                    et_Password_signUp.setError("Password lenth must be greater than 6");
                    return;
                }

                if (!password.equals(et_cnfmPassword_signUp.getText().toString().trim())) {
                    et_cnfmPassword_signUp.setError("Password Mis Match");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }
    }


}