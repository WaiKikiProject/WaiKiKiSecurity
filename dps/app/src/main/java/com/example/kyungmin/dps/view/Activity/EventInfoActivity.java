package com.example.kyungmin.dps.view.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.kyungmin.dps.view.fragment.FragmentPagerAdapter;
import com.example.kyungmin.dps.R;

public class EventInfoActivity extends AppCompatActivity {

    String mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventview);

        Intent intent = getIntent();
        mDate = intent.getStringExtra("date");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setTitle("침입기록확인");
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFF89D88));

        ViewPager viewPager = (ViewPager) findViewById(R.id.eventpager);
        viewPager.setAdapter(new EventViewpagerAdpater(getSupportFragmentManager()));

        Button call = (Button)findViewById(R.id.call);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog();
            }
        });
    }

    public void Dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("신고하기");
        builder.setMessage("신고하시겠습니까?");
        builder.setPositiveButton("아니요",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
        builder.setNeutralButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String tel = "tel:" + 112;
                        startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                    }
                });
        builder.show();
    }
}