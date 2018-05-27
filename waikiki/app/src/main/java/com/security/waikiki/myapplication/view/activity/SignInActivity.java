package com.security.waikiki.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.WaiKiKi;
import com.security.waikiki.myapplication.network.ServerCallBack;
import com.security.waikiki.myapplication.network.ServerManager;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class SignInActivity extends RootParentActivity {

    EditText edit_email, edit_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_password = (EditText) findViewById(R.id.edit_password);

        findViewById(R.id.button_login).setOnClickListener(mOnclickListener);
        findViewById(R.id.button_sign_up).setOnClickListener(mOnclickListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;

            switch (view.getId()) {
                case R.id.button_login:
//                    if (edit_email.getText().toString().length() == 0) {
//                        Toast.makeText(SignInActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
//                        edit_email.requestFocus();
//                        return;
//                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edit_email.getText().toString()).matches()) {
//                        Toast.makeText(SignInActivity.this, "이메일 형식이 아닙니다", Toast.LENGTH_SHORT).show();
//                        edit_email.requestFocus();
//                        return;
//                    } else if (edit_password.getText().toString().length() == 0) {
//                        Toast.makeText(SignInActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
//                        edit_password.requestFocus();
//                        return;
//                    } else {
//                        ServerManager.getInstanse().loginMethod(callBack, edit_email.getText().toString(), edit_password.getText().toString());
//                    }
//                    break;
                    intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.button_sign_up:
                    intent = new Intent(SignInActivity.this, SignUpActivity.class);
                    startActivityForResult(intent, WaiKiKi.SIGN_IN);
                    break;

            }
        }
    };

    ServerCallBack<ResponseBody> callBack = new ServerCallBack<ResponseBody>() {
        @Override
        public void onResponseResult(Response<ResponseBody> response) {
            Intent intent;
            if (response.isSuccessful()) {
                intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(SignInActivity.this, "성공", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(SignInActivity.this, "실패", Toast.LENGTH_LONG).show();
            }
        }
    };
}