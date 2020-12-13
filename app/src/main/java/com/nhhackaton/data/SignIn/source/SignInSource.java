package com.nhhackaton.data.SignIn.source;

import com.nhhackaton.data.SignIn.SignIn;

public interface SignInSource {

    interface SignInApiListener {
        void onSuccess(String identity);
        void onFail(String message);
    }

    void callSignIn(SignIn signIn, SignInApiListener listener);

}
