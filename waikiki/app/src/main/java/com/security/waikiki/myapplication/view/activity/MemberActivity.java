package com.security.waikiki.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.WaiKiKi;
import com.security.waikiki.myapplication.db.RealmManager;
import com.security.waikiki.myapplication.entitiy.Member;
import com.security.waikiki.myapplication.view.adapter.MemberAdapter;

import io.realm.RealmResults;

public class MemberActivity extends RootParentActivity
{
	FloatingActionButton mFABPlus, mFABAdd, mFABDelete, mFABComm;
	RecyclerView mRecyclerList;
	MemberAdapter mMemberAdpater;
	MemberActionStatus mStatus;

	String mDeviceID;
	boolean isMaster;

	boolean isFabOpen = false;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member);

		Intent intent = getIntent();
		mDeviceID = intent.getStringExtra("DeviceID");

		isMaster = RealmManager.getDevice(mDeviceID).getMaster().equals(RealmManager.getUser().getUserEmail()) ?
			true : false;

		findViewById(R.id.button_up).setOnClickListener(mOnClickListener);

		initFloatingActionButton();
		initRecyclerView();

		mStatus = MemberActionStatus.DEFAULT;

	}

	private void initFloatingActionButton()
	{
		mFABPlus = findViewById(R.id.fab_plus);
		mFABAdd = findViewById(R.id.fab_add);
		mFABDelete = findViewById(R.id.fab_delete);
		mFABComm = findViewById(R.id.fab_commission);
		mFABPlus.setOnClickListener(mOnClickListener);
		mFABAdd.setOnClickListener(mOnClickListener);
		mFABDelete.setOnClickListener(mOnClickListener);
		mFABComm.setOnClickListener(mOnClickListener);
	}

	private void initRecyclerView()
	{
		RealmResults<Member> items = RealmManager.getMember();
		mRecyclerList = findViewById(R.id.recyclerview_member);
		mMemberAdpater = new MemberAdapter(items, true, null);
		mRecyclerList.setAdapter(mMemberAdpater);
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
				mStatus = MemberActionStatus.DEFAULT;
				mMemberAdpater.setMemberStatus(mStatus);
				Intent intent = new Intent(MemberActivity.this,InviteActivity.class);
				startActivityForResult(intent, WaiKiKi.INVITE);
				startAni();
				break;
			case R.id.fab_commission:
				mStatus = MemberActionStatus.COMMISSION;
				mMemberAdpater.setMemberStatus(mStatus);
				startAni();
				break;
			case R.id.fab_delete:
				mStatus = MemberActionStatus.DELETE;
				int a = mStatus.ordinal();
				mMemberAdpater.setMemberStatus(mStatus);
				startAni();
				break;
			}
		}
	};


	void startAni()
	{

		if (isFabOpen)
		{
			Animation ani_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
			Animation ani_rotate_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim
				.rotate_fab_close);
			ani_rotate_close.setRepeatCount(0);
			isFabOpen = false;
			mFABPlus.setAnimation(ani_rotate_close);
			mFABAdd.setAnimation(ani_close);
			mFABDelete.setAnimation(ani_close);
			mFABComm.setAnimation(ani_close);
			mFABAdd.setVisibility(View.GONE);
			mFABDelete.setVisibility(View.GONE);
			mFABComm.setVisibility(View.GONE);
			mFABAdd.setEnabled(false);
			mFABDelete.setEnabled(false);
			mFABComm.setEnabled(false);
		}
		else
		{
			Animation ani_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
			Animation ani_rotate_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_fab_open);
			ani_rotate_open.setRepeatCount(0);
			isFabOpen = true;
			mFABPlus.setAnimation(ani_rotate_open);
			mFABAdd.setAnimation(ani_open);
			mFABDelete.setAnimation(ani_open);
			mFABComm.setAnimation(ani_open);
			mFABAdd.setVisibility(View.VISIBLE);
			mFABDelete.setVisibility(View.VISIBLE);
			mFABComm.setVisibility(View.VISIBLE);
			mFABAdd.setEnabled(true);
			mFABDelete.setEnabled(true);
			mFABComm.setEnabled(true);
		}
	}

	public enum MemberActionStatus
	{
		DEFAULT,
		COMMISSION,
		DELETE
	}
}