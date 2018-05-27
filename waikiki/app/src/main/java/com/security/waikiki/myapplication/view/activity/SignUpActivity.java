package com.security.waikiki.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.network.ServerCallBack;
import com.security.waikiki.myapplication.network.ServerManager;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class SignUpActivity extends RootParentActivity {

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
                Toast.makeText(SignUpActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                email.requestFocus();
                return;
            }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                Toast.makeText(SignUpActivity.this,"이메일 형식이 아닙니다",Toast.LENGTH_SHORT).show();
                email.requestFocus();
                return;
            }else if(name.getText().toString().length() == 0) {
                Toast.makeText(SignUpActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                name.requestFocus();
                return;
            }else if(password.getText().toString().length() == 0) {
                Toast.makeText(SignUpActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                password.requestFocus();
                return;
            }else if(repassword.getText().toString().length() == 0) {
                Toast.makeText(SignUpActivity.this, "비밀번호를 재입력해주세요", Toast.LENGTH_SHORT).show();
                repassword.requestFocus();
                return;
            }else if(!password.getText().toString().equals(repassword.getText().toString())) {
                Toast.makeText(SignUpActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                repassword.requestFocus();
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
                Toast.makeText(SignUpActivity.this, "성공", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(SignUpActivity.this, "실패", Toast.LENGTH_LONG).show();
            }
        }
    };
}