package com.nhhackaton.network.api;

import com.nhhackaton.data.DepositMoney.DepositMoney;
import com.nhhackaton.data.SignIn.SignIn;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitApiService {

    /**
     * 로그인
     */
    @POST("/")
    Call<SignIn> callSignIn(@Body SignIn signIn);


    /**
     * 예치금 조회
     */
    @POST("/")
    Call<DepositMoney> callReadDepositMoney(@Body String message);


}
