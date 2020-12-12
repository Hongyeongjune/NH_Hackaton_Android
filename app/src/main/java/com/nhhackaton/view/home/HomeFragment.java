package com.nhhackaton.view.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nhhackaton.R;
import com.nhhackaton.data.DepositMoney.source.DepositMoneyRepository;
import com.nhhackaton.data.LatelyHistory.source.LatelyHistoryRepository;
import com.nhhackaton.listener.OnBasicItemClickListener;
import com.nhhackaton.listener.OnLoadMoreListener;
import com.nhhackaton.util.SharedPreferencesUtils;
import com.nhhackaton.util.ToastUtils;
import com.nhhackaton.view.home.adapter.LatelyHistoryAdapter;
import com.nhhackaton.view.home.presenter.HomeContract;
import com.nhhackaton.view.home.presenter.HomePresenter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment implements HomeContract.View {

    private Context context;
    private TextView tvDepositMoney;
    private Button btnInvest;
    private Button btnInvestHistory;
    private Button btnLoan;
    private Button btnLoanHistory;

    private RecyclerView recyclerView;
    private LatelyHistoryAdapter latelyHistoryAdapter;
    private HomeContract.Presenter presenter;

    public static HomeFragment createFragment() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        context = view.getContext();

        tvDepositMoney = (TextView) view.findViewById(R.id.tv_home_deposit_money);
        btnInvest = (Button) view.findViewById(R.id.btn_home_invest);
        btnInvestHistory = (Button) view.findViewById(R.id.btn_home_invest_history);
        btnLoan = (Button) view.findViewById(R.id.btn_home_loan);
        btnLoanHistory = (Button) view.findViewById(R.id.btn_home_loan_history);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_home_lately_history);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        latelyHistoryAdapter = new LatelyHistoryAdapter(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        context, linearLayoutManager.getOrientation()
                )
        );

        latelyHistoryAdapter.setLinearLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(latelyHistoryAdapter);

        presenter = new HomePresenter(this, DepositMoneyRepository.getInstance(),  LatelyHistoryRepository.getInstance());
        presenter.setLatelyHistoryAdapterModel(latelyHistoryAdapter);
        presenter.setLatelyHistoryAdapterView(latelyHistoryAdapter);
        presenter.callReadLatelyHistory();
        presenter.callReadDepositMoney(SharedPreferencesUtils.readMemberFromEmail(context));

    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startLatelyDetailActivity(Long LatelyId) {
        // 어떻게 할지
    }

    @Override
    public void setVirtualAccount(String message) {
        tvDepositMoney.setText(message + "원");
    }
}
