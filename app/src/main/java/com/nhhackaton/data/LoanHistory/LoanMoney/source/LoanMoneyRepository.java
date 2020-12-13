package com.nhhackaton.data.LoanHistory.LoanMoney.source;

public class LoanMoneyRepository implements LoanMoneySource{

    private static LoanMoneyRepository loanMoneyRepository;
    private LoanMoneyRemoteDataSource loanMoneyRemoteDataSource;

    public static LoanMoneyRepository getInstance() {
        if(loanMoneyRepository == null) {
            loanMoneyRepository = new LoanMoneyRepository();
        }

        return loanMoneyRepository;
    }

    private LoanMoneyRepository() {
        loanMoneyRemoteDataSource = LoanMoneyRemoteDataSource.getInstance();
    }


    @Override
    public void callReadLoanMoney(String identity, LoanMoneyApiListener listener) {
        loanMoneyRemoteDataSource.callReadLoanMoney(identity, listener);
    }
}
