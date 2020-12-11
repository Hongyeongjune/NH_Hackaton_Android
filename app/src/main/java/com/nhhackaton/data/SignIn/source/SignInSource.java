package com.nhhackaton.data.SignIn.source;

import com.nhhackaton.data.SignIn.SignIn;

public interface SignInSource {

    interface SignInApiListener {
        void onSuccess(SignIn signIn);
        void onFail(String message);
    }

    void callSignIn(SignIn signIn, SignInApiListener listener);

}
