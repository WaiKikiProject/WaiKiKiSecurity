package com.security.waikiki.myapplication.view.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.security.waikiki.myapplication.view.fragment.MainFragment;
import java.util.List;


public class ViewPagerAdapter extends FragmentStatePagerAdapter
{

	List<MainFragment> listFragments;

	public ViewPagerAdapter(FragmentManager fm, List<MainFragment> listFragments)
	{
		super(fm);
		this.listFragments = listFragments;
	}

	@Override
	public MainFragment getItem(int position)
	{
		return listFragments.get(position);
	}

	@Override
	public int getCount()
	{
		return listFragments.size();
	}

}
