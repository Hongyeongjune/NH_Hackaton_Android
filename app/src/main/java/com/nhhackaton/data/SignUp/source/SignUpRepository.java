package com.nhhackaton.data.SignUp.source;

import com.nhhackaton.data.SignUp.SignUp;

public class SignUpRepository implements SignUpSource {

    private static SignUpRepository signUpRepository;
    private SignUpRemoteDataSource signUpRemoteDataSource;

    public static SignUpRepository getInstance() {
        if(signUpRepository == null) {
            signUpRepository = new SignUpRepository();
        }

        return signUpRepository;
    }

    private SignUpRepository() {
        signUpRemoteDataSource = SignUpRemoteDataSource.getInstance();
    }

    @Override
    public void callSignUp(SignUp signUp, SignUpApiListener listener) {
        signUpRemoteDataSource.callSignUp(signUp, listener);
    }
}
