package com.nhhackaton.data.Account.source;

import com.nhhackaton.data.Account.Account;
import com.nhhackaton.data.Account.source.AccountRemoteDataSource;

public class AccountRepository implements AccountSource {

    private static AccountRepository accountRepository;
    private AccountRemoteDataSource accountRemoteDataSource;

    public static AccountRepository getInstance() {

        if(accountRepository == null) {
            accountRepository = new AccountRepository();
        }

        return accountRepository;

    }

    private AccountRepository() {

        accountRemoteDataSource = AccountRemoteDataSource.getInstance();
    }

    
    @Override
    public void callSetAccount(String identity, Account account, AccountApiListener listener) {
        accountRemoteDataSource.callSetAccount(identity, account, listener);
    }
}
