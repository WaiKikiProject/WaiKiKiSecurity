package com.security.waikiki.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

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

        email = (EditText)findViewById(R.id.edittext_signup_email);
        password = (EditText)findViewById(R.id.edittext_signup_password);
        name = (EditText)findViewById(R.id.edittext_signup_name);
        repassword = (EditText)findViewById(R.id.edittext_signup_repassword);


        findViewById(R.id.button_sign_in_login).setOnClickListener(mOnclickListener);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (email.getText().toString().length() == 0) {
                dialog = new Customdialog(SignUpActivity.this,getString(R.string.dialog_email_title), getString(R.string.dialog_email_mesgase), mOnclickListener);
                dialog.show();
                return;
            }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                dialog = new Customdialog(SignUpActivity.this,getString(R.string.dialog_email_title), getString(R.string.dialog_email_mesgase_form), mOnclickListener);
                dialog.show();
                return;
            }else if(name.getText().toString().length() == 0) {
                dialog = new Customdialog(SignUpActivity.this,getString(R.string.dialog_name_title), getString(R.string.dialog_name_mesgase), mOnclickListener);
                dialog.show();
                return;
            }else if(password.getText().toString().length() == 0) {
                dialog = new Customdialog(SignUpActivity.this,getString(R.string.dialog_password_title), getString(R.string.dialog_password_mesgase), mOnclickListener);
                dialog.show();
                return;
            }else if(repassword.getText().toString().length() == 0) {
                dialog = new Customdialog(SignUpActivity.this,getString(R.string.dialog_repassword_title), getString(R.string.dialog_repassword_mesgase), mOnclickListener);
                dialog.show();
                return;
            }else if(!password.getText().toString().equals(repassword.getText().toString())) {
                dialog = new Customdialog(SignUpActivity.this,getString(R.string.dialog_password_confirm_title), getString(R.string.dialog_password_confirm_mesgase), mOnclickListener);
                dialog.show();
                return;
            }else {
                ServerManager.getInstanse().signUpMethod(callBack,email.getText().toString() ,name.getText().toString(), password.getText().toString());
            }


        }
    };
     ServerCallBack<ResponseBody> callBack = new ServerCallBack<ResponseBody>() {
        @Override
        public void onResponseResult(Response<ResponseBody> response) {
            Intent intent;
            if (response.isSuccessful()) {
                dialog = new Customdialog(SignUpActivity.this,getString(R.string.dialog_signup_success_title), getString(R.string.dialog_signup_success_mesgse), mOnclickListener);
                dialog.show();
                intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            } else {
                dialog = new Customdialog(SignUpActivity.this,getString(R.string.dialog_signup_fail_title), getString(R.string.dialog_signup_fail_mesgse), mOnclickListener);
                dialog.show();
            }
        }
    };
}