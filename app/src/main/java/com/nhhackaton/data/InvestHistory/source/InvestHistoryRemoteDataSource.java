package com.nhhackaton.data.InvestHistory.source;

import com.nhhackaton.data.InvestHistory.InvestPagingResponse;
import com.nhhackaton.network.api.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvestHistoryRemoteDataSource implements InvestHistorySource {

    private static InvestHistoryRemoteDataSource INSTANCE;

    public static InvestHistoryRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InvestHistoryRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callReadInvestHistory(int pageNo, InvestHistoryApiListener listener) {

        Call<InvestPagingResponse> result = RetrofitApiClient.getInstance().getRetrofitApiService().callReadInvestHistory(pageNo);

        result.enqueue(new Callback<InvestPagingResponse>() {
            @Override
            public void onResponse(Call<InvestPagingResponse> call, Response<InvestPagingResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    InvestPagingResponse investPagingResponse = response.body();
                    listener.onSuccess(investPagingResponse);
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<InvestPagingResponse> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }
}
