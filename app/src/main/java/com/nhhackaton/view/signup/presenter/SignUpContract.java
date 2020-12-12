package com.nhhackaton.view.signup.presenter;

import com.nhhackaton.data.SignUp.SignUp;

public interface SignUpContract {

    interface View {
        void showErrorMessage(String message);
        void startSignInActivity();

    }

    interface Presenter {
        void callSignUp(String identity, String password, String birth, String name);
    }
}
