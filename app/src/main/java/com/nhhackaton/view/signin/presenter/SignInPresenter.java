package com.nhhackaton.view.signin.presenter;

import com.nhhackaton.data.SignIn.SignIn;
import com.nhhackaton.data.SignIn.source.SignInRepository;
import com.nhhackaton.data.SignIn.source.SignInSource;

public class SignInPresenter implements SignInContract.Presenter{

        private SignInContract.View view;
        private final SignInRepository signInRepository;

    public SignInPresenter(SignInContract.View view, SignInRepository signInRepository) {
            this.view = view;
            this.signInRepository = signInRepository;
    }

    @Override
    public void callSignIn(String email, String password) {

        SignIn signIn = SignIn.builder()
                .email(email)
                .password(password)
                .build();

        if(!email.isEmpty() || !password.isEmpty()) {

            signInRepository.callSignIn(signIn, new SignInSource.SignInApiListener() {
                @Override
                public void onSuccess(SignIn signIn) {
                    view.startMainActivity(signIn);
                }

                @Override
                public void onFail(String message) {
                    view.showErrorMessage(message);
                }
            });
        }

        view.showErrorMessage("내용을 입력해주세요");

    }
}
