package com.nhhackaton.network.api;

import com.nhhackaton.data.SignIn.SignIn;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitApiService {

    /**
     * 회원 관리
     */
    @POST("/")
    Call<SignIn> callSignIn(@Body SignIn signIn);

}
