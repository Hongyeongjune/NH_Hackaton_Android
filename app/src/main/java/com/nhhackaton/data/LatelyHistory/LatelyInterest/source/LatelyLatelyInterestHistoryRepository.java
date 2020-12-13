package com.nhhackaton.data.LatelyHistory.LatelyInterest.source;


public class LatelyLatelyInterestHistoryRepository implements LatelyInterestHistorySource {

    private static LatelyLatelyInterestHistoryRepository latelyInterestHistoryRepository;
    private LatelyLatelyInterestHistoryRemoteDataSource latelyInterestHistoryRemoteDataSource;

    public static LatelyLatelyInterestHistoryRepository getInstance() {
        if(latelyInterestHistoryRepository == null) {
            latelyInterestHistoryRepository = new LatelyLatelyInterestHistoryRepository();
        }

        return latelyInterestHistoryRepository;
    }

    private LatelyLatelyInterestHistoryRepository() {
        latelyInterestHistoryRemoteDataSource = LatelyLatelyInterestHistoryRemoteDataSource.getInstance();
    }


    @Override
    public void callReadInterest(String identity, InterestHistoryApiListener listener) {
        latelyInterestHistoryRemoteDataSource.callReadInterest(identity, listener);
    }
}
