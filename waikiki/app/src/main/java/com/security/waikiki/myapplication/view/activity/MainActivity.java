package com.security.waikiki.myapplication.view.activity;

<<<<<<< HEAD
=======
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
>>>>>>> android_feature
import android.graphics.Point;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.WaiKiKi;
import com.security.waikiki.myapplication.controller.ControlCallback;
import com.security.waikiki.myapplication.controller.Task;
import com.security.waikiki.myapplication.db.RealmManager;
import com.security.waikiki.myapplication.entitiy.Device;
import com.security.waikiki.myapplication.entitiy.Event;
import com.security.waikiki.myapplication.entitiy.User;
import com.security.waikiki.myapplication.entitiy.UserType;
import com.security.waikiki.myapplication.util.CircleAnimIndicator;
import com.security.waikiki.myapplication.util.CustomProgress;
import com.security.waikiki.myapplication.view.adapter.ViewPagerAdapter;
import com.security.waikiki.myapplication.view.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;


<<<<<<< HEAD
    private TextView mTextUser;
    private ConstraintLayout mButtonLeft;
    private ConstraintLayout mButtonRight;
=======
public class MainActivity extends RootParentActivity
{

	private View mMenuView;

	private SlidingUpPanelLayout mLayoutSliding;
>>>>>>> android_feature

	private ConstraintLayout mButtonLeft;
	private ConstraintLayout mButtonRight;

	private CircleAnimIndicator mPagerIndicator;

<<<<<<< HEAD
=======
	private ViewPager mViewPager;
	private ViewPagerAdapter mAdapter;
	private int mViewPagerIndex;
>>>>>>> android_feature

	private TextView mTextEvent;

	private LinearLayout mButtonMember;
	private LinearLayout mButtonEvent;
	private LinearLayout mButtonSModeLog;
	private LinearLayout mButtonDelete;
	private LinearLayout mButtonLogOut;
	private LinearLayout mButtonInstall;

	private List<MainFragment> listFragment;
	private String mCurrentDeviceID;
	private UserType mUserType;
	private User mUser;

<<<<<<< HEAD

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
=======
	private CustomProgress mProgress;
>>>>>>> android_feature

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

<<<<<<< HEAD
        mViewPager = findViewById(R.id.viewpager);
        mTextUser = findViewById(R.id.textview_user);
        mButtonLeft = findViewById(R.id.button_left);
        mButtonRight = findViewById(R.id.button_right);
        mPagerIndicator = findViewById(R.id.pager_indicator);

        mButtonRight.setOnClickListener(mOnClickListener);
        mButtonLeft.setOnClickListener(mOnClickListener);

        mViewPagerIndex = 0;

        setViewPage();
    }


    private void setViewPage()
    {
        if (listFragment == null)
        {
            listFragment = new ArrayList<>();
        }
        else
        {
            listFragment.clear();
        }

        MainFragment fragment1 = new MainFragment(MainFragment.UserType.GUEST,MainFragment.SecureMode.SECURE);
        MainFragment fragment2 = new MainFragment(MainFragment.UserType.MASTER,MainFragment.SecureMode.UNSECURE);
        MainFragment fragment3 = new MainFragment(MainFragment.UserType.DEFAULT,null);
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

    private void initIndicaotor()
    {
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

    private void setViewPagerButton(int pageindex)
    {
        if (listFragment.size() == 1)
        {
            mButtonLeft.setVisibility(View.INVISIBLE);
            mButtonRight.setVisibility(View.INVISIBLE);
        }
        else if (pageindex <= 0)
        {
            mButtonLeft.setVisibility(View.INVISIBLE);
            mButtonRight.setVisibility(View.VISIBLE);
        }
        else if (pageindex >= listFragment.size() - 1)
        {
            mButtonLeft.setVisibility(View.VISIBLE);
            mButtonRight.setVisibility(View.INVISIBLE);
        }
        else
        {
            mButtonLeft.setVisibility(View.VISIBLE);
            mButtonRight.setVisibility(View.VISIBLE);
        }
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener()
    {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
        {
        }

        @Override
        public void onPageSelected(int position)
        {
            mPagerIndicator.selectDot(position);
            setViewPagerButton(position);
            mViewPagerIndex = position;
        }

        @Override
        public void onPageScrollStateChanged(int state)
        {
        }
    };


    View.OnClickListener mOnClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.button_left:
                    mViewPager.setCurrentItem(mViewPagerIndex-1);
                    break;
                case R.id.button_right:
                    mViewPager.setCurrentItem(mViewPagerIndex+1);
                    break;
            }
        }
    };
=======
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ActionBar supportActionBar = getSupportActionBar();
		supportActionBar.setDisplayShowHomeEnabled(false);
		supportActionBar.setDisplayHomeAsUpEnabled(false);
		supportActionBar.setDisplayShowCustomEnabled(true);

//        setStatusbar(toolbar);

		mLayoutSliding = findViewById(R.id.layout_sliding);

		mViewPager = findViewById(R.id.viewpager);
		mButtonLeft = findViewById(R.id.button_left);
		mButtonRight = findViewById(R.id.button_right);
		mPagerIndicator = findViewById(R.id.pager_indicator);

		mTextEvent = findViewById(R.id.textview_event);

		mButtonRight.setOnClickListener(mOnClickListener);
		mButtonLeft.setOnClickListener(mOnClickListener);

		mUser = RealmManager.getUser();

		mViewPagerIndex = 0;

		initMenu();
		setViewPage();

		setMenu();

		mProgress = new CustomProgress(this);

		getEventData();
		getDeviceData();

	}


	private void initMenu()
	{
		mMenuView = findViewById(R.id.include_menu);

		mButtonMember = mMenuView.findViewById(R.id.button_member);
		mButtonEvent = mMenuView.findViewById(R.id.button_event);
		mButtonSModeLog = mMenuView.findViewById(R.id.button_smode_log);
		mButtonDelete = mMenuView.findViewById(R.id.button_delete);
		mButtonLogOut = mMenuView.findViewById(R.id.button_logout);
		mButtonInstall = mMenuView.findViewById(R.id.button_install);
		mButtonMember.setOnClickListener(mOnClickListener);
		mButtonEvent.setOnClickListener(mOnClickListener);
		mButtonSModeLog.setOnClickListener(mOnClickListener);
		mButtonDelete.setOnClickListener(mOnClickListener);
		mButtonLogOut.setOnClickListener(mOnClickListener);
		mButtonInstall.setOnClickListener(mOnClickListener);
	}

	private void getEventData()
	{
		mProgress.show();
		Task.getInstance().getEvnetTask(mUser.getUserEmail(), eventCollback);
	}

	private ControlCallback eventCollback = new ControlCallback()
	{
		@Override
		public void onSucccess()
		{
			setEventData();
			Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
			mProgress.dismiss();
		}

		@Override
		public void onError(int code)
		{
			mTextEvent.setText(getString(R.string.main_event_error));
			mProgress.dismiss();
		}

		@Override
		public void onFail()
		{
			mTextEvent.setText(getString(R.string.main_event_error));
			mProgress.dismiss();
		}
	};

	private void getDeviceData()
	{
		mProgress.show();
		Task.getInstance().getInstallTask(mUser.getUserEmail(), deviceCallback);
	}

	ControlCallback deviceCallback = new ControlCallback()
	{
		@Override
		public void onSucccess()
		{
			Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
			setViewPage();
			if (listFragment.size() - 1 < mViewPagerIndex)
			{
				mViewPager.setCurrentItem(0);
			}
			else
			{
				mViewPager.setCurrentItem(mViewPagerIndex);
			}
			mProgress.dismiss();
		}

		@Override
		public void onError(int code)
		{
			Toast.makeText(getApplicationContext(), "error : " + code, Toast.LENGTH_SHORT).show();
			mProgress.dismiss();
		}

		@Override
		public void onFail()
		{
			Toast.makeText(getApplicationContext(), "오류", Toast.LENGTH_SHORT).show();
			mProgress.dismiss();
		}
	};

	private void setEventData()
	{
		mCurrentDeviceID = mAdapter.getItem(mViewPagerIndex).getDeviceID();

		int event_count = 0;

		RealmResults<Event> events = RealmManager.getEvent(mCurrentDeviceID);
		for (Event event : events)
		{
			if (event.getConfirm().equals("X"))
			{
				event_count++;
			}
		}

		if (mCurrentDeviceID == null)
		{
			mTextEvent.setText(getString(R.string.main_install_navi));
		}
		else if (event_count == 0)
		{
			mTextEvent.setText(getString(R.string.main_event_null));
		}
		else
		{
			mTextEvent.setText(getString(R.string.main_event, event_count));
		}
	}

	private void setMenu()
	{
		mUserType = mAdapter.getItem(mViewPagerIndex).getUserType();

		switch (mUserType)
		{
		case MASTER:
			mButtonEvent.setVisibility(View.VISIBLE);
			mButtonMember.setVisibility(View.VISIBLE);
			mButtonSModeLog.setVisibility(View.VISIBLE);
			mButtonDelete.setVisibility(View.VISIBLE);
			mButtonInstall.setVisibility(View.GONE);
			break;
		case GUEST:
			mButtonEvent.setVisibility(View.VISIBLE);
			mButtonMember.setVisibility(View.VISIBLE);
			mButtonSModeLog.setVisibility(View.VISIBLE);
			mButtonDelete.setVisibility(View.GONE);
			mButtonInstall.setVisibility(View.GONE);
			break;
		case DEFAULT:
			mButtonEvent.setVisibility(View.GONE);
			mButtonMember.setVisibility(View.GONE);
			mButtonSModeLog.setVisibility(View.GONE);
			mButtonDelete.setVisibility(View.GONE);
			mButtonInstall.setVisibility(View.VISIBLE);
			break;
		}
	}

	private long time = 0;

	@Override
	public void onBackPressed()
	{
		if (mLayoutSliding.getPanelState().equals(SlidingUpPanelLayout.PanelState.EXPANDED))
		{
			mLayoutSliding.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
		}
		else
		{
			if (System.currentTimeMillis() - time >= 2000)
			{
				time = System.currentTimeMillis();
				Toast.makeText(getApplicationContext(), getString(R.string.main_backpress), Toast.LENGTH_SHORT).show();
			}
			else if (System.currentTimeMillis() - time < 2000)
			{
				finish();
			}
		}
	}

	private void setViewPage()
	{
		if (listFragment == null)
		{
			listFragment = new ArrayList<>();
		}
		else
		{
			listFragment.clear();
		}

		RealmResults<Device> devices = RealmManager.getDevices();

		for (Device device : devices)
		{
			MainFragment fragment = new MainFragment(device);
			listFragment.add(fragment);
		}
		MainFragment fragment = new MainFragment(null);
		listFragment.add(fragment);


		mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), listFragment);

		mViewPager.setAdapter(mAdapter);
		mViewPager.addOnPageChangeListener(mOnPageChangeListener);

		mViewPager.setCurrentItem(mViewPagerIndex);

		initIndicaotor();
		setViewPagerButton(mViewPager.getCurrentItem());
	}

	private void initIndicaotor()
	{
		mPagerIndicator.removeAllViews();

		mPagerIndicator.setItemMargin(10);
		mPagerIndicator.setAnimDuration(300);


		mPagerIndicator.createDotPanel(listFragment.size(), R.drawable.indicator_non, R.drawable.indicator_on, WaiKiKi
                .WIDTH /
				120,
			mViewPagerIndex);
	}

	private void setViewPagerButton(int pageindex)
	{
		if (listFragment.size() == 1)
		{
			mButtonLeft.setVisibility(View.INVISIBLE);
			mButtonRight.setVisibility(View.INVISIBLE);
		}
		else if (pageindex <= 0)
		{
			mButtonLeft.setVisibility(View.INVISIBLE);
			mButtonRight.setVisibility(View.VISIBLE);
		}
		else if (pageindex >= listFragment.size() - 1)
		{
			mButtonLeft.setVisibility(View.VISIBLE);
			mButtonRight.setVisibility(View.INVISIBLE);
		}
		else
		{
			mButtonLeft.setVisibility(View.VISIBLE);
			mButtonRight.setVisibility(View.VISIBLE);
		}
	}

	private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener()
	{

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
		{
		}

		@Override
		public void onPageSelected(int position)
		{
			mPagerIndicator.selectDot(position);
			setViewPagerButton(position);
			mViewPagerIndex = position;

			setMenu();
			setEventData();
		}

		@Override
		public void onPageScrollStateChanged(int state)
		{
		}
	};


	View.OnClickListener mOnClickListener = new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{

			mLayoutSliding.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

			Intent intent;

			switch (v.getId())
			{
			case R.id.button_left:
				mViewPager.setCurrentItem(mViewPagerIndex - 1);
				break;
			case R.id.button_right:
				mViewPager.setCurrentItem(mViewPagerIndex + 1);
				break;
			case R.id.button_member:
				Task.getInstance().getMemberTask(mCurrentDeviceID, new ControlCallback()
				{
					@Override
					public void onSucccess()
					{
						Intent intent = new Intent(MainActivity.this, MemberActivity.class);
						intent.putExtra("DeviceID", mCurrentDeviceID);
						startActivity(intent);
					}

					@Override
					public void onError(int code)
					{
						Toast.makeText(getApplicationContext(), "error : " + code, Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFail()
					{
						Toast.makeText(getApplicationContext(), "serverfail", Toast.LENGTH_SHORT).show();
					}
				});
				break;
			case R.id.button_event:
				intent = new Intent(MainActivity.this, EventActivity.class);
				intent.putExtra("DeviceID", mCurrentDeviceID);
				startActivity(intent);
				break;
			case R.id.button_delete:
				break;

			case R.id.button_install:
				intent = new Intent(MainActivity.this, InstallSplashActivity.class);
				startActivity(intent);
				break;

			case R.id.button_smode_log:
				intent = new Intent(MainActivity.this, ControlLogActivity.class);
				intent.putExtra("DeviceID", mCurrentDeviceID);
				startActivity(intent);
				break;

			case R.id.button_logout:
				Task.getInstance().logoutTask(new ControlCallback()
				{
					@Override
					public void onSucccess()
					{
						finish();
						Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onError(int code)
					{
						Toast.makeText(getApplicationContext(), "error : " + code, Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFail()
					{
						Toast.makeText(getApplicationContext(), "serverfail", Toast.LENGTH_SHORT).show();
					}
				});
				break;

			}
		}
	};


	BroadcastReceiver mPushBroadcast = new BroadcastReceiver()
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			if (intent.getAction().equals(WaiKiKi.PUSH_BROADCASTMESSAGE))
			{
				getDeviceData();
				getEventData();
				setMenu();
			}
		}
	};

	private void registerReceiver()
	{
		final IntentFilter filter = new IntentFilter();
		filter.addAction(WaiKiKi.PUSH_BROADCASTMESSAGE);

		registerReceiver(mPushBroadcast, filter);
	}

	private void unregisterReceiver()
	{
		unregisterReceiver(mPushBroadcast);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		registerReceiver();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		unregisterReceiver();
	}
>>>>>>> android_feature
}
