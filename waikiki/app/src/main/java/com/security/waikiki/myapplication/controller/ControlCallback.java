package com.security.waikiki.myapplication.controller;

public interface ControlCallback
{
	void onSucccess();
	void onError(int code);
	void onFail();
}
