package com.security.waikiki.myapplication.controller;

import com.google.firebase.iid.FirebaseInstanceId;
import com.security.waikiki.myapplication.db.RealmManager;
import com.security.waikiki.myapplication.entitiy.Device;
import com.security.waikiki.myapplication.entitiy.Event;
import com.security.waikiki.myapplication.entitiy.User;
import com.security.waikiki.myapplication.network.ServerCallBack;
import com.security.waikiki.myapplication.network.ServerManager;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Response;

public class Task {
    private static Task mTask;

    public static Task getInstance() {
        if (mTask == null) {
            mTask = new Task();
        }

        return mTask;
    }

    public void loginTask(final String email, String password, final ControlCallback controlCallback) {

        ServerCallBack serverCallBack = new ServerCallBack() {
            @Override
            public void onResponseResult(Response response) {
                if (response.isSuccessful()) {
                    User user = (User) response.body();
                    RealmManager.insertUser(user);
                    tokenTask(email,controlCallback);
                } else {
                    controlCallback.onError(response.code());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                controlCallback.onFail();
            }
        };

        User user = new User(email, password);
        ServerManager.getInstanse().loginMethod(serverCallBack, user);
    }

    public void logoutTask(final ControlCallback controlCallback) {

        ServerCallBack serverCallBack = new ServerCallBack() {
            @Override
            public void onResponseResult(Response response) {
                if (response.isSuccessful()) {
                    RealmManager.dumpDB();
                    controlCallback.onSucccess();
                } else {
                    controlCallback.onError(response.code());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                controlCallback.onFail();
            }
        };

        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();

        ServerManager.getInstanse().logOutMethod(serverCallBack, user.getUserEmail());
    }

    public void tokenTask(String email, final ControlCallback controlCallback) {
        ServerCallBack serverCallBack = new ServerCallBack() {
            @Override
            public void onResponseResult(Response response) {
                if (response.isSuccessful()) {
                    controlCallback.onSucccess();
                } else {
                    controlCallback.onError(response.code());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
             //   controlCallback.onFail();
            }
        };

        ServerManager.getInstanse().sendToken(serverCallBack, email, FirebaseInstanceId.getInstance().getToken());
    }

    public void getInstallTask(String email, final ControlCallback controlCallback) {
        ServerCallBack serverCallBack = new ServerCallBack() {
            @Override
            public void onResponseResult(Response response) {
                if (response.isSuccessful()) {
                    RealmManager.dumpDevice();
                    List<Device> devices = (List<Device>) response.body();
                    RealmManager.insertDevice(devices);
                    controlCallback.onSucccess();
                } else {
                    controlCallback.onError(response.code());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                controlCallback.onFail();
            }
        };

        ServerManager.getInstanse().installListMethod(serverCallBack, email);
    }

    public void getEvnetTask(String email, final ControlCallback controlCallback) {
        ServerCallBack serverCallBack = new ServerCallBack() {
            @Override
            public void onResponseResult(Response response) {
                if (response.isSuccessful()) {
                    RealmManager.dumpEvent();
                    List<Event> events = (List<Event>) response.body();
                    RealmManager.insertEvent(events);
                    controlCallback.onSucccess();
                } else {
                    controlCallback.onError(response.code());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                controlCallback.onFail();
            }
        };

        ServerManager.getInstanse().eventListMethod(serverCallBack, email);
    }
}
