package com.nhhackaton.data.LoanHistory.repayment.source;


import com.nhhackaton.data.LoanHistory.repayment.Repayment;
import com.nhhackaton.network.api.RetrofitApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepaymentRemoteDataSource implements RepaymentSource {

    private static RepaymentRemoteDataSource INSTANCE;

    public static RepaymentRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RepaymentRemoteDataSource();
        }

        return INSTANCE;
    }
    
    @Override
    public void callReadInterest(String identity, RepaymentApiListener listener) {

        Call<List<Repayment>> result = RetrofitApiClient.getInstance().getRetrofitApiService().callReadInterestBorrow(identity);

        result.enqueue(new Callback<List<Repayment>>() {
            @Override
            public void onResponse(Call<List<Repayment>> call, Response<List<Repayment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Repayment> repaymentList = response.body();
                    listener.onSuccess(repaymentList);
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<List<Repayment>> call, Throwable t) {
                listener.onFail("통신 실패");
            }
        });
    }
}
