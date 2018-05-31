package com.security.waikiki.myapplication.db;

import com.security.waikiki.myapplication.entitiy.User;

import io.realm.Realm;

public class RealmManager
{

	public static User getUser(){
		Realm realm = Realm.getDefaultInstance();
		User user = realm.where(User.class).findFirst();
		return user;
	};
}
