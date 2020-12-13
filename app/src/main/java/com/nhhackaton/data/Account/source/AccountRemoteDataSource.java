package com.nhhackaton.data.Account.source;

import com.nhhackaton.data.Account.Account;
import com.nhhackaton.data.Account.source.AccountRemoteDataSource;
import com.nhhackaton.data.DepositMoney.DepositMoney;
import com.nhhackaton.network.api.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRemoteDataSource implements AccountSource {

    private static AccountRemoteDataSource INSTANCE;

    public static AccountRemoteDataSource getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new AccountRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callSetAccount(String identity, Account account, AccountApiListener listener) {
        Call<Void> result = RetrofitApiClient.getInstance().getRetrofitApiService().callSetAccount(identity, account);

        result.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess("계좌등록 성공");
                    return;
                }
                listener.onFail("계좌등록 실패");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail("계좌등록 성공");
            }
        });
    }
}
