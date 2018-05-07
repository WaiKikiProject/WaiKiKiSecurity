package com.example.kyungmin.dps.view.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kyungmin.dps.R;
import com.example.kyungmin.dps.view.fragment.EventImageFragment;
import com.example.kyungmin.dps.view.fragment.ImageFragment;

import java.util.ArrayList;
import java.util.List;

public class EventViewpagerAdpater extends FragmentStatePagerAdapter {

    int image [] = {R.drawable.eventdoor,R.drawable.eventdoor,R.drawable.eventdoor,R.drawable.eventdoor,R.drawable.eventdoor,R.drawable.eventdoor,R.drawable.eventdoor,R.drawable.eventdoor,R.drawable.eventdoor};

    public EventViewpagerAdpater(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new EventImageFragment().newInstance(image[position]);
    }

    @Override
    public int getCount() {
        return image.length;
    }

}

