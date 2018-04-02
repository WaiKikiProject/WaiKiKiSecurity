package com.example.a.login;

import android.os.ResultReceiver;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.a.login.Vo.User;
import com.example.a.login.server.RetrofitConnect;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Realm mRealm;
    CheckBox autlogin;
    EditText mTextEmail;
    EditText mTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextEmail = (EditText) findViewById(R.id.email);
        mTextPassword = (EditText) findViewById(R.id.password);
        autlogin = (CheckBox)findViewById(R.id.autlogin);
        findViewById(R.id.login).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Call<User> call = new RetrofitConnect().loginConnec(mTextEmail.getText().toString(), mTextPassword.getText().toString());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setPositiveButton("닫기", null);
                alertDialog.create();

                if (response.isSuccessful()) {
                    alertDialog.setTitle("성공");
                } else {
                    alertDialog.setTitle("실패");
                }
                alertDialog.show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        Realm realm = Realm.getDefaultInstance();

        User user = new User();
        user.setEmail(mTextEmail.getText().toString());
        user.setPassword(mTextPassword.getText().toString());

        realm.beginTransaction();

        User realmUser = realm.copyToRealm(user);

        realm.commitTransaction();


    }
}
