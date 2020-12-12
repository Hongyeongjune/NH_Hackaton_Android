package com.nhhackaton.view.signup.presenter;

import com.nhhackaton.data.SignUp.SignUp;
import com.nhhackaton.data.SignUp.source.SignUpRepository;
import com.nhhackaton.data.SignUp.source.SignUpSource;

public class SignUpPresenter implements SignUpContract.Presenter{

    private SignUpContract.View view;
    private final SignUpRepository signUpRepository;

    public SignUpPresenter(SignUpContract.View view, SignUpRepository signUpRepository) {
        this.view = view;
        this.signUpRepository = signUpRepository;
    }


    @Override
    public void callSignUp(String identity, String password, String birth, String name) {

        SignUp signUp = SignUp.builder()
                .identity(identity)
                .password(password)
                .birth(birth)
                .name(name)
                .build();

        if(!identity.isEmpty() || !password.isEmpty() || !birth.isEmpty() || !name.isEmpty()) {

            signUpRepository.callSignUp(signUp, new SignUpSource.SignUpApiListener() {
                @Override
                public void onSuccess(SignUp signUp) {
                    view.startSignInActivity();
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
