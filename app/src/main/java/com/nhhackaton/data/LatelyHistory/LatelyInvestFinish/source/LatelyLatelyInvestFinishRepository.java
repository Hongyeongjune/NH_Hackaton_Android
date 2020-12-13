package com.nhhackaton.data.LatelyHistory.LatelyInvestFinish.source;

public class LatelyLatelyInvestFinishRepository implements LatelyInvestFinishSource {

    private static LatelyLatelyInvestFinishRepository latelyInvestFinishRepository;
    private LatelyLatelyInvestFinishRemoteDataSource latelyInvestFinishRemoteDataSource;

    public static LatelyLatelyInvestFinishRepository getInstance() {
        if(latelyInvestFinishRepository == null) {
            latelyInvestFinishRepository = new LatelyLatelyInvestFinishRepository();
        }

        return latelyInvestFinishRepository;
    }

    private LatelyLatelyInvestFinishRepository() {
        latelyInvestFinishRemoteDataSource = LatelyLatelyInvestFinishRemoteDataSource.getInstance();
    }

    @Override
    public void callReadInvestFinish(String identity, InvestFinishApiListener listener) {
        latelyInvestFinishRemoteDataSource.callReadInvestFinish(identity, listener);
    }
}
