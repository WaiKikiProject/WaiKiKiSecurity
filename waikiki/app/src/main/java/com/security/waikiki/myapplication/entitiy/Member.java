package com.security.waikiki.myapplication.entitiy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.security.waikiki.myapplication.view.activity.MemberActivity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import retrofit2.http.Field;

public class Member extends RealmObject
{
	@Expose
	@SerializedName("device_id")
	private String DeviceID;

	@Expose
	@SerializedName("email")
	private String Email;

	@Expose
	@SerializedName("name")
	private String Name;

	private boolean IsMaster;

	private int Status;

	public Member()
	{

	}

	public Member(String device_id, String email, String name)
	{
		DeviceID = device_id;
		Email = email;
		Name = name;
	}

	public String getDeviceID()
	{
		return DeviceID;
	}

	public void setDeviceID(String deviceID)
	{
		DeviceID = deviceID;
	}

	public String getEmail()
	{
		return Email;
	}

	public void setEmail(String email)
	{
		Email = email;
	}

	public String getName()
	{
		return Name;
	}

	public void setName(String name)
	{
		Name = name;
	}

	public boolean isMaster()
	{
		return IsMaster;
	}

	public void setMaster(boolean master)
	{
		IsMaster = master;
	}

	public int getStatus()
	{
		return Status;
	}

	public void setStatus(int status)
	{
		Status = status;
	}
}
