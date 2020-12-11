package com.nhhackaton.data.SignIn.source;

import com.nhhackaton.data.SignIn.SignIn;
import com.nhhackaton.network.api.RetrofitApiClient;
import com.nhhackaton.network.api.RetrofitApiService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInRemoteDataSource implements SignInSource{

    private static SignInRemoteDataSource INSTANCE;


    public static SignInRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SignInRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callSignIn(SignIn signIn, SignInApiListener listener) {

        Call<SignIn> result = RetrofitApiClient.getInstance().getRetrofitApiService().callSignIn(signIn);
        result.enqueue(new Callback<SignIn>() {
            @Override
            public void onResponse(Call<SignIn> call, Response<SignIn> response) {
                if(response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(response.body());
                    return;
                }
                listener.onFail("로그인에 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<SignIn> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });

    }
}
