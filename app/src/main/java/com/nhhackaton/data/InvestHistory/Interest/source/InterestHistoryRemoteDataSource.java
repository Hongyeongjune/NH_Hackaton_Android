package com.nhhackaton.data.InvestHistory.Interest.source;


import com.nhhackaton.data.InvestHistory.Interest.InterestHistory;
import com.nhhackaton.data.InvestHistory.Interest.source.InterestHistorySource;
import com.nhhackaton.data.InvestHistory.InvestPagingResponse;
import com.nhhackaton.network.api.RetrofitApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterestHistoryRemoteDataSource implements InterestHistorySource {

    private static InterestHistoryRemoteDataSource INSTANCE;

    public static InterestHistoryRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InterestHistoryRemoteDataSource();
        }

        return INSTANCE;
    }
    
    @Override
    public void callReadInterest(String identity, InterestHistoryApiListener listener) {

        Call<List<InterestHistory>> result = RetrofitApiClient.getInstance().getRetrofitApiService().callReadInterest(identity);

        result.enqueue(new Callback<List<InterestHistory>>() {
            @Override
            public void onResponse(Call<List<InterestHistory>> call, Response<List<InterestHistory>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<InterestHistory> interestHistoryList = response.body();
                    listener.onSuccess(interestHistoryList);
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<List<InterestHistory>> call, Throwable t) {
                listener.onFail("통신 실패");
            }
        });
    }
}
