package com.example.kyungmin.dps.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kyungmin.dps.R;
import com.example.kyungmin.dps.view.entity.MainStatus;
import com.example.kyungmin.dps.view.entity.UserType;

/**
 * Created by user on 2018-04-09.
 */

@SuppressLint("ValidFragment")
public class ImageFragment extends android.support.v4.app.Fragment{

    private View mRootView;

    private MainStatus mStatus;
    private UserType mUserType;

    private ImageView mImageViewMain;

    public ImageFragment(MainStatus status, UserType usertype){
        mStatus = status;
        mUserType = usertype;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mRootView =  inflater.inflate(R.layout.imagefragment,container,false);

        mImageViewMain = mRootView.findViewById(R.id.imageview_main);

        switch(mStatus){
            case SECURE:
                mImageViewMain.setImageResource(R.drawable.ic_account_circle_black);
                break;
            case UNSECUR:
                mImageViewMain.setImageResource(R.drawable.home1);
                break;
            case INSTALL:
                mImageViewMain.setImageResource(R.drawable.home2);
                break;

        }

        return mRootView;
    }

    public MainStatus getStatus(){
        return mStatus;
    }

    public UserType getUserType(){
        return mUserType;
    }

}