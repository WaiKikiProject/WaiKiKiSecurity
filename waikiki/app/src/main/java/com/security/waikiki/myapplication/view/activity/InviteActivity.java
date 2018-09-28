package com.security.waikiki.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.WaiKiKi;
import com.security.waikiki.myapplication.controller.ControlCallback;
import com.security.waikiki.myapplication.controller.Task;

public class InviteActivity extends RootParentActivity {

    String mDevice_ID;

    EditText mEditEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayShowHomeEnabled(false);
        supportActionBar.setDisplayHomeAsUpEnabled(false);
        supportActionBar.setDisplayShowCustomEnabled(true);

        findViewById(R.id.button_up).setOnClickListener(mOnClickListener);

        mEditEmail = findViewById(R.id.edit_email);
        findViewById(R.id.button_invite).setOnClickListener(mOnClickListener);

        Intent intent = getIntent();
        mDevice_ID = intent.getStringExtra("DeviceID");

    }

    private boolean checkEmailFormat() {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mEditEmail.getText().toString()).matches() ? true : false;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_up:
                    finish();
                    break;
                case R.id.button_invite:
                    if (checkEmailFormat()) {
                        inviteTask();
                    }
            }
        }
    };

    private void inviteTask() {
        Task.getInstance().inviteTask(mEditEmail.getText().toString(), mDevice_ID, inviteCallback);
    }

    ControlCallback inviteCallback = new ControlCallback() {
        @Override
        public void onSucccess() {
            String title = getString(R.string.dialog_signup_title);
            WaiKiKi.showDialog(InviteActivity.this, title, getString(R.string.dialog_deivce_success_message), null);
        }

        @Override
        public void onError(int code) {
            String title = getString(R.string.dialog_signup_title);
            switch (code) {
                case 403:
                    WaiKiKi.showDialog(InviteActivity.this, title, getString(R.string.dialog_error_diss_match_login_message), null);
                    break;
                case 405:
                    WaiKiKi.showDialog(InviteActivity.this, title, getString(R.string.dialog_error_not_exist_deivce_message), null);
                    break;
                case 406:
                    WaiKiKi.showDialog(InviteActivity.this, title, getString(R.string.dialog_error_not_exist_user_message), null);
                    break;
                case 408:
                    WaiKiKi.showDialog(InviteActivity.this, title, getString(R.string.dialog_error_not_install_user_message), null);
                    break;
                case 409:
                    WaiKiKi.showDialog(InviteActivity.this, title, getString(R.string.dialog_error_aleady_install_device_message), null);
                    break;
                case 410:
                    WaiKiKi.showDialog(InviteActivity.this, title, getString(R.string.dialog_error_aleady_install_master_message), null);
                    break;
                case 411:
                    WaiKiKi.showDialog(InviteActivity.this, title, getString(R.string.dialog_error_not_install_master_message), null);
                    break;
                case 500:
                    WaiKiKi.showDialog(InviteActivity.this, title, getString(R.string.dialog_error_not_dis_match_error_message), null);
                    break;

            }

        }

        @Override
        public void onFail() {
            Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
        }
    };
}
