package com.nhhackaton.data.SignUp.duplicate.source;


public interface DuplicateSource {

    interface DuplicateApiListener {
        void onSuccess(boolean isDuplicate);
        void onFail(String message);
    }

    void callDuplicate(String identity, DuplicateSource.DuplicateApiListener listener);

}
