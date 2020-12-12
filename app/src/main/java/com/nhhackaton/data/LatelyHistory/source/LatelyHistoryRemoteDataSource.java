package com.nhhackaton.data.LatelyHistory.source;

import com.nhhackaton.data.LatelyHistory.LatelyPagingResponse;
import com.nhhackaton.data.LoanHistory.LoanPagingResponse;
import com.nhhackaton.network.api.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatelyHistoryRemoteDataSource implements LatelyHistorySource {

    private static LatelyHistoryRemoteDataSource INSTANCE;

    public static LatelyHistoryRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LatelyHistoryRemoteDataSource();
        }

        return INSTANCE;
    }
    
    @Override
    public void callReadLatelyHistory(LatelyHistoryApiListener listener) {
        Call<LatelyPagingResponse> result = RetrofitApiClient.getInstance().getRetrofitApiService().callReadLatelyHistory();

        result.enqueue(new Callback<LatelyPagingResponse>() {
            @Override
            public void onResponse(Call<LatelyPagingResponse> call, Response<LatelyPagingResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LatelyPagingResponse latelyPagingResponse = response.body();
                    listener.onSuccess(latelyPagingResponse);
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<LatelyPagingResponse> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }
}
