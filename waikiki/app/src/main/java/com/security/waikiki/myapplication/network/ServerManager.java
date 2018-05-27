package com.security.waikiki.myapplication.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerManager {
    private static ServerManager mServerManager;
    private static Retrofit mRetropit;
    private static ServerInterface mServerInterface;

    public static ServerManager getInstanse() {
        if (mServerManager == null) {
            mServerManager = new ServerManager();
        }
        return mServerManager;
    }

    private ServerManager() {
        mRetropit = new Retrofit.Builder()
                .baseUrl(ServerConfig.BASE_URL)
                .client(gethttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mServerInterface = mRetropit.create(ServerInterface.class);
    }


    private static OkHttpClient gethttpClient() {
        // TimeOut 주는 방법
        // 1. OkHttpClient 객체준비
        // 2. OkHttpClient 객체를 Bulid하기전에 Timeout메소드 들에 값을 넣음
        //   readTimeout(원하는 시간 Int, Timeunit.원하는 시간단위) -> 읽어오는 시간 Timeout
        //   connectTimeout(원하는 시간 Int, Timeunit.원하는 시간단위) -> 연결하는 시간 Timeout
        //   writeTimeout(원하는 시간 Int, Timeunit.원하는 시간단위) -> 쓰는데 걸리는 시간 Timeout
        // 3. Retrofit 객체가 Build되기전에 clinet("Put this Method")에 OkhttpClinet 객체를 넣어준다.
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(ServerConfig.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(ServerConfig.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(ServerConfig.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return httpClient;
    }


    public void loginMethod(ServerCallBack callBack, String email, String password) {
        Call<ResponseBody> call = mServerInterface.login(email, password);
        call.enqueue(callBack);
    }

    public void signUpMethod(ServerCallBack callBack, String email, String name, String password) {
        Call<ResponseBody> call = mServerInterface.signUp(email, name, password);
        call.enqueue(callBack);
    }

    public void sendToken(ServerCallBack callBack, String email, String token) {
        Call<ResponseBody> call = mServerInterface.sendToken(email, token);
        call.enqueue(callBack);
    }
}
