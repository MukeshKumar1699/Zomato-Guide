package com.mukeshproject.zomatoguide.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mukeshproject.zomatoguide.fragments.FragmentViewPager1;
import com.mukeshproject.zomatoguide.fragments.FragmentViewPager2;
import com.mukeshproject.zomatoguide.fragments.FragmentViewPager3;

public class FragmentAdapterViewPager extends FragmentStatePagerAdapter {


    public FragmentAdapterViewPager(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentViewPager1();
            case 1:
                return new FragmentViewPager2();
            case 2:
                return new FragmentViewPager3();
        }
        return FragmentViewPager1.newInstance("This is First Fragment", "");
    }

    @Override
    public int getCount() {
        return 3;
    }
}
