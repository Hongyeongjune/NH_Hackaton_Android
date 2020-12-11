package com.nhhackaton.data.LoanHistory.source;

public class LoanHistoryRepository implements LoanHistorySource {

    private static LoanHistoryRepository loanHistoryRepository;
    private LoanHistoryRemoteDataSource loanHistoryRemoteDataSource;

    public static LoanHistoryRepository getInstance() {
        if(loanHistoryRepository == null) {
            loanHistoryRepository = new LoanHistoryRepository();
        }

        return loanHistoryRepository;
    }

    private LoanHistoryRepository() {
        loanHistoryRemoteDataSource = LoanHistoryRemoteDataSource.getInstance();
    }

    @Override
    public void callReadLoanHistory(int pageNo, LoanHistoryApiListener listener) {
        loanHistoryRemoteDataSource.callReadLoanHistory(pageNo, listener);
    }
}
