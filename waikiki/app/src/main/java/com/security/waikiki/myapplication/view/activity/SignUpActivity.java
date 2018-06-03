package com.security.waikiki.myapplication.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.security.waikiki.myapplication.WaiKiKi;
import com.security.waikiki.myapplication.controller.ControlCallback;
import com.security.waikiki.myapplication.controller.Task;
import com.security.waikiki.myapplication.util.Customdialog;
import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.network.ServerCallBack;
import com.security.waikiki.myapplication.network.ServerManager;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class SignUpActivity extends RootParentActivity {

    private Customdialog dialog;
    EditText email, password, name, repassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = (EditText) findViewById(R.id.edittext_signup_email);
        password = (EditText) findViewById(R.id.edittext_signup_password);
        name = (EditText) findViewById(R.id.edittext_signup_name);
        repassword = (EditText) findViewById(R.id.edittext_signup_repassword);


        findViewById(R.id.button_sign_in_login).setOnClickListener(mOnclickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_sign_in_login:
                    String title = getString(R.string.dialog_signup_title);
                    if (email.getText().toString().length() == 0) {
                        WaiKiKi.showDialog(SignUpActivity.this, getString(R.string.dialog_email_mesgase), null);
                        return;
                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                        WaiKiKi.showDialog(SignUpActivity.this, getString(R.string.dialog_email_mesgase), null);
                        dialog.show();
                        return;
                    } else if (name.getText().toString().length() == 0) {
                        WaiKiKi.showDialog(SignUpActivity.this, getString(R.string.dialog_email_mesgase), null);
                        dialog.show();
                        return;
                    } else if (password.getText().toString().length() == 0) {
                        WaiKiKi.showDialog(SignUpActivity.this, getString(R.string.dialog_email_mesgase), null);
                        dialog.show();
                        return;
                    } else if (repassword.getText().toString().length() == 0) {
                        WaiKiKi.showDialog(SignUpActivity.this, getString(R.string.dialog_email_mesgase), null);
                        dialog.show();
                        return;
                    } else if (!password.getText().toString().equals(repassword.getText().toString())) {
                        WaiKiKi.showDialog(SignUpActivity.this, getString(R.string.dialog_email_mesgase), null);
                        dialog.show();
                        return;
                    } else {
                        signupTask(email.getText().toString(), name.getText().toString(), password.getText().toString());
                    }
                    break;
            }
        }
    };

    public void signupTask(String email, String name, String password) {
        Task.getInstance().signupTask(email, name, password, signupCallback);
    }

    ControlCallback signupCallback = new ControlCallback() {
        @Override
        public void onSucccess() {
            String title = getString(R.string.dialog_signup_title);
            String message = getString(R.string.dialog_signup_success_mesgse);
            WaiKiKi.showDialog(SignUpActivity.this, title, message, mOnDismissListener);
        }

        @Override
        public void onError(int code) {
            String title = getString(R.string.dialog_signup_title);
            switch (code) {

            }

//            WaiKiKi.showDialog(SignUpActivity.this, title, code, null);
        }

        @Override
        public void onFail() {

        }
    };

    DialogInterface.OnDismissListener mOnDismissListener = new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialogInterface) {
            finish();
        }
    };

}