package com.nhhackaton.view.loan.presenter;

import com.nhhackaton.data.SignIn.SignIn;
import com.nhhackaton.data.SignIn.source.SignInSource;
import com.nhhackaton.data.loan.LoanApply;
import com.nhhackaton.data.loan.source.LoanRepository;

public class LoanPresenter implements LoanContract.Presenter{

    private LoanContract.View view;
    private final LoanRepository loanRepository;

    public LoanPresenter(LoanContract.View view, LoanRepository loanRepository) {
        this.view = view;
        this.loanRepository = loanRepository;
    }

    @Override
    public void callLoanApply(LoanApply loanApply) {


        view.showErrorMessage("내용을 입력해주세요");

    }
}
