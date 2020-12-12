package com.nhhackaton.view.loan.presenter;

import com.nhhackaton.data.loan.LoanApply;

public interface LoanContract {

    interface View {
        void showErrorMessage(String message);
        void startMainActivity();

    }

    interface Presenter {
        void callLoanApply(LoanApply loanApply);
    }
}
