package com.security.waikiki.myapplication.network;

import com.security.waikiki.myapplication.entitiy.Device;
import com.security.waikiki.myapplication.entitiy.Event;
import com.security.waikiki.myapplication.entitiy.Member;
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

public interface ServerInterface {

    @POST("signup/")
    @FormUrlEncoded
    Call<ResponseBody> signUp(@Field("email") String email, @Field("name") String name, @Field("password") String password);

    @POST("login/")
    Call<User> login(@Body User user);

    @POST("token/")
    @FormUrlEncoded
    Call<ResponseBody> sendToken(@Field("email") String email, @Field("token") String token);

    @DELETE("logout/")
    Call<ResponseBody> logout(@Header("email") String email);

    @GET("installlist/{email}/")
    Call<List<Device>> getInstallList(@Path("email") String email);

    @GET("eventlist/{email}/")
    Call<List<Event>> getEvnetList(@Path("email") String email);

    @POST("invite/")
    @FormUrlEncoded
    Call<ResponseBody> invite(@Field("email") String email, @Field("device_id") String device_id);

<<<<<<< HEAD
	@POST("login/")
	@FormUrlEncoded
	Call<ResponseBody> login(@Field("email") String email,@Field("password") String password);
=======
    @POST("convert/")
    @FormUrlEncoded
    Call<ResponseBody> convert(@Field("email") String email, @Field("device_id") String device_id);

    @GET("memberlist/{device_id}/")
    Call<List<Member>> getMemberList(@Path("device_id") String device_id);
>>>>>>> android_feature
}
