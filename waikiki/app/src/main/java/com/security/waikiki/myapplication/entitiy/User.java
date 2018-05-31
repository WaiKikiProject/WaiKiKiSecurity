package com.security.waikiki.myapplication.entitiy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject
{
	@Expose
	@SerializedName("email")
	@PrimaryKey
	private String UserEmail;

	@Expose(deserialize = true)
	@SerializedName("password")
	private String Password;

	@Expose
	@SerializedName("name")
	private String UserName;

	@Expose
	@SerializedName("token")
	private String Token;

	public User(){

	}

	public User(String email,String password){
		UserEmail = email;
		Password = password;
	}

	public String getUserEmail()
	{
		return UserEmail;
	}

	public void setUserEmail(String userEmail)
	{
		UserEmail = userEmail;
	}

	public String getPassword()
	{
		return Password;
	}

	public void setPassword(String password)
	{
		Password = password;
	}

	public String getUserName()
	{
		return UserName;
	}

	public void setUserName(String userName)
	{
		UserName = userName;
	}

	public String getToken()
	{
		return Token;
	}

	public void setToken(String token)
	{
		Token = token;
	}
}
