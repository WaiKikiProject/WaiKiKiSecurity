package com.example.a.login.server;

import com.example.a.login.Vo.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by a on 2018-03-25.
 */

public class RetrofitConnect {
    public static final String baseUrl = "https://api.hospitality-staging.imgate.co.kr/v2/";


    private ServiceInterface getService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        ServiceInterface service = retrofit.create(ServiceInterface.class);

        return service;
    }

    public Call loginConnec(String email, String password){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Call<User> call = getService().login(user);
        return call;
    }

    ;
}