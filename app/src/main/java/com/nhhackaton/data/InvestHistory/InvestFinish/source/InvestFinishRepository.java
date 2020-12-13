package com.nhhackaton.data.InvestHistory.InvestFinish.source;

public class InvestFinishRepository implements InvestFinishSource{

    private static InvestFinishRepository investFinishRepository;
    private InvestFinishRemoteDataSource investFinishRemoteDataSource;

    public static InvestFinishRepository getInstance() {
        if(investFinishRepository == null) {
            investFinishRepository = new InvestFinishRepository();
        }

        return investFinishRepository;
    }

    private InvestFinishRepository() {
        investFinishRemoteDataSource = InvestFinishRemoteDataSource.getInstance();
    }

    @Override
    public void callReadInvestFinish(String identity, InvestFinishApiListener listener) {
        investFinishRemoteDataSource.callReadInvestFinish(identity, listener);
    }
}
