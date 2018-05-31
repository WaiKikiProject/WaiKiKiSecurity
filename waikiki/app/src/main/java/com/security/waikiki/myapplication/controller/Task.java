package com.security.waikiki.myapplication.controller;

import com.security.waikiki.myapplication.entitiy.User;
import com.security.waikiki.myapplication.network.ServerCallBack;
import com.security.waikiki.myapplication.network.ServerManager;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Response;

public class Task
{
	private static Task mTask;

	public static Task getInstance(){
		if(mTask == null){
			mTask = new Task();
		}

		return mTask;
	}

	public void loginTask(String email, String password, final ControlCallback controlCallback)
	{

		ServerCallBack serverCallBack = new ServerCallBack()
		{
			@Override
			public void onResponseResult(Response response)
			{
				if (response.isSuccessful())
				{
					controlCallback.onSucccess();
					User user = (User)response.body();
					Realm realm = Realm.getDefaultInstance();
					realm.beginTransaction();
					realm.insert(user);
					realm.commitTransaction();
				}
				else {
					controlCallback.onError(response.code());
				}
			}

			@Override
			public void onFailure(Call call, Throwable t)
			{
				super.onFailure(call, t);
				controlCallback.onFail();
			}
		};

		User user = new User(email, password);
		ServerManager.getInstanse().loginMethod(serverCallBack,user);

	}
}
