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
import android.widget.Toast;

import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.controller.ControlCallback;
import com.security.waikiki.myapplication.controller.Task;
import com.security.waikiki.myapplication.db.RealmManager;
import com.security.waikiki.myapplication.entitiy.Device;
import com.security.waikiki.myapplication.entitiy.User;
import com.security.waikiki.myapplication.entitiy.UserType;
import com.security.waikiki.myapplication.view.activity.InstallSplashActivity;

@SuppressLint("ValidFragment")
public class MainFragment extends Fragment {

    private Context mContext;
    private ViewGroup mRootview;

    private Device mDevcie;
    private UserType mUserType;
    private SecureMode mSecureMode;

    private ConstraintLayout mDefaultLayout;
    private ConstraintLayout mStatusLayout;

    private TextView mTextDeviceName;
    private TextView mTextUserType;
    private TextView mTextSMode;
    private TextView mButtonSecurity;

    public enum SecureMode {
        SECURE,
        UNSECURE
    }

    public MainFragment(Device device) {

        if (device != null) {
            mDevcie = device;
            mUserType = device.getMaster().equals(RealmManager.getUser().getUserEmail()) ? UserType.MASTER : UserType.GUEST;
            mSecureMode = device.getSMode().equals("O") ? SecureMode.SECURE : SecureMode.UNSECURE;
        } else {
            mUserType = UserType.DEFAULT;
        }

    }

    public UserType getUserType() {
        return mUserType;
    }

    public String getDeviceID() {
        if (mDevcie == null) {
            return null;
        }
        return mDevcie.getDeviceID();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootview = (ViewGroup) inflater.inflate(R.layout.fragment_main_circle, container, false);

        mContext = container.getContext();

        mTextDeviceName = mRootview.findViewById(R.id.textview_device_name);
        mTextSMode = mRootview.findViewById(R.id.text_smode);
        mTextUserType = mRootview.findViewById(R.id.textview_user_type);
        mButtonSecurity = mRootview.findViewById(R.id.button_security);

        mDefaultLayout = mRootview.findViewById(R.id.layout_install);
        mStatusLayout = mRootview.findViewById(R.id.layout_status);

        mRootview.findViewById(R.id.button_install).setOnClickListener(mOnclickListener);
        mButtonSecurity.setOnClickListener(mOnclickListener);

        setStatusUI();

        return mRootview;
    }

    public void setStatusUI() {
        if (mDevcie != null) {
            mTextDeviceName.setText(mDevcie.getDeviceName());
        }

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
                    mButtonSecurity.setText(getString(R.string.main_unsecurity));
                    break;
                case UNSECURE:
                    mTextSMode.setText(R.string.main_smode_off);
                    mButtonSecurity.setText(getString(R.string.main_security));
                    break;
            }
        }
    }

    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_install:
                    Intent intent = new Intent(mContext, InstallSplashActivity.class);
                    mContext.startActivity(intent);
                    break;
                case R.id.button_security:
                    User user = RealmManager.getUser();
                    Task.getInstance().convertTask(user.getUserEmail(), mDevcie.getDeviceID(), convertCallback);
            }
        }
    };

    ControlCallback convertCallback = new ControlCallback() {
        @Override
        public void onSucccess() {

        }

        @Override
        public void onError(int code) {

        }

        @Override
        public void onFail() {

        }
    };
}
