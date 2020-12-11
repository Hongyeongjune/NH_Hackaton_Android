package com.nhhackaton.view.investhistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.nhhackaton.R;
import com.nhhackaton.data.InvestHistory.InvestHistory;
import com.nhhackaton.data.InvestHistory.source.InvestHistoryRepository;
import com.nhhackaton.listener.OnLoadMoreListener;
import com.nhhackaton.util.LogUtils;
import com.nhhackaton.util.ToastUtils;
import com.nhhackaton.view.investhistory.adapter.InvestHistoryAdapter;
import com.nhhackaton.view.investhistory.presenter.InvestHistoryContract;
import com.nhhackaton.view.investhistory.presenter.InvestHistoryPresenter;

import java.util.ArrayList;
import java.util.List;

public class InvestHistoryActivity extends AppCompatActivity implements OnLoadMoreListener, InvestHistoryContract.View {

    private Context context;
    private RecyclerView recyclerView;
    private InvestHistoryAdapter investHistoryAdapter;
    private List<InvestHistory> investHistories = new ArrayList<>();

    private InvestHistoryContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_history);

        init();
    }

    private void init() {

        context = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.rv_invest_history);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        investHistoryAdapter = new InvestHistoryAdapter(context, this);
        recyclerView.setAdapter(investHistoryAdapter);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        context, linearLayoutManager.getOrientation()
                )
        );

        investHistoryAdapter.setRecyclerView(recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        investHistoryAdapter.setLinearLayoutManager(linearLayoutManager);
        investHistoryAdapter.addItems(investHistories);
        investHistoryAdapter.notifyAdapter();

        presenter = new InvestHistoryPresenter(this, InvestHistoryRepository.getInstance());
        presenter.setInvestHistoryAdapterModel(investHistoryAdapter);
        presenter.setInvestHistoryAdapterView(investHistoryAdapter);
        presenter.callReadInvestHistory(1);

    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(() -> {
            presenter.callReadInvestHistory(calculatePageNo());
        }, 500);
    }

    private int calculatePageNo() {
        final int pageSize = 24;
        int itemCount = investHistoryAdapter.getItemCount();
        return (itemCount == 0) ? 0 : (itemCount / pageSize) + 1;
    }


    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startInvestDetailActivity(Long boardId) {
        // 있어야하나?
    }
}
