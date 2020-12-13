package com.nhhackaton.view.loanhistory.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nhhackaton.R;
import com.nhhackaton.data.LoanHistory.LoanHistory;
import com.nhhackaton.listener.OnBasicItemClickListener;
import com.nhhackaton.util.ToastUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LoanHistoryViewHolder extends RecyclerView.ViewHolder{

    private Context context;
    private TextView tvLoanHistoryNo;
    private TextView tvLoanHistoryType;
    private TextView tvLoanHistoryMoney;
    private TextView tvLoanHistoryInformation;
    private OnBasicItemClickListener onBasicItemClickListener;
    
    public LoanHistoryViewHolder(@NonNull View itemView, OnBasicItemClickListener onBasicItemClickListener, Context context) {
        super(itemView);
        this.context = context;
        this.tvLoanHistoryNo = (TextView) itemView.findViewById(R.id.tv_item_loan_history_no);
        this.tvLoanHistoryType = (TextView) itemView.findViewById(R.id.tv_item_loan_history_type);
        this.tvLoanHistoryMoney = (TextView) itemView.findViewById(R.id.tv_item_loan_history_money);
        this.tvLoanHistoryInformation = (TextView) itemView.findViewById(R.id.tv_item_loan_history_information);
        this.onBasicItemClickListener = onBasicItemClickListener;


    }
    
    public void onBind(LoanHistory loanHistory, int position) {
        tvLoanHistoryNo.setText(loanHistory.getCount());
        tvLoanHistoryType.setText(loanHistory.getType());
        tvLoanHistoryMoney.setText(loanHistory.getMoney());
        tvLoanHistoryInformation.setText(loanHistory.getDate());

        itemView.setOnClickListener(v -> {
            onBasicItemClickListener.onStartItemClick(position);
        });
    }

}
