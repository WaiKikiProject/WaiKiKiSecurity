package com.security.waikiki.myapplication.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.security.waikiki.myapplication.R;

public class MemberActivity extends RootParentActivity
{
    FloatingActionButton mFABPlus,mFABAdd,mFABDelete,mFABComm;
    boolean isFabOpen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        findViewById(R.id.button_up).setOnClickListener(mOnClickListener);

        initFloatingActionButton();
    }

    private void initFloatingActionButton(){
        mFABPlus = findViewById(R.id.fab_plus);
        mFABAdd = findViewById(R.id.fab_add);
        mFABDelete = findViewById(R.id.fab_delete);
        mFABComm = findViewById(R.id.fab_commission);
        mFABPlus.setOnClickListener(mOnClickListener);
        mFABAdd.setOnClickListener(mOnClickListener);
        mFABDelete.setOnClickListener(mOnClickListener);
        mFABComm.setOnClickListener(mOnClickListener);
    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
            case R.id.button_up:
                finish();
                break;
            case R.id.fab_plus:
                startAni();
                break;
            case R.id.fab_add:
                startAni();
                break;
            case R.id.fab_commission:
                startAni();
                break;
            case  R.id.fab_delete:
                startAni();
                break;
            }
        }
    };


    void startAni(){

        if (isFabOpen) {
            Animation ani_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
            Animation ani_rotate_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_fab_close);
            ani_rotate_close.setRepeatCount(0);
            isFabOpen = false;
            mFABPlus.setAnimation(ani_rotate_close);
            mFABAdd.setAnimation(ani_close);
            mFABDelete.setAnimation(ani_close);
            mFABComm.setAnimation(ani_close);
            mFABAdd.setVisibility(View.INVISIBLE);
            mFABDelete.setVisibility(View.INVISIBLE);
            mFABComm.setVisibility(View.INVISIBLE);
        } else {
            Animation ani_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
            Animation ani_rotate_open= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_fab_open);
            ani_rotate_open.setRepeatCount(0);
            isFabOpen = true;
            mFABPlus.setAnimation(ani_rotate_open);
            mFABAdd.setAnimation(ani_open);
            mFABDelete.setAnimation(ani_open);
            mFABComm.setAnimation(ani_open);
            mFABAdd.setVisibility(View.VISIBLE);
            mFABDelete.setVisibility(View.VISIBLE);
            mFABComm.setVisibility(View.VISIBLE);
        }
    }
}