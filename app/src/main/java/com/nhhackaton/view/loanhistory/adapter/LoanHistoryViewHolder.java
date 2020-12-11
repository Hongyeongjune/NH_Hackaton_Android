package com.nhhackaton.view.loanhistory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nhhackaton.R;
import com.nhhackaton.data.LoanHistory.LoanHistory;
import com.nhhackaton.listener.OnBasicItemClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LoanHistoryViewHolder extends RecyclerView.ViewHolder{

    private Context context;
    private TextView tvLoanHistoryNo;
    private TextView tvLoanHistoryType;
    private TextView tvLoanHistoryMoney;
    private TextView tvLoanHistoryInformation;
    private OnBasicItemClickListener onBasicItemClickListener;

    public LoanHistoryViewHolder(@NonNull View itemView, OnBasicItemClickListener onBasicItemClickListener) {
        super(itemView);
        this.tvLoanHistoryNo = (TextView) itemView.findViewById(R.id.tv_item_load_history_no);
        this.tvLoanHistoryType = (TextView) itemView.findViewById(R.id.tv_item_load_history_type);
        this.tvLoanHistoryMoney = (TextView) itemView.findViewById(R.id.tv_item_load_history_money);
        this.tvLoanHistoryInformation = (TextView) itemView.findViewById(R.id.tv_item_load_history_information);
        this.onBasicItemClickListener = onBasicItemClickListener;

    }


    public void onBind(LoanHistory loanHistory, int position) {

        tvLoanHistoryNo.setText(loanHistory.getId() + "");
        tvLoanHistoryType.setText(loanHistory.getType());
        tvLoanHistoryMoney.setText(loanHistory.getMoney());
        tvLoanHistoryInformation.setText(loanHistory.getInformation());

        itemView.setOnClickListener(v -> {
            onBasicItemClickListener.onStartItemClick(position);
        });

    }
}
