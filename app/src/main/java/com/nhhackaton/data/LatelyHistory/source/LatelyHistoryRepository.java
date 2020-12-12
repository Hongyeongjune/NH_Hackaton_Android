package com.nhhackaton.data.LatelyHistory.source;

import com.nhhackaton.data.LatelyHistory.LatelyHistory;
import com.nhhackaton.data.LatelyHistory.source.LatelyHistoryRemoteDataSource;
import com.nhhackaton.data.LatelyHistory.source.LatelyHistoryRepository;

public class LatelyHistoryRepository implements LatelyHistorySource {

    private static LatelyHistoryRepository latelyHistoryRepository;
    private LatelyHistoryRemoteDataSource latelyHistoryRemoteDataSource;

    public static LatelyHistoryRepository getInstance() {
        if(latelyHistoryRepository == null) {
            latelyHistoryRepository = new LatelyHistoryRepository();
        }

        return latelyHistoryRepository;
    }

    private LatelyHistoryRepository(){
        latelyHistoryRemoteDataSource = LatelyHistoryRemoteDataSource.getInstance();
    }
    
    @Override
    public void callReadLatelyHistory(LatelyHistoryApiListener listener) {
        latelyHistoryRemoteDataSource.callReadLatelyHistory(listener);
    }
}
