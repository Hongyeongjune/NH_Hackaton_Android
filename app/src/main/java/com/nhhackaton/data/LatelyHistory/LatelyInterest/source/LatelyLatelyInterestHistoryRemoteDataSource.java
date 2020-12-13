package com.nhhackaton.data.LatelyHistory.LatelyInterest.source;


import com.nhhackaton.data.LatelyHistory.LatelyInterest.LatelyInterestHistory;
import com.nhhackaton.network.api.RetrofitApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatelyLatelyInterestHistoryRemoteDataSource implements LatelyInterestHistorySource {

    private static LatelyLatelyInterestHistoryRemoteDataSource INSTANCE;

    public static LatelyLatelyInterestHistoryRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LatelyLatelyInterestHistoryRemoteDataSource();
        }

        return INSTANCE;
    }
    
    @Override
    public void callReadInterest(String identity, InterestHistoryApiListener listener) {

        Call<List<LatelyInterestHistory>> result = RetrofitApiClient.getInstance().getRetrofitApiService().callLatelyReadInterest(identity);

        result.enqueue(new Callback<List<LatelyInterestHistory>>() {
            @Override
            public void onResponse(Call<List<LatelyInterestHistory>> call, Response<List<LatelyInterestHistory>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<LatelyInterestHistory> latelyInterestHistoryList = response.body();
                    listener.onSuccess(latelyInterestHistoryList);
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<List<LatelyInterestHistory>> call, Throwable t) {
                listener.onFail("통신 실패");
            }
        });
    }
}
