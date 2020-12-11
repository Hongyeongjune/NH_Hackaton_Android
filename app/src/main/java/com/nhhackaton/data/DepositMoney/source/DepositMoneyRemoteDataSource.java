package com.nhhackaton.data.DepositMoney.source;

import com.nhhackaton.data.DepositMoney.DepositMoney;
import com.nhhackaton.network.api.RetrofitApiClient;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DepositMoneyRemoteDataSource implements DepositMoneySource {

    private static DepositMoneyRemoteDataSource INSTANCE;

    public static DepositMoneyRemoteDataSource getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DepositMoneyRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callReadDepositMoney(String message, VirtualAccountApiListener listener) {

        Call<DepositMoney> result = RetrofitApiClient.getInstance().getRetrofitApiService().callReadDepositMoney(message);

        result.enqueue(new Callback<DepositMoney>() {
            @Override
            public void onResponse(Call<DepositMoney> call, Response<DepositMoney> response) {
                if (response.isSuccessful() && response.body() != null) {
                    DepositMoney virtualAccount = response.body();
                    listener.onSuccess(virtualAccount.getMessage());
                    return;
                }
                listener.onFail("예치금 조회를 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<DepositMoney> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });

    }
}
