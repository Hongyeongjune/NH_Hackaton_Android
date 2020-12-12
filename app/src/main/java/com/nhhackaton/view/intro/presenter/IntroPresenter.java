package com.nhhackaton.view.intro.presenter;

import android.content.Context;

import com.nhhackaton.util.SharedPreferencesUtils;

public class IntroPresenter implements IntroContract.Presenter {

    private IntroContract.View view;

    public IntroPresenter(IntroContract.View view) {
        this.view = view;
    }

    @Override
    public void confirmAutoSignIn(Context context) {
        if(SharedPreferencesUtils.readAutoSignInFromToken(context)) {
            view.startMainActivity();
        }
        else {
            view.startSignInActivity();
        }
    }
}
