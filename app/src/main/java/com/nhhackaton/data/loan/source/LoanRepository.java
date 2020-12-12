package com.nhhackaton.data.loan.source;

import com.nhhackaton.data.loan.LoanApply;

public class LoanRepository implements LoanSource {

    private static LoanRepository loanRepository;
    private LoanRemoteDataSource loanRemoteDataSource;

    public static LoanRepository getInstance() {
        if(loanRepository == null) {
            loanRepository = new LoanRepository();
        }

        return loanRepository;
    }

    private LoanRepository() {
        loanRemoteDataSource = LoanRemoteDataSource.getInstance();
    }

    @Override
    public void callLoanApply(LoanApply loanApply, LoanApiListener listener) {
        loanRemoteDataSource.callLoanApply(loanApply, listener);
    }
}
