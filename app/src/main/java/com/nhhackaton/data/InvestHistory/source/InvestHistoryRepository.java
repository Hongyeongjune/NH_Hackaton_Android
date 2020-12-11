package com.nhhackaton.data.InvestHistory.source;

public class InvestHistoryRepository implements InvestHistorySource {

    private static InvestHistoryRepository investHistoryRepository;
    private InvestHistoryRemoteDataSource investHistoryRemoteDataSource;

    public static InvestHistoryRepository getInstance() {
        if(investHistoryRepository == null) {
            investHistoryRepository = new InvestHistoryRepository();
        }

        return investHistoryRepository;
    }

    private InvestHistoryRepository() {
        investHistoryRemoteDataSource = InvestHistoryRemoteDataSource.getInstance();
    }

    @Override
    public void callReadInvestHistory(int pageNo, InvestHistoryApiListener listener) {
        investHistoryRemoteDataSource.callReadInvestHistory(pageNo, listener);
    }
}
