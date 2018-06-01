package com.security.waikiki.myapplication.entitiy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Event extends RealmObject
{
	@Expose
	@SerializedName("envent_code")
	@PrimaryKey
	private String EventCode;

	@Expose
	@SerializedName("device_id")
	private String DeviceID;

	@Expose
	@SerializedName("confirm_result")
	private String Confirm;

	public String getEventCode()
	{
		return EventCode;
	}

	public void setEventCode(String eventCode)
	{
		EventCode = eventCode;
	}

	public String getDevice_id()
	{
		return DeviceID;
	}

	public void setDevice_id(String device_id)
	{
		DeviceID = device_id;
	}

	public String getConfirm()
	{
		return Confirm;
	}

	public void setConfirm(String confirm)
	{
		Confirm = confirm;
	}
}
