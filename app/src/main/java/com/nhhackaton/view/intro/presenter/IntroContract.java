package com.nhhackaton.view.intro.presenter;

import android.content.Context;

public interface IntroContract {
    interface View {
        void startSignInActivity();
        void startMainActivity();
    }

    interface Presenter {
        void confirmAutoSignIn(Context context);
    }

}
