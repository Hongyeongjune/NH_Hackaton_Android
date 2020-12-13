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
        Call<Void> result = RetrofitApiClient.getInstance().getRetrofitApiService().callSignUp(signUp);

        result.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess();
                    return;
                }
                listener.onFail("회원가입 실패");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail("통신 실패");
            }
        });

    }
}
