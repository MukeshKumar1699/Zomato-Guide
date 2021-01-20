package com.mukeshproject.zomatoguide.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mukeshproject.zomatoguide.fragments.GoOutFragment;
import com.mukeshproject.zomatoguide.fragments.OrderFragment;
import com.mukeshproject.zomatoguide.fragments.ProfileFragment;

public class FragmentAdapterMainActivity extends FragmentStatePagerAdapter {

    public FragmentAdapterMainActivity(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                OrderFragment orderFragment = new OrderFragment();
                return orderFragment;
            case 1:

                GoOutFragment goOutFragment = new GoOutFragment();
                return goOutFragment;
            case 2:

                return new ProfileFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String tabTitle;

        switch (position) {
            case 0:
                tabTitle = "Order";
                break;
            case 1:
                tabTitle = "Go Out";
                break;
            case 2:
                tabTitle = "Profile";
                break;
            default:
                tabTitle = "TAB - n";
                break;
        }
        return tabTitle;
    }


}
