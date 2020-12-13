package com.nhhackaton.data.InvestHistory.Interest.source;


import com.nhhackaton.data.InvestHistory.Interest.source.InterestHistoryRemoteDataSource;
import com.nhhackaton.data.InvestHistory.Interest.source.InterestHistorySource;

public class InterestHistoryRepository implements InterestHistorySource {

    private static InterestHistoryRepository interestHistoryRepository;
    private InterestHistoryRemoteDataSource interestHistoryRemoteDataSource;

    public static InterestHistoryRepository getInstance() {
        if(interestHistoryRepository == null) {
            interestHistoryRepository = new InterestHistoryRepository();
        }

        return interestHistoryRepository;
    }

    private InterestHistoryRepository() {
        interestHistoryRemoteDataSource = InterestHistoryRemoteDataSource.getInstance();
    }


    @Override
    public void callReadInterest(String identity, InterestHistoryApiListener listener) {
        interestHistoryRemoteDataSource.callReadInterest(identity, listener);
    }
}
