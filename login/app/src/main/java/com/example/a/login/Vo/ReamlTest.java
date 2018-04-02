package com.example.a.login.Vo;

import android.app.Application;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

/**
 * Created by a on 2018-03-28.
 */

public class ReamlTest extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .schemaVersion(1)
                .migration(new RealmMigration(){
                    @Override
                    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

                    }
                }).build();
        realm = Realm.getInstance(config);
        realm.close();
    }
}
