package com.nhhackaton.data.Invest.source;

import com.nhhackaton.data.Invest.Invest;
import com.nhhackaton.data.Invest.source.InvestRemoteDataSource;
import com.nhhackaton.data.Invest.source.InvestRepository;
import com.nhhackaton.data.Invest.source.InvestRemoteDataSource;

public class InvestRepository implements InvestSource {

    private static InvestRepository investRepository;
    private InvestRemoteDataSource investRemoteDataSource;

    public static InvestRepository getInstance() {
        if(investRepository == null) {
            investRepository = new InvestRepository();
        }

        return investRepository;
    }

    private InvestRepository() {
        investRemoteDataSource = InvestRemoteDataSource.getInstance();
    }
    
    @Override
    public void callApplyInvest(String identity, Invest invest, InvestApiListener listener) {
        investRemoteDataSource.callApplyInvest(identity, invest, listener);
    }
}
