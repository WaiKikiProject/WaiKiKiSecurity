package com.security.waikiki.myapplication.entitiy;

import io.realm.RealmObject;

public class Member extends RealmObject
{
	private String device_id;
	private String email;
	private String name;
	private boolean isMaster;

	public Member()
	{

	}

	public Member(String device_id, String email, String name)
	{
		this.device_id = device_id;
		this.email = email;
		this.name = name;
	}

	public String getDevice_id()
	{
		return device_id;
	}

	public void setDevice_id(String device_id)
	{
		this.device_id = device_id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isMaster()
	{
		return isMaster;
	}

	public void setMaster(boolean isMaster)
	{
		this.isMaster = isMaster;
	}

}
