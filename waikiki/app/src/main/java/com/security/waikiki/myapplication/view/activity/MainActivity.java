package com.security.waikiki.myapplication.view.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.util.CircleAnimIndicator;
import com.security.waikiki.myapplication.view.adapter.ViewPagerAdapter;
import com.security.waikiki.myapplication.view.fragment.MainFragment;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends RootParentActivity {

    private View mMenuView;

    private SlidingUpPanelLayout mLayoutSliding;

    private TextView mTextUser;
    private ConstraintLayout mButtonLeft;
    private ConstraintLayout mButtonRight;

    private CircleAnimIndicator mPagerIndicator;

    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;
    private int mViewPagerIndex;

    private LinearLayout mButtonMember;
    private LinearLayout mButtonEvent;
    private LinearLayout mButtonSModeLog;
    private LinearLayout mButtonMemberInvite;
    private LinearLayout mButtonDelete;
    private LinearLayout mButtonMemberLogOut;

    private List<MainFragment> listFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayShowHomeEnabled(false);
        supportActionBar.setDisplayHomeAsUpEnabled(false);
        supportActionBar.setDisplayShowCustomEnabled(true);

        setStatusbar(toolbar);

        mLayoutSliding = findViewById(R.id.layout_sliding);

        mViewPager = findViewById(R.id.viewpager);
        mTextUser = findViewById(R.id.textview_user);
        mButtonLeft = findViewById(R.id.button_left);
        mButtonRight = findViewById(R.id.button_right);
        mPagerIndicator = findViewById(R.id.pager_indicator);

        mButtonRight.setOnClickListener(mOnClickListener);
        mButtonLeft.setOnClickListener(mOnClickListener);

        mViewPagerIndex = 0;

        setViewPage();

        initMenu();
    }


    private void initMenu() {
        mMenuView = findViewById(R.id.include_menu);

        mButtonMember = mMenuView.findViewById(R.id.button_member);
        mButtonEvent = mMenuView.findViewById(R.id.button_event);
        mButtonSModeLog = mMenuView.findViewById(R.id.button_event);
        mButtonMemberInvite = mMenuView.findViewById(R.id.button_member_invite);
        mButtonDelete = mMenuView.findViewById(R.id.button_delete);
        mButtonMember.setOnClickListener(mOnClickListener);
        mButtonEvent.setOnClickListener(mOnClickListener);
        mButtonSModeLog.setOnClickListener(mOnClickListener);
        mButtonMemberInvite.setOnClickListener(mOnClickListener);
        mButtonDelete.setOnClickListener(mOnClickListener);

    }


    private void setViewPage() {
        if (listFragment == null) {
            listFragment = new ArrayList<>();
        } else {
            listFragment.clear();
        }

        MainFragment fragment1 = new MainFragment(MainFragment.UserType.GUEST, MainFragment.SecureMode.SECURE);
        MainFragment fragment2 = new MainFragment(MainFragment.UserType.MASTER, MainFragment.SecureMode.UNSECURE);
        MainFragment fragment3 = new MainFragment(MainFragment.UserType.DEFAULT, null);
        listFragment.add(fragment1);
        listFragment.add(fragment2);
        listFragment.add(fragment3);


        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), listFragment);

        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);

        mViewPager.setCurrentItem(mViewPagerIndex);

        initIndicaotor();
        setViewPagerButton(mViewPager.getCurrentItem());
    }

    private void initIndicaotor() {
        mPagerIndicator.removeAllViews();

        mPagerIndicator.setItemMargin(10);
        mPagerIndicator.setAnimDuration(300);

        Point pt = new Point();
        getWindowManager().getDefaultDisplay().getSize(pt);
        ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getSize(pt);

        int width = pt.x;

        mPagerIndicator.createDotPanel(listFragment.size(), R.drawable.indicator_non, R.drawable.indicator_on, width /
                        120,
                mViewPagerIndex);
    }

    private void setViewPagerButton(int pageindex) {
        if (listFragment.size() == 1) {
            mButtonLeft.setVisibility(View.INVISIBLE);
            mButtonRight.setVisibility(View.INVISIBLE);
        } else if (pageindex <= 0) {
            mButtonLeft.setVisibility(View.INVISIBLE);
            mButtonRight.setVisibility(View.VISIBLE);
        } else if (pageindex >= listFragment.size() - 1) {
            mButtonLeft.setVisibility(View.VISIBLE);
            mButtonRight.setVisibility(View.INVISIBLE);
        } else {
            mButtonLeft.setVisibility(View.VISIBLE);
            mButtonRight.setVisibility(View.VISIBLE);
        }
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            mPagerIndicator.selectDot(position);
            setViewPagerButton(position);
            mViewPagerIndex = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            mLayoutSliding.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

            Intent intent;

            switch (v.getId()) {
                case R.id.button_left:
                    mViewPager.setCurrentItem(mViewPagerIndex - 1);
                    break;
                case R.id.button_right:
                    mViewPager.setCurrentItem(mViewPagerIndex + 1);
                    break;
                case R.id.button_member:
                    intent = new Intent(MainActivity.this, MemberActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_event:
                    intent = new Intent(MainActivity.this, EventActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_member_invite:
                    intent = new Intent(MainActivity.this, InviteActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_delete:
                    break;
                case R.id.button_logout:
                    break;

            }
        }
    };
}
