package com.nhhackaton.data.LatelyHistory.LatelyInvestDeposit.source;


import com.nhhackaton.data.LatelyHistory.LatelyInvestDeposit.LatelyInvestDeposit;
import com.nhhackaton.network.api.RetrofitApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatelyLatelyInvestDepositRemoteDataSource implements LatelyInvestDepositSource {

    private static LatelyLatelyInvestDepositRemoteDataSource INSTANCE;

    public static LatelyLatelyInvestDepositRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LatelyLatelyInvestDepositRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callReadInvestDeposit(String identity, InvestDepositApiListener listener) {
        Call<List<LatelyInvestDeposit>> result = RetrofitApiClient.getInstance().getRetrofitApiService().callLatelyReadInvestDeposit(identity);

        result.enqueue(new Callback<List<LatelyInvestDeposit>>() {
            @Override
            public void onResponse(Call<List<LatelyInvestDeposit>> call, Response<List<LatelyInvestDeposit>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<LatelyInvestDeposit> latelyInvestDeposits = response.body();
                    listener.onSuccess(latelyInvestDeposits);
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<List<LatelyInvestDeposit>> call, Throwable t) {
                listener.onFail("통신 실패");
            }
        });
    }
}
