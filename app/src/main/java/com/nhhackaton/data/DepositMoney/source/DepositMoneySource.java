package com.nhhackaton.data.DepositMoney.source;

public interface DepositMoneySource {

    interface VirtualAccountApiListener {
        void onSuccess(String message);
        void onFail(String message);
    }

    void callReadDepositMoney(String message, VirtualAccountApiListener listener);
}
