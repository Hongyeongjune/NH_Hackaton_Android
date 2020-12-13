package com.nhhackaton.view.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhhackaton.R;
import com.nhhackaton.data.LatelyHistory.LatelyHistory;
import com.nhhackaton.listener.OnBasicItemClickListener;
import com.nhhackaton.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LatelyHistoryAdapter extends RecyclerView.Adapter<LatelyHistoryViewHolder> implements LatelyHistoryAdapterContract.View, LatelyHistoryAdapterContract.Model {

    private Context context;
    private List<LatelyHistory> latelyHistories = new ArrayList<>();
    private OnBasicItemClickListener onBasicItemClickListener;
    private LinearLayoutManager linearLayoutManager;

    private boolean isMoreLoading = false;
    private boolean isLast = false;

    public LatelyHistoryAdapter(Context context) {
        this.context = context;
    }

    public void setOnBasicItemClickListener(OnBasicItemClickListener onBasicItemClickListener) {
        this.onBasicItemClickListener = onBasicItemClickListener;
    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }
    @NonNull
    @Override
    public LatelyHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lately_history, parent, false);
        return new LatelyHistoryViewHolder(view, onBasicItemClickListener, context);
    }

    @Override
    public void onBindViewHolder(@NonNull LatelyHistoryViewHolder holder, int position) {
        if (holder == null) return;
        holder.onBind(latelyHistories.get(position), position);
    }

    @Override
    public int getItemCount() {
        return latelyHistories != null ? latelyHistories.size() : 0;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public LatelyHistory getItem(int position) {
        return this.latelyHistories.get(position);
    }

    @Override
    public void addItems(List<LatelyHistory> latelyHistories) {
        this.latelyHistories.addAll(latelyHistories);
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
        Collections.sort(this.latelyHistories, new Comparator<LatelyHistory>() {
            @Override
            public int compare(LatelyHistory o1, LatelyHistory o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
    }

    @Override
    public void clearItems() {
        this.latelyHistories.clear();
    }

    @Override
    public int getCount() {
        return this.latelyHistories.size();
    }
}
