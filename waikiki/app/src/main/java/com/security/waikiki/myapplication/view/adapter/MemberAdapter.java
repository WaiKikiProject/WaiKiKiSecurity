package com.security.waikiki.myapplication.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.entitiy.Member;
import com.security.waikiki.myapplication.view.activity.MemberActivity;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;

public class MemberAdapter extends RealmRecyclerViewAdapter<Member, MemberAdapter.ViewHolder>
{
	private View.OnClickListener mOnClickListener;


	public MemberAdapter(@Nullable OrderedRealmCollection<Member> data, boolean autoUpdate, View.OnClickListener
		onClickListener)
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
		View converView = inflater.inflate(R.layout.item_member, parent, false);
		return new MemberAdapter.ViewHolder(converView);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position)
	{
		Member member = getItem(position);
		holder.setTag(position);

		if (!member.isMaster())
		{
			holder.mLaymain.setBackgroundResource(R.drawable.background_member_guest_border);
			holder.mLayTitle.setBackgroundResource(R.drawable.background_member_guest);
			holder.mTextTitle.setText(R.string.type_guest_english);
			holder.mTextName.setBackgroundResource(R.drawable.background_member_guest_sub);
			holder.mTextEmail.setBackgroundResource(R.drawable.background_member_guest_sub);

			if (member.getStatus() == MemberActivity.MemberActionStatus.DEFAULT.ordinal())
			{
				holder.mLayStatus.setVisibility(View.GONE);
			}
			else
			{
				holder.mLayStatus.setVisibility(View.VISIBLE);
				holder.mImageStatus.setImageResource(member.getStatus() == MemberActivity.MemberActionStatus
					.DELETE.ordinal() ? R.drawable.cancel : R.drawable.crown);
			}
		}
		else
		{
			holder.mLaymain.setBackgroundResource(R.drawable.background_member_master_border);
			holder.mLayTitle.setBackgroundResource(R.drawable.background_member_master);
			holder.mTextTitle.setText(R.string.type_master_english);
			holder.mTextName.setBackgroundResource(R.drawable.background_member_master_sub);
			holder.mTextEmail.setBackgroundResource(R.drawable.background_member_master_sub);

			holder.mLayStatus.setVisibility(View.GONE);
		}

		holder.mTextEmailDetail.setText(member.getEmail());
		holder.mTextNameDetail.setText(member.getName());


	}

	public void setMemberStatus(MemberActivity.MemberActionStatus status)
	{
		Realm realm = Realm.getDefaultInstance();
		realm.beginTransaction();
		for (int i = 0; i < getItemCount(); i++)
		{

			getItem(i).setStatus(status.ordinal());
		}
		realm.commitTransaction();
	}


	public class ViewHolder extends RecyclerView.ViewHolder
	{
		ConstraintLayout mLaymain;
		ConstraintLayout mLayTitle;
		ConstraintLayout mLayStatus;
		TextView mTextTitle;
		TextView mTextEmail;
		TextView mTextName;
		TextView mTextEmailDetail;
		TextView mTextNameDetail;
		ImageView mImageStatus;

		public ViewHolder(View view)
		{
			super(view);
//			view.setOnClickListener(mOnClickListener);
			mLaymain = view.findViewById(R.id.layout_member_item);
			mLayTitle = view.findViewById(R.id.layout_member_item_top);
			mLayStatus = view.findViewById(R.id.layout_member_action);
			mTextTitle = view.findViewById(R.id.textview_item_title);
			mTextEmail = view.findViewById(R.id.textview_email);
			mTextName = view.findViewById(R.id.textview_name);
			mTextEmailDetail = view.findViewById(R.id.textview_email_detail);
			mTextNameDetail = view.findViewById(R.id.textview_name_detail);
			mImageStatus = view.findViewById(R.id.imageview_member_status);
		}

		public void setTag(int position)
		{
			itemView.setTag(position);
		}

	}


	@Override
	public void onViewAttachedToWindow(@NonNull ViewHolder holder)
	{
		super.onViewAttachedToWindow(holder);
	}

	@Override
	public void onViewDetachedFromWindow(@NonNull ViewHolder holder)
	{
		super.onViewDetachedFromWindow(holder);
	}
}
