package com.nhhackaton.view.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nhhackaton.R;
import com.nhhackaton.data.LatelyHistory.LatelyHistory;
import com.nhhackaton.listener.OnBasicItemClickListener;
import com.nhhackaton.util.ToastUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LatelyHistoryViewHolder extends RecyclerView.ViewHolder{

    private Context context;
    private TextView tvLatelyHistoryNo;
    private TextView tvLatelyHistoryType;
    private TextView tvLatelyHistoryMoney;
    private TextView tvLatelyHistoryInformation;
    private OnBasicItemClickListener onBasicItemClickListener;
    
    public LatelyHistoryViewHolder(@NonNull View itemView, OnBasicItemClickListener onBasicItemClickListener, Context context) {
        super(itemView);
        this.context = context;
        this.tvLatelyHistoryNo = (TextView) itemView.findViewById(R.id.tv_item_lately_history_no);
        this.tvLatelyHistoryType = (TextView) itemView.findViewById(R.id.tv_item_lately_history_type);
        this.tvLatelyHistoryMoney = (TextView) itemView.findViewById(R.id.tv_item_lately_history_money);
        this.tvLatelyHistoryInformation = (TextView) itemView.findViewById(R.id.tv_item_lately_history_information);
        this.onBasicItemClickListener = onBasicItemClickListener;

    }

    public void onBind(LatelyHistory loanHistory, int position) {
        tvLatelyHistoryNo.setText(loanHistory.getId() + "");
        tvLatelyHistoryType.setText(loanHistory.getType());
        tvLatelyHistoryMoney.setText(loanHistory.getMoney());
        tvLatelyHistoryInformation.setText(loanHistory.getInformation());

        itemView.setOnClickListener(v -> {
            onBasicItemClickListener.onStartItemClick(position);
        });

    }

}
