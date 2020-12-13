package com.nhhackaton.view.account.presenter;

import com.nhhackaton.data.Account.Account;
import com.nhhackaton.data.SignIn.SignIn;

public interface AccountContract {
    interface View {
        void showErrorMessage(String message);
        void startSignInActivity();
    }

    interface Presenter {
        void callSetAccount(String identity, String acno, String bncd);
    }
}
