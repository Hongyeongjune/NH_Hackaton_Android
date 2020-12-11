package com.nhhackaton.view.loanhistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.nhhackaton.R;
import com.nhhackaton.data.LoanHistory.LoanHistory;
import com.nhhackaton.listener.OnLoadMoreListener;
import com.nhhackaton.util.LogUtils;
import com.nhhackaton.view.loanhistory.adapter.LoanHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class LoanHistoryActivity extends AppCompatActivity implements OnLoadMoreListener {

    private Context context;
    private RecyclerView recyclerView;
    private LoanHistoryAdapter loanHistoryAdapter;
    private List<LoanHistory> loanHistories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_history);

        init();
    }

    private void init() {

        context = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.rv_loan_history);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        loanHistoryAdapter = new LoanHistoryAdapter(context, this);
        recyclerView.setAdapter(loanHistoryAdapter);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        context, linearLayoutManager.getOrientation()
                )
        );


        for(int i=0; i<5; i++) {
            loanHistories.add(new LoanHistory(Long.parseLong(i+5+""),
                    "TestType" + i+5,
                    "TestMoney" + i+5,
                    "TestInfo" + i+5));
        }


        loanHistoryAdapter.setRecyclerView(recyclerView);

        recyclerView.setLayoutManager(linearLayoutManager);

        loanHistoryAdapter.setLinearLayoutManager(linearLayoutManager);

        loanHistoryAdapter.addItems(loanHistories);

        loanHistoryAdapter.notifyAdapter();


        LogUtils.logInfo(loanHistoryAdapter.getItemCount() + "");
        LogUtils.logInfo(loanHistoryAdapter.getItem(1).getMoney() + "");
        LogUtils.logInfo(loanHistoryAdapter.getItem(2).getMoney() + "");
    }

    @Override
    public void onLoadMore() {
    }
}
