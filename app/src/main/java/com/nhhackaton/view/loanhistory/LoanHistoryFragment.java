package com.nhhackaton.view.loanhistory;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhhackaton.R;
import com.nhhackaton.data.LoanHistory.LoanHistory;
import com.nhhackaton.data.LoanHistory.LoanMoney.source.LoanMoneyRepository;
import com.nhhackaton.data.LoanHistory.repayment.source.RepaymentRepository;
import com.nhhackaton.data.LoanHistory.source.LoanHistoryRepository;
import com.nhhackaton.listener.OnLoadMoreListener;
import com.nhhackaton.util.SharedPreferencesUtils;
import com.nhhackaton.util.ToastUtils;
import com.nhhackaton.view.loanhistory.adapter.LoanHistoryAdapter;
import com.nhhackaton.view.loanhistory.presenter.LoanHistoryContract;
import com.nhhackaton.view.loanhistory.presenter.LoanHistoryPresenter;

import java.util.ArrayList;
import java.util.List;

public class LoanHistoryFragment extends Fragment implements LoanHistoryContract.View, OnLoadMoreListener {

    private Context context;
    private RecyclerView recyclerView;
    private LoanHistoryAdapter loanHistoryAdapter;
    private List<LoanHistory> loanHistories = new ArrayList<>();

    private LoanHistoryContract.Presenter presenter;

    public static LoanHistoryFragment createFragment() {
        LoanHistoryFragment fragment = new LoanHistoryFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_loan_history, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_loan_history);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        loanHistoryAdapter = new LoanHistoryAdapter(context, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        context, linearLayoutManager.getOrientation()
                )
        );
        loanHistoryAdapter.setLinearLayoutManager(linearLayoutManager);
        loanHistoryAdapter.setRecyclerView(recyclerView);
        recyclerView.setAdapter(loanHistoryAdapter);

        presenter = new LoanHistoryPresenter(this, LoanMoneyRepository.getInstance(), RepaymentRepository.getInstance());
        presenter.setLoanHistoryAdapterModel(loanHistoryAdapter);
        presenter.setLoanHistoryAdapterView(loanHistoryAdapter);

        presenter.callReadLoanMoney(SharedPreferencesUtils.readMemberFromEmail(context));
        presenter.callReadRepayment(SharedPreferencesUtils.readMemberFromEmail(context));
        loanHistoryAdapter.setOnBasicItemClickListener(v -> {
            ToastUtils.showToast(context, "구현 중...");
        });


    }

    @Override
    public void onLoadMore() {
//        new Handler().postDelayed(() -> {
//            presenter.callReadInvestHistory(calculatePageNo());
//        }, 500);
    }

    private int calculatePageNo() {
        final int pageSize = 24;
        int itemCount = loanHistoryAdapter.getItemCount();
        return (itemCount == 0) ? 0 : (itemCount / pageSize) + 1;
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startLoanDetailActivity(Long historyId) {
        // 있어야 하나?
    }
}
