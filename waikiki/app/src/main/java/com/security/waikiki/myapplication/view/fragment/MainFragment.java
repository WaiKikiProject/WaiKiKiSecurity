package com.security.waikiki.myapplication.view.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.entitiy.UserType;
import com.security.waikiki.myapplication.view.activity.InstallSplashActivity;

@SuppressLint("ValidFragment")
public class MainFragment extends Fragment {

    private Context mContext;
    private ViewGroup mRootview;

    private UserType mUserType;
    private SecureMode mSecureMode;

    private ConstraintLayout mDefaultLayout;
    private ConstraintLayout mStatusLayout;

    private TextView mTextDeviceName;
    private TextView mTextUserType;
    private TextView mTextSMode;
    private TextView mTextevent;

    public enum SecureMode {
        SECURE,
        UNSECURE
    }

    public MainFragment(UserType userType, SecureMode secureMode) {
        mUserType = userType;
        mSecureMode = secureMode;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootview = (ViewGroup) inflater.inflate(R.layout.fragment_main_circle, container, false);

        mContext = container.getContext();

        mTextDeviceName = mRootview.findViewById(R.id.textview_device_name);
        mTextSMode = mRootview.findViewById(R.id.text_smode);
        mTextUserType = mRootview.findViewById(R.id.textview_user_type);
        mTextevent = mRootview.findViewById(R.id.text_event);

        mDefaultLayout = mRootview.findViewById(R.id.layout_install);
        mStatusLayout = mRootview.findViewById(R.id.layout_status);

        mRootview.findViewById(R.id.button_install).setOnClickListener(mOnclickListener);

        setStatusUI();

        return mRootview;
    }

    public void setStatusUI() {

        switch (mUserType) {
            case MASTER:
            case GUEST:
                mDefaultLayout.setVisibility(View.INVISIBLE);
                mStatusLayout.setVisibility(View.VISIBLE);
                break;
            case DEFAULT:
                mDefaultLayout.setVisibility(View.VISIBLE);
                mStatusLayout.setVisibility(View.INVISIBLE);
                break;
        }

        switch (mUserType) {
            case GUEST:
                mTextUserType.setText(R.string.type_guest);
                break;
            case MASTER:
                mTextUserType.setText(R.string.type_master);
                break;
        }

        if (mSecureMode != null) {
            switch (mSecureMode) {
                case SECURE:
                    mTextSMode.setText(R.string.main_smode_on);
                    break;
                case UNSECURE:
                    mTextSMode.setText(R.string.main_smode_off);
            }
        }

        String event = getString(R.string.main_event, 1);
        mTextevent.setText(event);

    }


    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_install:
                    Intent intent = new Intent(mContext, InstallSplashActivity.class);
                    mContext.startActivity(intent);
                    break;
            }

        }
    };
}
