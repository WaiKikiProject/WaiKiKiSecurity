package com.security.waikiki.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.WaiKiKi;
import com.security.waikiki.myapplication.controller.ControlCallback;
import com.security.waikiki.myapplication.controller.Task;
import com.security.waikiki.myapplication.db.RealmManager;
import com.security.waikiki.myapplication.util.CustomProgress;

import java.util.concurrent.CountDownLatch;


public class SignInActivity extends RootParentActivity {

    private EditText edit_email, edit_password;
    private CountDownLatch mCountDownLatch;
    private String email;
    private String password;

    public CustomProgress mProgrss;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        edit_email = findViewById(R.id.edit_email);
        edit_password = findViewById(R.id.edit_password);

        findViewById(R.id.button_login).setOnClickListener(mOnclickListener);
        findViewById(R.id.button_sign_up).setOnClickListener(mOnclickListener);

        mProgrss = new CustomProgress(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean isValid() {
        String title = getString(R.string.dialog_signup_title);
        if (edit_email.getText().toString().length() == 0) {
            WaiKiKi.showDialog(SignInActivity.this, getString(R.string.dialog_email_message), null);
            edit_email.requestFocus();
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edit_email.getText().toString()).matches()) {
            WaiKiKi.showDialog(SignInActivity.this, getString(R.string.dialog_email_message_form), null);
            edit_email.requestFocus();
            return false;
        } else if (edit_password.getText().toString().length() == 0) {
            WaiKiKi.showDialog(SignInActivity.this, getString(R.string.dialog_password_message), null);
            edit_password.requestFocus();
            return false;
        }
        return true;
    }

    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;

            switch (view.getId()) {
                case R.id.button_login:
                    if (isValid()) {
                        email = edit_email.getText().toString();
                        password = edit_password.getText().toString();
                        loginTask();
                    }
                    break;
                case R.id.button_sign_up:
                    intent = new Intent(SignInActivity.this, SignUpActivity.class);
                    startActivityForResult(intent, WaiKiKi.SIGN_IN);
                    break;

            }
        }
    };
    ControlCallback loginCallback = new ControlCallback() {
        @Override
        public void onSucccess() {
            mCountDownLatch.countDown();
            if (checkTask()) {
                getInformationTask();
            }
        }

        @Override
        public void onError(int code) {
            switch (code) {
                case 402:
                    WaiKiKi.showDialog(SignInActivity.this, getString(R.string.dialog_error_not_exist_mail_message), null);
                    break;
                case 403:
                    WaiKiKi.showDialog(SignInActivity.this, getString(R.string.dialog_error_diss_match_login_message), null);
                    break;
            }

            mProgrss.dismiss();
        }
        @Override
        public void onFail() {
            WaiKiKi.showDialog(SignInActivity.this, getString(R.string.dialog_error_not_dis_match_error_message), null);
            mProgrss.dismiss();
        }
    };


    ControlCallback informationCallback = new ControlCallback() {
        @Override
        public void onSucccess() {
            mCountDownLatch.countDown();
            if (checkTask()) {
                Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                mProgrss.dismiss();
            }
        }

        @Override
        public void onError(int code) {
            RealmManager.dumpDB();
            String title = getString(R.string.dialog_signin_title);
            switch (code) {
                case 402 :
                    WaiKiKi.showDialog(SignInActivity.this, title,getString(R.string.dialog_error_not_exist_mail_message), null);
                    break;
                case 403 :
                    WaiKiKi.showDialog(SignInActivity.this, title,getString(R.string.dialog_error_diss_match_login_message), null);
                    break;

            }
            mProgrss.dismiss();
        }

        @Override
        public void onFail() {
            RealmManager.dumpDB();
            WaiKiKi.showDialog(SignInActivity.this, getString(R.string.dialog_error_not_dis_match_error_message), null);
            mProgrss.dismiss();
        }
    };

    public void loginTask() {
        mProgrss.show();
        mCountDownLatch = new CountDownLatch(1);
        Task.getInstance().loginTask(email, password, loginCallback);
    }

    public void getInformationTask(){
        mCountDownLatch = new CountDownLatch(1);
        Task.getInstance().getInstallTask(email,informationCallback);
    }

    public boolean checkTask() {
        return mCountDownLatch.getCount() <= 0 ? true : false;
    }
}