package com.mukeshproject.zomatoguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.hbb20.CountryCodePicker;
import com.mukeshproject.zomatoguide.activities.MainActivity;

public class LoginOptionActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_SIGN_IN = 123;
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public Button sendOTP, cntEmail, cntFacebook, cntGoogle;
    private CountryCodePicker Ccp;
    private EditText et_phone;
    private String ccp, phone;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option);
        initViews();
        createRequest();
    }

    private void createRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void initViews() {

        Ccp = findViewById(R.id.ccp);
        et_phone = findViewById(R.id.et_phone);

        sendOTP = findViewById(R.id.sendOTP);
        cntEmail = findViewById(R.id.cntEmail);
        cntFacebook = findViewById(R.id.cntFacebook);
        cntGoogle = findViewById(R.id.cntGoogle);

        sendOTP.setOnClickListener(this);
        cntEmail.setOnClickListener(this);
        cntFacebook.setOnClickListener(this);
        cntGoogle.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {

            case R.id.sendOTP:
                sendOTP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ccp = Ccp.getFullNumberWithPlus();
                        phone = et_phone.getText().toString();

                        if (phone.length() == 10) {
                            Intent intent = new Intent(LoginOptionActivity.this, VerifyOTPActivity.class);
                            intent.putExtra("number_code", ccp);
                            intent.putExtra("phone_number", phone);
                            startActivity(intent);
                        } else if (phone.equals(null)) {
                            et_phone.setError("This Field cannot be Empty");
                        } else if (phone.length() < 10 || et_phone.length() > 10) {
                            et_phone.setError("Number Invalid");
                        }
                    }
                });
                break;

            case R.id.cntEmail:
                cntEmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LoginOptionActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                break;

            case R.id.cntFacebook:
                cntFacebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;

            case R.id.cntGoogle:
                cntGoogle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signIn();
                    }
                });
                break;
        }
    }
}