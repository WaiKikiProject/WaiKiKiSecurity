package com.example.kyungmin.dps.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kyungmin.dps.R;
import com.example.kyungmin.dps.view.fragment.ImageFragment;

public class EventImageFragment extends android.support.v4.app.Fragment {

    public EventImageFragment newInstance(int eventUrl) {

        Bundle args = new Bundle();
        args.putInt("eventUrl", eventUrl);

        EventImageFragment fragment = new EventImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.eventviewpager, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.eventimage);
        imageView.setImageResource(getArguments().getInt("eventUrl"));
        return view;
    }

}

