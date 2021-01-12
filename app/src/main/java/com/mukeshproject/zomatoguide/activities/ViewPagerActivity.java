package com.mukeshproject.zomatoguide.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.cuberto.liquid_swipe.LiquidPager;
import com.google.android.material.tabs.TabLayout;
import com.mukeshproject.zomatoguide.R;

public class ViewPagerActivity extends AppCompatActivity {

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    LiquidPager liquidPager;
    ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

    }
}