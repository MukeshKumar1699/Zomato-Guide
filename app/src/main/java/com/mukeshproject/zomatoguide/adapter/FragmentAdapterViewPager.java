package com.mukeshproject.zomatoguide.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mukeshproject.zomatoguide.fragments.FragmentViewPager1;
import com.mukeshproject.zomatoguide.fragments.FragmentViewPager2;
import com.mukeshproject.zomatoguide.fragments.FragmentViewPager3;

public class FragmentAdapterViewPager extends FragmentPagerAdapter {


    public FragmentAdapterViewPager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentViewPager1 fragmentViewPager1 = new FragmentViewPager1();
                return fragmentViewPager1;
            case 1:
                FragmentViewPager2 fragmentViewPager2 = new FragmentViewPager2();
                return fragmentViewPager2;
            case 2:
                FragmentViewPager3 fragmentViewPager3 = new FragmentViewPager3();
                return fragmentViewPager3;
        }
        return FragmentViewPager1.newInstance("This is First Fragment", "");
    }

    @Override
    public int getCount() {
        return 3;
    }
}
