package com.nhhackaton.data.LatelyHistory.source;

import com.nhhackaton.data.LatelyHistory.LatelyPagingResponse;

public interface LatelyHistorySource {

    interface LatelyHistoryApiListener {
        void onSuccess(LatelyPagingResponse latelyPagingResponse);
        void onFail(String message);
    }

    void callReadLatelyHistory(LatelyHistorySource.LatelyHistoryApiListener listener);

}
