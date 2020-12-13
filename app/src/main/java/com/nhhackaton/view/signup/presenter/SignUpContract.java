package com.nhhackaton.view.signup.presenter;

import com.nhhackaton.data.SignUp.SignUp;

public interface SignUpContract {

    interface View {
        void showErrorMessage(String message);
        void showDuplicateMessage(String message);
        void startAccountActivity();

    }

    interface Presenter {
        void callSignUp(String identity, String password, String birth, String name);
        void callDuplication(String identity);
    }
}
