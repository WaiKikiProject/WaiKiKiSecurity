package com.example.a.login.server;

import com.example.a.login.Vo.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by a on 2018-03-25.
 */

public interface ServiceInterface {

    @POST("staffs/session/")
    Call<User> login(@Body User user);
}
