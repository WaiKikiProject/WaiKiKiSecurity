package com.security.waikiki.myapplication.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.security.waikiki.myapplication.R;

public class ControlLogActivity extends RootParentActivity
{

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_controllog);

		findViewById(R.id.button_up).setOnClickListener(mOnClickListener);
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
			}
		}
	};
}