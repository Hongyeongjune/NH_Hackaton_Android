package com.nhhackaton.view.loanhistory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhhackaton.R;
import com.nhhackaton.data.LoanHistory.LoanHistory;
import com.nhhackaton.listener.OnBasicItemClickListener;
import com.nhhackaton.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LoanHistoryAdapter extends RecyclerView.Adapter<LoanHistoryViewHolder> implements LoanHistoryAdapterContract.View, LoanHistoryAdapterContract.Model {

    private Context context;
    private List<LoanHistory> loanHistories = new ArrayList<>();
    private OnBasicItemClickListener onBasicItemClickListener;
    private OnLoadMoreListener onLoadMoreListener;
    private LinearLayoutManager linearLayoutManager;

    private boolean isMoreLoading = false;
    private boolean isLast = false;

    public LoanHistoryAdapter(Context context, OnLoadMoreListener onLoadMoreListener) {
        this.context = context;
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setOnBasicItemClickListener(OnBasicItemClickListener onBasicItemClickListener) {
        this.onBasicItemClickListener = onBasicItemClickListener;
    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    private boolean isVisibleLastItem(RecyclerView recyclerView) {
        return (!isLast && !isMoreLoading && (
                linearLayoutManager.getItemCount() - recyclerView.getChildCount())
                <= (linearLayoutManager.findFirstVisibleItemPosition() + 1));
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView rv, int dx, int dy) {
                super.onScrolled(rv, dx, dy);

                if (isVisibleLastItem(rv)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isMoreLoading = true;
                }
            }
        });
    }

    @NonNull
    @Override
    public LoanHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loan_history, parent, false);
        return new LoanHistoryViewHolder(view, onBasicItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanHistoryViewHolder holder, int position) {
        if (holder == null) return;
        holder.onBind(loanHistories.get(position), position);
    }

    @Override
    public int getItemCount() {
        return loanHistories != null ? loanHistories.size() : 0;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public LoanHistory getItem(int position) {
        return loanHistories.get(position);
    }

    @Override
    public void addItems(List<LoanHistory> loanHistories) {
        this.loanHistories.addAll(loanHistories);
    }

    @Override
    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    @Override
    public void setMoreLoading(boolean isMoreLoading) {
        this.isMoreLoading = isMoreLoading;
    }
}
