package com.nhhackaton.data.SignUp.source;

import com.nhhackaton.data.SignUp.SignUp;

public interface SignUpSource {

    interface SignUpApiListener {
        void onSuccess();
        void onFail(String message);
    }

    void callSignUp(SignUp signUp, SignUpApiListener listener);
}
