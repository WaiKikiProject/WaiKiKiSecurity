package com.security.waikiki.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.WaiKiKi;
import com.security.waikiki.myapplication.entitiy.UserType;

public class InstallSplashActivity extends RootParentActivity {

    private ConstraintLayout mButtonUserType;
    private LinearLayout mLayoutOption;
    private View mViewArrow;
    private TextView mButtonMaster;
    private TextView mButtonGuest;
    private TextView mTextUserType;
    private TextView mTextNavi;
    private TextView mButtonInstall;

    private UserType mUserType;

    private boolean isCheck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install_splash);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayShowHomeEnabled(false);
        supportActionBar.setDisplayHomeAsUpEnabled(false);
        supportActionBar.setDisplayShowCustomEnabled(true);

        setStatusbar(toolbar);

        mButtonUserType = findViewById(R.id.button_usertype);
        mLayoutOption = findViewById(R.id.layout_option);
        mViewArrow = findViewById(R.id.view_arrow);
        mButtonMaster = findViewById(R.id.button_master);
        mButtonGuest = findViewById(R.id.button_guest);
        mTextUserType = findViewById(R.id.text_usertype);
        mTextNavi = findViewById(R.id.text_navi_sub);
        mButtonInstall = findViewById(R.id.button_install);

        mButtonUserType.setOnClickListener(mOnClickListener);
        mButtonMaster.setOnClickListener(mOnClickListener);
        mButtonGuest.setOnClickListener(mOnClickListener);
        mButtonInstall.setOnClickListener(mOnClickListener);

        isCheck = false;
    }

    private void setOptionUI() {
        if (isCheck) {
            isCheck = false;
            mLayoutOption.setVisibility(View.VISIBLE);
            mViewArrow.setBackgroundResource(R.drawable.icon_arrow_up);
        } else {
            isCheck = true;
            mLayoutOption.setVisibility(View.INVISIBLE);
            mViewArrow.setBackgroundResource(R.drawable.icon_arrow_down);
        }
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_up:
                    finish();
                    break;
                case R.id.button_usertype:
                    setOptionUI();
                    break;
                case R.id.button_master:
                    mUserType = UserType.MASTER;
                    mTextNavi.setText(getString(R.string.install_splash_master_navi));
                    mTextUserType.setText(getString(R.string.type_master));
                    setOptionUI();
                    break;
                case R.id.button_guest:
                    mUserType = UserType.GUEST;
                    mTextNavi.setText(getString(R.string.install_splash_guest_navi));
                    mTextUserType.setText(getString(R.string.type_guest));
                    setOptionUI();
                    break;
                case R.id.button_install:
                    if (mUserType != null) {
                        Intent intent;
                        if (mUserType.equals(UserType.MASTER)) {
                            intent = new Intent(InstallSplashActivity.this, InstallNameActivity.class);
                            startActivityForResult(intent, WaiKiKi.INSTALL);
                        } else {
                            intent = new Intent(InstallSplashActivity.this, InstallNFCACtivity.class);
                        }
                        startActivityForResult(intent, WaiKiKi.INSTALL);
                    }
            }
        }
    };

}
