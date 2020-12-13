package com.nhhackaton.view.investhistory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhhackaton.R;
import com.nhhackaton.data.InvestHistory.InvestHistory;
import com.nhhackaton.listener.OnBasicItemClickListener;
import com.nhhackaton.listener.OnLoadMoreListener;
import com.nhhackaton.util.LogUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InvestHistoryAdapter extends RecyclerView.Adapter<InvestHistoryViewHolder> implements InvestHistoryAdapterContract.Model, InvestHistoryAdapterContract.View{

    private Context context;
    private List<InvestHistory> investHistories = new ArrayList<>();
    private OnBasicItemClickListener onBasicItemClickListener;
    private OnLoadMoreListener onLoadMoreListener;
    private LinearLayoutManager linearLayoutManager;

    private boolean isMoreLoading = false;
    private boolean isLast = false;

    public void setOnBasicItemClickListener(OnBasicItemClickListener onBasicItemClickListener) {
        this.onBasicItemClickListener = onBasicItemClickListener;
    }

    public InvestHistoryAdapter(Context context, OnLoadMoreListener onLoadMoreListener) {
        this.context = context;
        this.onLoadMoreListener = onLoadMoreListener;
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

    @Override
    public void addItems(List<InvestHistory> investHistories) {
        this.investHistories.addAll(investHistories);
    }

    @Override
    public InvestHistory getItem(int position) {
        return investHistories.get(position);
    }

    @Override
    public int getItemCount() {
        return investHistories != null ? investHistories.size() : 0;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InvestHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invest_history, parent, false);
        return new InvestHistoryViewHolder(view, onBasicItemClickListener, context);
    }

    @Override
    public void onBindViewHolder(@NonNull InvestHistoryViewHolder holder, int position) {
        if(holder == null) return;
        holder.onBind(investHistories.get(position), position);
    }

    @Override
    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    @Override
    public void setMoreLoading(boolean isMoreLoading) {
        this.isMoreLoading = isMoreLoading;
    }

    @Override
    public void sortItems() {
        Collections.sort(this.investHistories, new Comparator<InvestHistory>() {
            @Override
            public int compare(InvestHistory o1, InvestHistory o2) {
                return o2.getDate().compareTo(o1.getMoney());
            }
        });
    }
}
