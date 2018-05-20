package com.security.waikiki.myapplication.view.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.security.waikiki.myapplication.R;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {
    private CountDownLatch mCountDownLatch;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        //changepoint
        checkLoading(true);
    }

    private void checkLoading(boolean first) {
        mCountDownLatch = new CountDownLatch(first ? 1 : 3);
        if (!first) {

        }

        new LoadingWaitTask();
    }

    public class LoadingWaitTask extends AsyncTask<Void, Void, Void> {
        public LoadingWaitTask() {
            execute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                mCountDownLatch.await(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        }

    }
}


