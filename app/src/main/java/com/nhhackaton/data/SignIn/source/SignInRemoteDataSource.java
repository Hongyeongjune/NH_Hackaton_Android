package com.nhhackaton.data.SignIn.source;

import com.nhhackaton.data.SignIn.SignIn;
import com.nhhackaton.data.SignIn.SignInResponse;
import com.nhhackaton.network.api.RetrofitApiClient;
import com.nhhackaton.network.api.RetrofitApiService;
import com.nhhackaton.util.SharedPreferencesUtils;

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

        Call<SignInResponse> result = RetrofitApiClient.getInstance().getRetrofitApiService().callSignIn(signIn);

        result.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    SignInResponse signInResponse = response.body();
                    listener.onSuccess(signInResponse.getIdentity());
                    return;
                }
                listener.onFail("로그인에 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });


    }
}
