package com.nhhackaton.data.InvestHistory.InvestDeposit.source;


import com.nhhackaton.data.InvestHistory.InvestDeposit.InvestDeposit;
import com.nhhackaton.network.api.RetrofitApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvestDepositRemoteDataSource implements InvestDepositSource {

    private static InvestDepositRemoteDataSource INSTANCE;

    public static InvestDepositRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InvestDepositRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callReadInvestDeposit(String identity, InvestDepositApiListener listener) {
        Call<List<InvestDeposit>> result = RetrofitApiClient.getInstance().getRetrofitApiService().callReadInvestDeposit(identity);

        result.enqueue(new Callback<List<InvestDeposit>>() {
            @Override
            public void onResponse(Call<List<InvestDeposit>> call, Response<List<InvestDeposit>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<InvestDeposit> investDeposits = response.body();
                    listener.onSuccess(investDeposits);
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<List<InvestDeposit>> call, Throwable t) {
                listener.onFail("통신 실패");
            }
        });
    }
}
