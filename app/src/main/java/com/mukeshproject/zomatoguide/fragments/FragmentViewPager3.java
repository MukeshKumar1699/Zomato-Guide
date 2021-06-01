package com.mukeshproject.zomatoguide.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.activities.LoginOptionActivity;

import org.jetbrains.annotations.NotNull;


public class FragmentViewPager3 extends Fragment {

    private TextView tv_getStarted;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_getStarted = view.findViewById(R.id.tv_getStarted);

        tv_getStarted.setOnClickListener(v -> {

            Intent intent = new Intent(getActivity(), LoginOptionActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
    }
}