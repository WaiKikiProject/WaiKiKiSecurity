package com.security.waikiki.myapplication.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.WaiKiKi;

public class CustomProgress extends Dialog
{
	CircleAnimIndicator indicator;
	int position;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_pogress);
		getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		position =0;
		initIndicaotor();
		lodingCount();
	}

	public CustomProgress(Context context){
		super(context);
	}

	private void initIndicaotor() {

		indicator = findViewById(R.id.indicator);

		indicator.setItemMargin(10);
		indicator.setAnimDuration(100);

		indicator.createDotPanel(5, R.drawable.indicator_non, R.drawable.indicator_on, WaiKiKi.WIDTH /
				100, position);
	}


	public void lodingCount(){
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				if(position >= 4){
					position = 0;
				}else {
					position++;
				}
				indicator.selectDot(position);
				lodingCount();
			}
		},200);
	}

}
