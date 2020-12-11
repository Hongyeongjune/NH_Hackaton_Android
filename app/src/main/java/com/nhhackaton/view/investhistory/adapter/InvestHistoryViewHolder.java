package com.nhhackaton.view.investhistory.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nhhackaton.R;
import com.nhhackaton.data.InvestHistory.InvestHistory;
import com.nhhackaton.listener.OnBasicItemClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InvestHistoryViewHolder extends RecyclerView.ViewHolder{

    private Context context;
    private TextView tvInvestHistoryNo;
    private TextView tvInvestHistoryType;
    private TextView tvInvestHistoryMoney;
    private TextView tvInvestHistoryInformation;
    private OnBasicItemClickListener onBasicItemClickListener;

    public InvestHistoryViewHolder(@NonNull View itemView, OnBasicItemClickListener onBasicItemClickListener) {
        super(itemView);
        this.tvInvestHistoryNo = (TextView) itemView.findViewById(R.id.tv_item_invest_history_no);
        this.tvInvestHistoryType = (TextView) itemView.findViewById(R.id.tv_item_invest_history_type);
        this.tvInvestHistoryMoney = (TextView) itemView.findViewById(R.id.tv_item_invest_history_money);
        this.tvInvestHistoryInformation = (TextView) itemView.findViewById(R.id.tv_item_invest_history_information);
        this.onBasicItemClickListener = onBasicItemClickListener;

    }


    public void onBind(InvestHistory investHistory, int position) {

        tvInvestHistoryNo.setText(investHistory.getId() + "");
        tvInvestHistoryType.setText(investHistory.getType());
        tvInvestHistoryMoney.setText(investHistory.getMoney());
        tvInvestHistoryInformation.setText(investHistory.getInformation());

        itemView.setOnClickListener(v -> {
            onBasicItemClickListener.onStartItemClick(position);
        });

    }
}
