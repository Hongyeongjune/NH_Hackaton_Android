package com.nhhackaton.data.Invest.source;

import com.nhhackaton.data.Invest.Invest;
import com.nhhackaton.data.Invest.source.InvestRemoteDataSource;
import com.nhhackaton.network.api.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvestRemoteDataSource implements InvestSource {

    private static InvestRemoteDataSource INSTANCE;

    public static InvestRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InvestRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callApplyInvest(Invest invest, InvestApiListener listener) {

        Call<Void> result = RetrofitApiClient.getInstance().getRetrofitApiService().callApplyMoney(invest);

        result.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess();
                    return;
                }
                listener.onFail("투자 신청 실패");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail("통신 실패");
            }
        });
    }
}
