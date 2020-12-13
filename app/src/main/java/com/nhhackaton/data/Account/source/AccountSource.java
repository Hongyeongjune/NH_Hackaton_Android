package com.nhhackaton.data.Account.source;

import com.nhhackaton.data.Account.Account;
import com.nhhackaton.data.DepositMoney.source.DepositMoneySource;

public interface AccountSource {

    interface AccountApiListener {
        void onSuccess(String message);
        void onFail(String message);
    }

    void callSetAccount(String identity, Account account, AccountSource.AccountApiListener listener);
    
}
