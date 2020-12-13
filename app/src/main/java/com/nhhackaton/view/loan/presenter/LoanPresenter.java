package com.nhhackaton.view.loan.presenter;

import com.nhhackaton.data.Invest.Invest;
import com.nhhackaton.data.Invest.source.InvestSource;
import com.nhhackaton.data.SignIn.SignIn;
import com.nhhackaton.data.SignIn.source.SignInSource;
import com.nhhackaton.data.loan.LoanApply;
import com.nhhackaton.data.loan.source.LoanRepository;
import com.nhhackaton.data.loan.source.LoanSource;

public class LoanPresenter implements LoanContract.Presenter{

    private LoanContract.View view;
    private final LoanRepository loanRepository;

    public LoanPresenter(LoanContract.View view, LoanRepository loanRepository) {
        this.view = view;
        this.loanRepository = loanRepository;
    }

    @Override
    public void callLoanApply(LoanApply loanApply) {

        if(!loanApply.getLoanAmount().isEmpty()) {
            loanRepository.callLoanApply(loanApply, new LoanSource.LoanApiListener() {

                @Override
                public void onSuccess(LoanApply loanApply) {
                    view.startMainActivity();
                }

                @Override
                public void onFail(String message) {
                    view.showErrorMessage(message);
                }
            });
        }

        view.showErrorMessage("내용을 입력하세요");

    }
}