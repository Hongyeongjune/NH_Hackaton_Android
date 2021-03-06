package com.nhhackaton.view.signin.presenter;

import com.nhhackaton.data.SignIn.SignIn;

public interface SignInContract {

    interface View {
        void showErrorMessage(String message);
        void startMainActivity(String identity);
        void startSignUpActivity();
    }

    interface Presenter {
        void callSignIn(String email, String password);
    }

}
