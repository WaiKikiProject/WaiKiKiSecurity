package com.security.waikiki.myapplication.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

abstract public class ServerCallBack<T> implements Callback<T>
{
	@Override
	public void onResponse(Call<T> call, Response<T> response)
	{
		onResponseResult(response);
	}

	@Override
	public void onFailure(Call<T> call, Throwable t)
	{

	}

	abstract public void onResponseResult(Response<T> response);
}
