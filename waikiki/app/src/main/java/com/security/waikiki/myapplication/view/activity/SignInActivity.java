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

import java.util.concurrent.CountDownLatch;


public class SignInActivity extends RootParentActivity {

    private EditText edit_email, edit_password;
    private CountDownLatch mCountDownLatch;
    private String email;
    private String password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        edit_email = findViewById(R.id.edit_email);
        edit_password = findViewById(R.id.edit_password);

        findViewById(R.id.button_login).setOnClickListener(mOnclickListener);
        findViewById(R.id.button_sign_up).setOnClickListener(mOnclickListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean isValid() {

        if (edit_email.getText().toString().length() == 0) {
            Toast.makeText(SignInActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
            edit_email.requestFocus();
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edit_email.getText().toString()).matches()) {
            Toast.makeText(SignInActivity.this, "이메일 형식이 아닙니다", Toast.LENGTH_SHORT).show();
            edit_email.requestFocus();
            return false;
        } else if (edit_password.getText().toString().length() == 0) {
            Toast.makeText(SignInActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getApplicationContext(), "error : " + code, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFail() {
            Toast.makeText(getApplicationContext(), "serverfail", Toast.LENGTH_SHORT).show();
        }
    };


    ControlCallback informationCallback = new ControlCallback() {
        @Override
        public void onSucccess() {
            mCountDownLatch.countDown();
            if (checkTask()) {
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        public void onError(int code) {
            RealmManager.dumpDB();
            Toast.makeText(getApplicationContext(), "error : " + code, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFail() {
            RealmManager.dumpDB();
            Toast.makeText(getApplicationContext(), "오류", Toast.LENGTH_SHORT).show();
        }
    };

    public void loginTask() {
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