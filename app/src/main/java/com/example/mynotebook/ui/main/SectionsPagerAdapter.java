package com.example.mynotebook.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mynotebook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    private static final List<String> FRAGMENT_TITLES = new ArrayList<>();
    private static final List<Fragment> FRAGMENT_LIST = new ArrayList<>();

    private final Context mContext;


    public void addFragment(Fragment fragment, String title) {

        FRAGMENT_LIST.add(fragment);
        FRAGMENT_TITLES.add(title);

    }

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //return PlaceholderFragment.newInstance(position);
        return FRAGMENT_LIST.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FRAGMENT_TITLES.get(position);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return FRAGMENT_LIST.size();
    }
}