package com.nhhackaton.network.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient {
    private final String URL = "http://192.168.0.4:8080/";
    private static final RetrofitApiClient INSTANCE = new RetrofitApiClient();

    private RetrofitApiService retrofitApiService;

    // 통신을 할 시 json 사용과 해당 객체로의 파싱을 위해 생성
    private Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static RetrofitApiClient getInstance() {
        return INSTANCE;
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)   // 서버 주소
            .addConverterFactory(GsonConverterFactory.create(gson)) // Json 사용을 위해 ConvertFactory 추가
            .build();

    // api 사용을 위한 서비스 생성 => 싱글톤
    public RetrofitApiService getRetrofitApiService() {
        if (retrofitApiService == null) {
            retrofitApiService = retrofit.create(RetrofitApiService.class);
        }
        return retrofitApiService;
    }
}
