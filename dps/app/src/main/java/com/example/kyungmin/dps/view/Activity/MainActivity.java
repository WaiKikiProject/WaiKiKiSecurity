package com.example.kyungmin.dps.view.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.kyungmin.dps.CustomDialog;
import com.example.kyungmin.dps.view.entity.MainStatus;
import com.example.kyungmin.dps.view.entity.UserType;
import com.example.kyungmin.dps.view.fragment.FragmentPagerAdapter;
import com.example.kyungmin.dps.view.fragment.ImageFragment;
import com.example.kyungmin.dps.R;
import com.example.kyungmin.dps.view.widget.CircleAnimIndicator;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;
    FragmentPagerAdapter mAdapter;

    private DrawerLayout mDrawerLayout;
    CustomDialog mCusstmBuutonDialogog;
    Button mBuutonDialog;
    View.OnClickListener mOnclickListener;

    CircleAnimIndicator mIndicator;
    int mIndicatorPosition;

    int mWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        mWidth = size.x;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        actionBar.setTitle("홈");
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFF89D88));

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mBuutonDialog = (Button) findViewById(R.id.dial);
        mCusstmBuutonDialogog = new CustomDialog(this);
        mBuutonDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCusstmBuutonDialogog.show();
            }
        });

        mOnclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.logout:
                        break;
                    case R.id.crystal:
                        Intent intent = new Intent(MainActivity.this, crystal.class);
                        startActivity(intent);
                        break;
                    case R.id.setting:
                        intent = new Intent(MainActivity.this, setting.class);
                        startActivity(intent);
                        break;
                    case R.id.eventbutton:
                        intent = new Intent(MainActivity.this, EventActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };

        Button logout = (Button) findViewById(R.id.logout);
        Button cry = (Button) findViewById(R.id.crystal);
        Button setting = (Button) findViewById(R.id.setting);
        Button eventbutton = (Button) findViewById(R.id.eventbutton);


        logout.setOnClickListener(mOnclickListener);
        cry.setOnClickListener(mOnclickListener);
        setting.setOnClickListener(mOnclickListener);
        eventbutton.setOnClickListener(mOnclickListener);

        mIndicatorPosition = 0;
        createView();
    }

    private void createView() {
        List<ImageFragment> items = new ArrayList<>();
        ImageFragment fragment = new ImageFragment(MainStatus.SECURE, UserType.MASTER);
        ImageFragment fragment2 = new ImageFragment(MainStatus.INSTALL, UserType.NONE);
        items.add(fragment);
        items.add(fragment2);

        mViewPager = findViewById(R.id.pager);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), items);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(mOnPagechangeListener);

        mIndicator = findViewById(R.id.main_indicator);
        mIndicator.setItemMargin(15);
        mIndicator.setAnimDuration(300);
        mIndicator.createDotPanel(items.size(), R.drawable.indicator_nor, R.drawable.indicator_on, 50, mIndicatorPosition);
    }

    public void settingUI() {
        MainStatus status = mAdapter.getItem(mViewPager.getCurrentItem()).getStatus();
        UserType userType = mAdapter.getItem(mViewPager.getCurrentItem()).getUserType();

        switch (status) {
            case UNSECUR:
                break;
            case INSTALL:
                break;
            case SECURE:
                break;
        }

        switch (userType) {
            case GUEST:
                break;
            case MASTER:
                break;
            case NONE:
                break;
        }
    }


    ViewPager.OnPageChangeListener mOnPagechangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mIndicatorPosition = position;
            mIndicator.selectDot(position);
            settingUI();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}


