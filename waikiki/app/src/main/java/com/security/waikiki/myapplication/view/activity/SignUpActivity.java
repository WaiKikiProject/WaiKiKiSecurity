package com.security.waikiki.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.security.waikiki.myapplication.Customdialog;
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
                dialog = new Customdialog(SignUpActivity.this,"이메일", "이메일을 입력해주세요.", mOnclickListener);
                dialog.show();
                return;
            }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                dialog = new Customdialog(SignUpActivity.this,"이메일","이메일 형식이 아닙니다.", mOnclickListener);
                dialog.show();
                return;
            }else if(name.getText().toString().length() == 0) {
                dialog = new Customdialog(SignUpActivity.this, "이름","이름을 입력해주세요.", mOnclickListener);
                dialog.show();
                return;
            }else if(password.getText().toString().length() == 0) {
                dialog = new Customdialog(SignUpActivity.this, "비밀번호","비밀번호를 입력해주세요.", mOnclickListener);
                dialog.show();
                return;
            }else if(repassword.getText().toString().length() == 0) {
                dialog = new Customdialog(SignUpActivity.this, "비밀번호 확인","비밀번호를 재입력 해주세요.", mOnclickListener);
                dialog.show();
                return;
            }else if(!password.getText().toString().equals(repassword.getText().toString())) {
                dialog = new Customdialog(SignUpActivity.this, "비밀번호 확인","비밀번호가 일치 하지않습니다.", mOnclickListener);
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
                intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
                dialog = new Customdialog(SignUpActivity.this, "회원가입 성공","회원가입에 성공 했습니다.", mOnclickListener);
            } else {
                dialog = new Customdialog(SignUpActivity.this, "회원가입 실패","회원가입에 실패 했습니다.", mOnclickListener);
            }
        }
    };
}