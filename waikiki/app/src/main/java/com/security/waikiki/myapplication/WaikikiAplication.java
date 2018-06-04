package com.security.waikiki.myapplication;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class WaikikiAplication extends Application
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		Realm.init(this);

		// Use the config
		RealmConfiguration config = new RealmConfiguration.Builder()
			.name("wikiki.realm")
			.schemaVersion(42)
			.build();

		Realm.setDefaultConfiguration(config);


	}
}
