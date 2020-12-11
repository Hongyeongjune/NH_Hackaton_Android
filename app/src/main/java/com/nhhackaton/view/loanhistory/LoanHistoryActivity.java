package com.nhhackaton.view.loanhistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.nhhackaton.R;
import com.nhhackaton.data.LoanHistory.LoanHistory;
import com.nhhackaton.listener.OnLoadMoreListener;
import com.nhhackaton.view.loanhistory.adapter.LoanHistoryAdapter;
import com.nhhackaton.view.loanhistory.presenter.LoanHistoryContract;

import java.util.ArrayList;
import java.util.List;

public class LoanHistoryActivity extends AppCompatActivity implements LoanHistoryContract.View, OnLoadMoreListener {

    private Context context;
    private RecyclerView recyclerView;
    private LoanHistoryAdapter loanHistoryAdapter;
    private List<LoanHistory> loanHistories = new ArrayList<>();

    private LoanHistoryContract.Presenter presenter;

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
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        context, linearLayoutManager.getOrientation()
                )
        );
        loanHistoryAdapter.setLinearLayoutManager(linearLayoutManager);
        loanHistoryAdapter.setRecyclerView(recyclerView);
        recyclerView.setAdapter(loanHistoryAdapter);



    }

    @Override
    public void onLoadMore() {

    }
}
