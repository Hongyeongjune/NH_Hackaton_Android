package com.nhhackaton.data.DepositMoney.source;

import com.nhhackaton.data.DepositMoney.DepositMoney;

public interface DepositMoneySource {

    interface VirtualAccountApiListener {
        void onSuccess(DepositMoney depositMoney);
        void onFail(String message);
    }

    void callReadDepositMoney(String identity, VirtualAccountApiListener listener);
}
