package com.mukeshproject.zomatoguide.activities;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.cuberto.liquid_swipe.LiquidPager;
import com.google.android.material.tabs.TabLayout;
import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.adapter.FragmentAdapterViewPager;

public class ViewPagerActivity extends AppCompatActivity {

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    LiquidPager liquidPager;
    FragmentAdapterViewPager fragmentAdapterViewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_view_pager);

        liquidPager = findViewById(R.id.liquidPager);

        fragmentAdapterViewPager = new FragmentAdapterViewPager(fragmentManager);
        liquidPager.setAdapter(fragmentAdapterViewPager);
    }
}