package com.security.waikiki.myapplication.db;

import com.security.waikiki.myapplication.entitiy.Device;
import com.security.waikiki.myapplication.entitiy.Event;
import com.security.waikiki.myapplication.entitiy.Member;
import com.security.waikiki.myapplication.entitiy.User;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RealmManager
{

	public static User getUser()
	{
		Realm realm = Realm.getDefaultInstance();
		User user = realm.where(User.class).findFirst();
		return user;
	}

	public static void insertUser(User user)
	{
		Realm realm = Realm.getDefaultInstance();
		realm.beginTransaction();
		realm.insertOrUpdate(user);
		realm.commitTransaction();
		
	}

	public static void deleteUser()
	{
		Realm realm = Realm.getDefaultInstance();
		User user = realm.where(User.class).findFirst();
		realm.beginTransaction();
		user.deleteFromRealm();
		realm.commitTransaction();
        
	}

	public static void insertDevice(List<Device> devices)
	{
		Realm realm = Realm.getDefaultInstance();
		realm.beginTransaction();
		for (Device device : devices)
		{
			realm.insert(device);
		}
		realm.commitTransaction();
	}


	public static RealmResults<Device> getDevices()
	{
		Realm realm = Realm.getDefaultInstance();
		RealmResults<Device> devices = realm.where(Device.class).findAll();
		return devices;
	}

	public static void dumpDevice()
	{
		Realm realm = Realm.getDefaultInstance();
		realm.beginTransaction();
		realm.delete(Device.class);
		realm.commitTransaction();
	}

	public static void insertEvent(List<Event> events)
	{
		Realm realm = Realm.getDefaultInstance();
		realm.beginTransaction();
		for (Event event : events)
		{
			realm.insertOrUpdate(event);
		}
		realm.commitTransaction();
	}

	public static void dumpEvent()
	{
		Realm realm = Realm.getDefaultInstance();
		realm.beginTransaction();
		realm.delete(Event.class);
		realm.commitTransaction();
	}

	public static RealmResults<Event> getEvent(String device_id)
	{
		Realm realm = Realm.getDefaultInstance();
		RealmResults<Event> events = realm.where(Event.class).equalTo("DeviceID", device_id).findAll();
		return events;
	}


	public static void insertMemberList(String device_id, List<Member> Members)
	{
		Realm realm = Realm.getDefaultInstance();
		String master = realm.where(Device.class).equalTo("DeviceID", device_id).findFirst().getMaster();
		realm.beginTransaction();
		realm.delete(Member.class);
		for (Member member : Members)
		{
			member.setMaster(member.getEmail().equals(master) ? true : false);
			realm.insertOrUpdate(member);
		}
		realm.commitTransaction();
	}


	public static RealmResults<Member> getMember()
	{
		Realm realm = Realm.getDefaultInstance();
		RealmResults<Member> members = realm.where(Member.class).findAll();
		return members;
	}


	public static void dumpDB()
	{
		Realm realm = Realm.getDefaultInstance();
		realm.beginTransaction();
		realm.deleteAll();
		realm.commitTransaction();
	}
}
