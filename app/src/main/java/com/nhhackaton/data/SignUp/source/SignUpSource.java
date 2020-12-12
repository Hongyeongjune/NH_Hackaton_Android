package com.nhhackaton.data.SignUp.source;

import com.nhhackaton.data.SignUp.SignUp;

public interface SignUpSource {

    interface SignUpApiListener {
        void onSuccess(SignUp signUp);
        void onFail(String message);
    }

    void callSignUp(SignUp signUp, SignUpApiListener listener);
}
