package com.nhhackaton.data.SignUp.source;

import com.nhhackaton.data.SignUp.SignUp;

import com.nhhackaton.network.api.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpRemoteDataSource implements SignUpSource {

    private static SignUpRemoteDataSource INSTANCE;

    public static SignUpRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SignUpRemoteDataSource();
        }

        return INSTANCE;
    }


    @Override
    public void callSignUp(SignUp signUp, SignUpApiListener listener) {
        Call<SignUp> result = RetrofitApiClient.getInstance().getRetrofitApiService().callSignUp(signUp);

        result.enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SignUp signUp = response.body();
                    listener.onSuccess(signUp);
                    return;
                }
                listener.onFail("대출 신청 실패");
            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });

    }
}
