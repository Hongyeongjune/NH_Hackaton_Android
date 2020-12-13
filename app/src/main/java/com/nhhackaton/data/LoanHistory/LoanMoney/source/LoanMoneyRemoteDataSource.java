package com.nhhackaton.data.LoanHistory.LoanMoney.source;

import com.nhhackaton.data.InvestHistory.Interest.InterestHistory;
import com.nhhackaton.data.LoanHistory.LoanMoney.LoanMoney;
import com.nhhackaton.network.api.RetrofitApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanMoneyRemoteDataSource implements LoanMoneySource {

    private static LoanMoneyRemoteDataSource INSTANCE;

    public static LoanMoneyRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoanMoneyRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callReadLoanMoney(String identity, LoanMoneyApiListener listener) {
        Call<List<LoanMoney>> result = RetrofitApiClient.getInstance().getRetrofitApiService().callReadLoanMoney(identity);

        result.enqueue(new Callback<List<LoanMoney>>() {
            @Override
            public void onResponse(Call<List<LoanMoney>> call, Response<List<LoanMoney>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<LoanMoney> loanMonies = response.body();
                    listener.onSuccess(loanMonies);
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<List<LoanMoney>> call, Throwable t) {
                listener.onFail("통신 실패");
            }
        });
    }
}
