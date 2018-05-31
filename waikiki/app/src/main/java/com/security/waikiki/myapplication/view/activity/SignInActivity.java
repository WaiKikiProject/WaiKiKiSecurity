package com.security.waikiki.myapplication.view.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.WaiKiKi;
import com.security.waikiki.myapplication.controller.ControlCallback;
import com.security.waikiki.myapplication.controller.Task;
import com.security.waikiki.myapplication.network.ServerCallBack;
import com.security.waikiki.myapplication.network.ServerManager;

import java.util.concurrent.CountDownLatch;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class SignInActivity extends RootParentActivity {

    EditText edit_email, edit_password;

    CountDownLatch mCountDownLatch;

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

    public boolean isValid() {

//        if (edit_email.getText().toString().length() == 0) {
//            Toast.makeText(SignInActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
//            edit_email.requestFocus();
//            return false;
//        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edit_email.getText().toString()).matches()) {
//            Toast.makeText(SignInActivity.this, "이메일 형식이 아닙니다", Toast.LENGTH_SHORT).show();
//            edit_email.requestFocus();
//            return false;
//        } else if (edit_password.getText().toString().length() == 0) {
//            Toast.makeText(SignInActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
//            edit_password.requestFocus();
//            return false;
//        }

        return true;

    }


    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;

            switch (view.getId()) {
                case R.id.button_login:
                    if (isValid()) {
                        new LoginTask().execute();
                    }
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
            if (response.isSuccessful()) {
                mCountDownLatch.countDown();
            } else {
            }
        }
    };

    ControlCallback controlCallback = new ControlCallback()
    {
        @Override
        public void onSucccess()
        {
           Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(int code)
        {
            Toast.makeText(getApplicationContext(),"error : "+code,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFail()
        {
            Toast.makeText(getApplicationContext(),"serverfail",Toast.LENGTH_SHORT).show();
        }
    };

    public class LoginTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mCountDownLatch = new CountDownLatch(0);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Task.getInstance().loginTask(edit_email.getText().toString(), edit_password.getText().toString(),controlCallback);

//            ServerManager.getInstanse().loginMethod(callBack, edit_email.getText().toString(), edit_password.getText().toString());
//            ServerManager.getInstanse().sendToken(callBack, "test", FirebaseInstanceId.getInstance().getToken());

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (mCountDownLatch.getCount() <= 0) {
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }
    }
}