package com.security.waikiki.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.WaiKiKi;

public class SignInActivity extends RootParentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        findViewById(R.id.button_login).setOnClickListener(mOnclickListener);

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
}
