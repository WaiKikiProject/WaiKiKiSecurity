package com.example.kyungmin.dps.view.fragment;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kyungmin.dps.R;
import com.example.kyungmin.dps.view.fragment.ImageFragment;

import java.util.List;

/**
 * Created by user on 2018-04-09.
 */

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {


    List<ImageFragment> mListFragments;



    public FragmentPagerAdapter(FragmentManager fm, List<ImageFragment> items) {
        super(fm);
        mListFragments = items;
    }

    @Override
    public ImageFragment getItem(int position) {
        return mListFragments.get(position);
    }

    @Override
    public int getCount() {
        return mListFragments.size();
    }

}