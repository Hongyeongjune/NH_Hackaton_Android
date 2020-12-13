package com.nhhackaton.data.loan.source;

import com.nhhackaton.data.loan.LoanApply;
import com.nhhackaton.network.api.RetrofitApiClient;
import com.nhhackaton.util.LogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanRemoteDataSource implements LoanSource {

    private static LoanRemoteDataSource INSTANCE;

    public static LoanRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoanRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callLoanApply(LoanApply loanApply, LoanApiListener listener) {

        LogUtils.logInfo(loanApply.toString());

        Call<Void> result = RetrofitApiClient.getInstance().getRetrofitApiService().callLoanApply(loanApply);

        result.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess();
                    return;
                }
                listener.onFail("대출 신청 실패");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });

    }
}
