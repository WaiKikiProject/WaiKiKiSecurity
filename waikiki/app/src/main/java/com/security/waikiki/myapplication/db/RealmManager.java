package com.security.waikiki.myapplication.db;

import com.security.waikiki.myapplication.entitiy.Device;
import com.security.waikiki.myapplication.entitiy.User;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RealmManager
{

	public static User getUser(){
		Realm realm = Realm.getDefaultInstance();
		User user = realm.where(User.class).findFirst();
		return user;
	};

	public static void insertUser(User user){
		Realm realm = Realm.getDefaultInstance();
		realm.beginTransaction();
		realm.insert(user);
		realm.commitTransaction();
	}

	public static void deleteUser(){
		Realm realm = Realm.getDefaultInstance();
		User user = realm.where(User.class).findFirst();
		realm.beginTransaction();
		user.deleteFromRealm();
		realm.commitTransaction();
	}

	public static void insertDevice(List<Device> devices){
		Realm realm = Realm.getDefaultInstance();
		User user = realm.where(User.class).findFirst();
		realm.beginTransaction();
		for(Device device : devices){
			realm.insert(device);
		}
		realm.commitTransaction();
	}


	public static RealmResults<Device> getDevices(){
		Realm realm = Realm.getDefaultInstance();
		RealmResults<Device> devices = realm.where(Device.class).findAll();
		return devices;
	}

	public static void dumpDB(){
		Realm realm = Realm.getDefaultInstance();
		realm.beginTransaction();
		realm.deleteAll();
		realm.commitTransaction();
	}
}
