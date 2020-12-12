package com.nhhackaton.network.api;

import com.nhhackaton.data.DepositMoney.DepositMoney;
import com.nhhackaton.data.InvestHistory.InvestPagingResponse;
import com.nhhackaton.data.LatelyHistory.LatelyHistory;
import com.nhhackaton.data.LatelyHistory.LatelyPagingResponse;
import com.nhhackaton.data.LoanHistory.LoanPagingResponse;
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


    /**
     * 투자 현황 조회
     * 대출 현황 조회
     * 최근 거래 내역 조회
     */
    @POST("/")
    Call<InvestPagingResponse> callReadInvestHistory(@Body int pageNo);
    @POST("/")
    Call<LoanPagingResponse> callReadLoanHistory(@Body int pageNo);
    @POST("/")
    Call<LatelyPagingResponse> callReadLatelyHistory();


}
