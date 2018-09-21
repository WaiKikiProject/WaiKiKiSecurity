package com.security.waikiki.myapplication.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.entitiy.Member;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class MemberAdapter extends RealmRecyclerViewAdapter<Member, MemberAdapter.ViewHolder>
{
	public MemberAdapter(@Nullable OrderedRealmCollection<Member> data, boolean autoUpdate,View.OnClickListener onClickListener)
	{
		super(data, autoUpdate);
		mOnClickListener = onClickListener;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		LayoutInflater inflater = (LayoutInflater) parent.getContext()
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View converView = inflater.inflate(R.layout.item_member,parent,false);
		return new MemberAdapter.ViewHolder(converView);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position)
	{
		Member member = getItem(position);

		if(!member.isMaster()){
			holder.mLaymain.setBackgroundResource(R.drawable.background_member_guest_border);
			holder.mLayTitle.setBackgroundResource(R.drawable.background_member_guest);
			holder.mTextTitle.setText(R.string.type_guest_english);
			holder.mTextName.setBackgroundResource(R.drawable.background_member_guest_sub);
			holder.mTextEmail.setBackgroundResource(R.drawable.background_member_guest_sub);
		}

	}

	public class ViewHolder extends RecyclerView.ViewHolder
	{
		ConstraintLayout mLaymain;
		ConstraintLayout mLayTitle;
		TextView mTextTitle;
		TextView mTextEmail;
		TextView mTextName;

		public ViewHolder(View view)
		{
			super(view);

			view.setOnClickListener(mOnClickListener);
			mLaymain = view.findViewById(R.id.layout_member_item);
			mLayTitle = view.findViewById(R.id.layout_member_item_top);
			mTextTitle = view.findViewById(R.id.textview_item_title);
			mTextEmail = view.findViewById(R.id.textview_email);
			mTextName = view.findViewById(R.id.textview_name);
		}
	}

	private View.OnClickListener mOnClickListener;


}
