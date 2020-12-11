package com.nhhackaton.data.LoanHistory.source;

import com.nhhackaton.data.LoanHistory.LoanPagingResponse;
import com.nhhackaton.network.api.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanHistoryRemoteDataSource implements LoanHistorySource {

    private static LoanHistoryRemoteDataSource INSTANCE;

    public static LoanHistoryRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoanHistoryRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callReadLoanHistory(int pageNo, LoanHistoryApiListener listener) {

        Call<LoanPagingResponse> result = RetrofitApiClient.getInstance().getRetrofitApiService().callReadLoanHistory(pageNo);

        result.enqueue(new Callback<LoanPagingResponse>() {
            @Override
            public void onResponse(Call<LoanPagingResponse> call, Response<LoanPagingResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoanPagingResponse loanPagingResponse = response.body();
                    listener.onSuccess(loanPagingResponse);
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<LoanPagingResponse> call, Throwable t) {

            }
        });
    }
}
