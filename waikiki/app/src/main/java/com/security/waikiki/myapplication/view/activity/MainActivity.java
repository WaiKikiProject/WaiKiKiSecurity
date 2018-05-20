package com.security.waikiki.myapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.network.IGCallBack;
import com.security.waikiki.myapplication.network.ServerManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends RootParentActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.send);
        button.setOnClickListener(mOnclickListener);


    }

    View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this,"누름",Toast.LENGTH_LONG).show();
            ServerManager.getInstanse().signUpMethod(callBack,"sex@naver.com","섹스","1234");
        }
    };

    IGCallBack<ResponseBody> callBack = new IGCallBack<ResponseBody>() {
        @Override
        public void onResponseResult(Response<ResponseBody> response) {
            if(response.isSuccessful()){
                Toast.makeText(MainActivity.this,"성공",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(MainActivity.this,"실패",Toast.LENGTH_LONG).show();
            }
        }
    };
}
