package com.security.waikiki.myapplication.view.activity;

<<<<<<< HEAD
=======
import android.content.DialogInterface;
>>>>>>> android_feature
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
<<<<<<< HEAD
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
=======
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
>>>>>>> android_feature

import com.security.waikiki.myapplication.WaiKiKi;
import com.security.waikiki.myapplication.controller.ControlCallback;
import com.security.waikiki.myapplication.controller.Task;
import com.security.waikiki.myapplication.util.Customdialog;
import com.security.waikiki.myapplication.R;
<<<<<<< HEAD
import com.security.waikiki.myapplication.network.IGCallBack;
import com.security.waikiki.myapplication.network.ServerManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

=======
import com.security.waikiki.myapplication.network.ServerCallBack;
import com.security.waikiki.myapplication.network.ServerManager;

>>>>>>> android_feature
import okhttp3.ResponseBody;
import retrofit2.Response;

public class SignUpActivity extends RootParentActivity {

<<<<<<< HEAD
=======
    private Customdialog dialog;
>>>>>>> android_feature
    EditText email, password, name, repassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

<<<<<<< HEAD
        email = (EditText)findViewById(R.id.edittext_signup_email);
        password = (EditText)findViewById(R.id.edittext_signup_password);
        name = (EditText)findViewById(R.id.edittext_signup_name);
        repassword = (EditText)findViewById(R.id.edittext_signup_repassword);


=======
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayShowHomeEnabled(false);
        supportActionBar.setDisplayHomeAsUpEnabled(false);
        supportActionBar.setDisplayShowCustomEnabled(true);

        email = (EditText) findViewById(R.id.edittext_signup_email);
        password = (EditText) findViewById(R.id.edittext_signup_password);
        name = (EditText) findViewById(R.id.edittext_signup_name);
        repassword = (EditText) findViewById(R.id.edittext_signup_repassword);

        findViewById(R.id.button_up).setOnClickListener(mOnclickListener);
>>>>>>> android_feature
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


<<<<<<< HEAD
        }
    };
    IGCallBack<ResponseBody> callBack = new IGCallBack<ResponseBody>() {
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
=======
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_up:
                    finish();
                    break;
                case R.id.button_sign_in_login:
                    String title = getString(R.string.dialog_signup_title);
                    if (email.getText().toString().length() == 0) {
                        WaiKiKi.showDialog(SignUpActivity.this, getString(R.string.dialog_email_message), null);
                        return;
                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                        WaiKiKi.showDialog(SignUpActivity.this, getString(R.string.dialog_email_message_form), null);
                        return;
                    } else if (name.getText().toString().length() == 0) {
                        WaiKiKi.showDialog(SignUpActivity.this, getString(R.string.dialog_name_message), null);
                        return;
                    } else if (password.getText().toString().length() == 0) {
                        WaiKiKi.showDialog(SignUpActivity.this, getString(R.string.dialog_password_message), null);
                        return;
                    } else if (repassword.getText().toString().length() == 0) {
                        WaiKiKi.showDialog(SignUpActivity.this, getString(R.string.dialog_repassword_message), null);
                        return;
                    } else if (!password.getText().toString().equals(repassword.getText().toString())) {
                        WaiKiKi.showDialog(SignUpActivity.this, getString(R.string.dialog_password_confirm_message), null);
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
            String message = getString(R.string.dialog_signup_success_message);
            WaiKiKi.showDialog(SignUpActivity.this, title, message, mOnDismissListener);
        }

        @Override
        public void onError(int code) {
            String title = getString(R.string.dialog_signup_title);
            switch (code) {
                case 401:
                    WaiKiKi.showDialog(SignUpActivity.this, title, getString(R.string.dialog_error_aleady_exist_mail_message), null);
                    break;
                case 460:
                    WaiKiKi.showDialog(SignUpActivity.this, title, getString(R.string.dialog_error_database_error_message), null);
                    break;
                case 470:
                    WaiKiKi.showDialog(SignUpActivity.this, title, getString(R.string.dialog_error_database_overlap_message), null);
                    break;
                case 500:
                    WaiKiKi.showDialog(SignUpActivity.this, title, getString(R.string.dialog_error_not_dis_match_error_message), null);
                    break;
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
>>>>>>> android_feature
