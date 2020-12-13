package com.nhhackaton.view.signup.presenter;

import com.nhhackaton.data.SignUp.SignUp;
import com.nhhackaton.data.SignUp.duplicate.source.DuplicateRepository;
import com.nhhackaton.data.SignUp.duplicate.source.DuplicateSource;
import com.nhhackaton.data.SignUp.source.SignUpRepository;
import com.nhhackaton.data.SignUp.source.SignUpSource;

public class SignUpPresenter implements SignUpContract.Presenter{

    private SignUpContract.View view;
    private final SignUpRepository signUpRepository;
    private final DuplicateRepository duplicateRepository;
    private static boolean isCheck = true;

    public SignUpPresenter(SignUpContract.View view, SignUpRepository signUpRepository, DuplicateRepository duplicateRepository) {
        this.view = view;
        this.signUpRepository = signUpRepository;
        this.duplicateRepository = duplicateRepository;
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

            if(!isCheck) {
                signUpRepository.callSignUp(signUp, new SignUpSource.SignUpApiListener() {
                    @Override
                    public void onSuccess() {
                        view.startAccountActivity();
                    }

                    @Override
                    public void onFail(String message) {
                        view.showErrorMessage(message);
                    }
                });
            }
        }

        else view.showErrorMessage("내용을 입력해주세요");
    }

    @Override
    public void callDuplication(String identity) {
        if(!identity.isEmpty())

            duplicateRepository.callDuplicate(identity, new DuplicateSource.DuplicateApiListener() {
                @Override
                public void onSuccess(boolean isDuplicate) {
                    if(isDuplicate) view.showDuplicateMessage("이미 존재하는 아이디입니다.");
                    else {
                        isCheck = isDuplicate;
                        view.showDuplicateMessage("생성 가능한 아이디입니다.");
                    }
                }

                @Override
                public void onFail(String message) {
                    view.showErrorMessage(message);
                }
            });
    }
}
