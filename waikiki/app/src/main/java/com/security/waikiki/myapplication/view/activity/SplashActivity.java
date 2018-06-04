package com.security.waikiki.myapplication.view.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.WaiKiKi;
import com.security.waikiki.myapplication.db.RealmManager;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {
    private CountDownLatch mCountDownLatch;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Point pt = new Point();

        getWindowManager().getDefaultDisplay().getSize(pt);
        ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getSize(pt);

        WaiKiKi.WIDTH = pt.x;
        WaiKiKi.HEIGTH = pt.y;

        checkLoading();
    }

    private void checkLoading() {
        new LoadingWaitTask();
    }

    public class LoadingWaitTask extends AsyncTask<Void, Void, Void> {
        public LoadingWaitTask() {
            mCountDownLatch = new CountDownLatch(1);
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
            if (RealmManager.getUser() == null) {
                Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

    }
}


