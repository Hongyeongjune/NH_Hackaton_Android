package com.nhhackaton.data.SignUp.duplicate.source;

import com.nhhackaton.network.api.RetrofitApiClient;
import com.nhhackaton.util.LogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DuplicateRemoteDataSource implements DuplicateSource {

    private static DuplicateRemoteDataSource INSTANCE;

    public static DuplicateRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DuplicateRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callDuplicate(String identity, DuplicateApiListener listener) {
        Call<Boolean> result = RetrofitApiClient.getInstance().getRetrofitApiService().callDuplicate(identity);

        result.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body() != null) {
                    boolean isDuplicate = response.body();
                    LogUtils.logInfo(isDuplicate + " ");
                    listener.onSuccess(isDuplicate);
                    return;
                }
                listener.onFail("중복확인 실패");
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFail("통신 실패");
            }
        });
    }
}
