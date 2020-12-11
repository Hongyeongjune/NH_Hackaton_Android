package com.nhhackaton.view.investhistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.nhhackaton.R;
import com.nhhackaton.data.InvestHistory.InvestHistory;
import com.nhhackaton.listener.OnLoadMoreListener;
import com.nhhackaton.util.LogUtils;
import com.nhhackaton.view.investhistory.adapter.InvestHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class InvestHistoryActivity extends AppCompatActivity implements OnLoadMoreListener {

    private Context context;
    private RecyclerView recyclerView;
    private InvestHistoryAdapter investHistoryAdapter;
    private List<InvestHistory> investHistories = new ArrayList<>();

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

    }

    @Override
    public void onLoadMore() {
    }
}
