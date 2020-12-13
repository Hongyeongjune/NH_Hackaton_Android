package com.nhhackaton.view.account.presenter;


import com.nhhackaton.data.Account.Account;
import com.nhhackaton.data.Account.source.AccountRepository;
import com.nhhackaton.data.Account.source.AccountSource;

public class AccountPresenter implements AccountContract.Presenter {

    private AccountContract.View view;
    private final AccountRepository accountRepository;

    public AccountPresenter(AccountContract.View view, AccountRepository accountRepository) {
        this.view = view;
        this.accountRepository = accountRepository;
    }

    @Override
    public void callSetAccount(String identity, String acno, String bncd) {

        Account account = Account.builder()
                .acno(acno)
                .bncd(bncd)
                .build();

        if(!acno.isEmpty() && !bncd.isEmpty()) {
            accountRepository.callSetAccount(identity, account, new AccountSource.AccountApiListener() {
                @Override
                public void onSuccess(String message) {
                    view.startSignInActivity();
                }

                @Override
                public void onFail(String message) {
                    view.showErrorMessage(message);
                }
            });
        }

    }
}
