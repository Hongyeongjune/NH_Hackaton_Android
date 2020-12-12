package com.nhhackaton.view.invest.presenter;

import com.nhhackaton.data.Invest.Invest;
import com.nhhackaton.data.Invest.source.InvestRepository;
import com.nhhackaton.data.Invest.source.InvestSource;
import com.nhhackaton.util.ToastUtils;

public class InvestPresenter implements InvestContract.Presenter {

    private InvestContract.View view;

    private final InvestRepository investRepository;

    public InvestPresenter(InvestContract.View view, InvestRepository investRepository) {
        this.view = view;
        this.investRepository = investRepository;
    }

    @Override
    public void callMoneyInvest(String money) {
        Invest invest = Invest.builder()
                .money(money)
                .build();

        if(!invest.getMoney().isEmpty()) {
            investRepository.callApplyInvest(invest, new InvestSource.InvestApiListener() {
                @Override
                public void onSuccess() {
                    view.startHomeFragment();
                }

                @Override
                public void onFail(String message) {
                    view.showErrorMessage(message);
                }
            });
        }

        view.showErrorMessage("내용을 입력하세요");
    }
}
