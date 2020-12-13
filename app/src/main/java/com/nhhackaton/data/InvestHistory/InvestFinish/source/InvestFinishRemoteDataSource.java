package com.nhhackaton.data.InvestHistory.InvestFinish.source;


import com.nhhackaton.data.InvestHistory.InvestFinish.InvestFinish;
import com.nhhackaton.network.api.RetrofitApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvestFinishRemoteDataSource implements InvestFinishSource {

    private static InvestFinishRemoteDataSource INSTANCE;

    public static InvestFinishRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InvestFinishRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callReadInvestFinish(String identity, InvestFinishApiListener listener) {
        Call<List<InvestFinish>> result = RetrofitApiClient.getInstance().getRetrofitApiService().callReadInvestFinish(identity);

        result.enqueue(new Callback<List<InvestFinish>>() {
            @Override
            public void onResponse(Call<List<InvestFinish>> call, Response<List<InvestFinish>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<InvestFinish> investFinishes = response.body();
                    listener.onSuccess(investFinishes);
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<List<InvestFinish>> call, Throwable t) {

            }
        });
    }
}
