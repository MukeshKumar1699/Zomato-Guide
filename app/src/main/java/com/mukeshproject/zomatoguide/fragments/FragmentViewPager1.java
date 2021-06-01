package com.mukeshproject.zomatoguide.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.activities.LoginOptionActivity;

import org.jetbrains.annotations.NotNull;


public class FragmentViewPager1 extends Fragment {

    private TextView tv_skip;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FragmentViewPager1() {
        // Required empty public constructor
    }


    public static FragmentViewPager1 newInstance(String param1, String param2) {
        FragmentViewPager1 fragment = new FragmentViewPager1();
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
        return inflater.inflate(R.layout.fragment_view_pager1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_skip = view.findViewById(R.id.tv_skip);

        tv_skip.setOnClickListener(v -> {

            Intent intent = new Intent(getActivity(), LoginOptionActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
    }
}