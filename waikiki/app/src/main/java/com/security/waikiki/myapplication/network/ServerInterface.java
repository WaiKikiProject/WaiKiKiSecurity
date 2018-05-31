package com.security.waikiki.myapplication.network;

import com.security.waikiki.myapplication.entitiy.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServerInterface
{

	@POST("signup/")
	@FormUrlEncoded
	Call<ResponseBody> signUp(@Field("email") String email,@Field("name") String name,@Field("password") String password);

	@POST("login/")
	Call<User> login(@Body User user);

	@POST("token/")
	@FormUrlEncoded
	Call<ResponseBody> sendToken(@Field("email") String email,@Field("token") String token);
}
