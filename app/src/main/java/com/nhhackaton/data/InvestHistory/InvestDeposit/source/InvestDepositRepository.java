package com.nhhackaton.data.InvestHistory.InvestDeposit.source;

import com.nhhackaton.data.InvestHistory.InvestDeposit.source.InvestDepositRemoteDataSource;
import com.nhhackaton.data.InvestHistory.InvestDeposit.source.InvestDepositSource;

public class InvestDepositRepository implements InvestDepositSource {
    private static InvestDepositRepository investDepositRepository;
    private InvestDepositRemoteDataSource investDepositRemoteDataSource;

    public static InvestDepositRepository getInstance() {
        if(investDepositRepository == null) {
            investDepositRepository = new InvestDepositRepository();
        }

        return investDepositRepository;
    }

    private InvestDepositRepository() {
        investDepositRemoteDataSource = InvestDepositRemoteDataSource.getInstance();
    }

    @Override
    public void callReadInvestDeposit(String identity, InvestDepositApiListener listener) {
        investDepositRemoteDataSource.callReadInvestDeposit(identity, listener);
    }
}
