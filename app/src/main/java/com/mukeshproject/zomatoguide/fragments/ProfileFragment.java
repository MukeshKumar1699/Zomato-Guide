package com.mukeshproject.zomatoguide.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.activities.LoginOptionActivity;


public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private TextView tv_Logout, tv_userName, tv_emailId;
    private ImageView iv_accProfilePic;
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

    }

    private void initViews(View view) {

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getContext());
        tv_userName = view.findViewById(R.id.tv_userName);
        tv_emailId = view.findViewById(R.id.tv_emailId);
        iv_accProfilePic = view.findViewById(R.id.iv_accProfilePic);

        if (signInAccount != null) {
            tv_userName.setText(signInAccount.getDisplayName());
            tv_emailId.setText(signInAccount.getEmail());
            Glide.with(getContext()).load(signInAccount.getPhotoUrl()).into(iv_accProfilePic);
        }

        tv_Logout = view.findViewById(R.id.tv_Logout);
        tv_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(getContext(), LoginOptionActivity.class);
                startActivity(intent);
            }
        });
    }
}