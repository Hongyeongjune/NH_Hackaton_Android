package com.nhhackaton.data.LatelyHistory.LatelyInvestFinish.source;


import com.nhhackaton.data.LatelyHistory.LatelyInvestFinish.LatelyInvestFinish;
import com.nhhackaton.network.api.RetrofitApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatelyLatelyInvestFinishRemoteDataSource implements LatelyInvestFinishSource {

    private static LatelyLatelyInvestFinishRemoteDataSource INSTANCE;

    public static LatelyLatelyInvestFinishRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LatelyLatelyInvestFinishRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callReadInvestFinish(String identity, InvestFinishApiListener listener) {
        Call<List<LatelyInvestFinish>> result = RetrofitApiClient.getInstance().getRetrofitApiService().callLatelyReadInvestFinish(identity);

        result.enqueue(new Callback<List<LatelyInvestFinish>>() {
            @Override
            public void onResponse(Call<List<LatelyInvestFinish>> call, Response<List<LatelyInvestFinish>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<LatelyInvestFinish> latelyInvestFinishes = response.body();
                    listener.onSuccess(latelyInvestFinishes);
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<List<LatelyInvestFinish>> call, Throwable t) {

            }
        });
    }
}
