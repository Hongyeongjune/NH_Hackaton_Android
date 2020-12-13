package com.nhhackaton.data.LatelyHistory.LatelyInvestDeposit.source;

public class LatelyLatelyInvestDepositRepository implements LatelyInvestDepositSource {
    private static LatelyLatelyInvestDepositRepository latelyInvestDepositRepository;
    private LatelyLatelyInvestDepositRemoteDataSource latelyInvestDepositRemoteDataSource;

    public static LatelyLatelyInvestDepositRepository getInstance() {
        if(latelyInvestDepositRepository == null) {
            latelyInvestDepositRepository = new LatelyLatelyInvestDepositRepository();
        }

        return latelyInvestDepositRepository;
    }

    private LatelyLatelyInvestDepositRepository() {
        latelyInvestDepositRemoteDataSource = LatelyLatelyInvestDepositRemoteDataSource.getInstance();
    }

    @Override
    public void callReadInvestDeposit(String identity, InvestDepositApiListener listener) {
        latelyInvestDepositRemoteDataSource.callReadInvestDeposit(identity, listener);
    }
}
