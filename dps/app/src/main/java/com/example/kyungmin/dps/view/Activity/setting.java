package com.example.kyungmin.dps.view.Activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.kyungmin.dps.R;

public class setting extends AppCompatActivity {


    Switch sound, vibration, popup;
    TextView tv;
    CompoundButton.OnCheckedChangeListener ccl;
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);


        Toolbar toolbar = (Toolbar) findViewById(R.id.setting);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setTitle("설정");
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFF89D88));


        sound = (Switch)findViewById(R.id.sound);
        vibration = (Switch)findViewById(R.id.vibration);
        popup = (Switch)findViewById(R.id.popup);
        tv = (TextView)findViewById(R.id.textView7);

        ccl = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switch (compoundButton.getId()){
                    case R.id.sound :
                        if(b) {
                            tv.setText("소리 on");
                        }else
                            tv.setText("소리 off");
                        break;
                    case R.id.vibration :
                        if(b) {
                            tv.setText("진동 on");
                        }else
                            tv.setText("소리 off");
                        break;
                    case R.id.popup :
                        if(b) {
                            tv.setText("팝업 on");
                        }else
                            tv.setText("팝업 off");
                        break;
                }
            }
        };
        sound.setOnCheckedChangeListener(ccl);
        vibration.setOnCheckedChangeListener(ccl);
        popup.setOnCheckedChangeListener(ccl);



    }
}

