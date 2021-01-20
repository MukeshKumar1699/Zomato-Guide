package com.mukeshproject.zomatoguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private EditText et_Email_login, et_Password_login;
    private Button btn_login, btn_signUp;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {

        et_Email_login = findViewById(R.id.et_Email_login);
        et_Password_login = findViewById(R.id.et_Password_login);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        btn_signUp = findViewById(R.id.btn_signUp);
        btn_signUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {

            case R.id.btn_signUp:
                Intent signUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
                break;

            case R.id.btn_login:

                email = et_Email_login.getText().toString();
                password = et_Password_login.getText().toString();
                Log.d("Mukesh", email + " " + password);
                if (!email.equals(null) && !password.equals(null)) {
                    signIn(email, password);
                }
                break;
        }
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), user.getDisplayName(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "No user Detected", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}