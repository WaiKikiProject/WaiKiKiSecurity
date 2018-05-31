package com.security.waikiki.myapplication.network;

import com.security.waikiki.myapplication.entitiy.Device;
import com.security.waikiki.myapplication.entitiy.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

	@DELETE("logout/")
	Call<ResponseBody> logout(@Header("email") String email);

	@GET("installlist/{email}/")
	Call<List<Device>> getInstallist(@Path("email") String email);
}
