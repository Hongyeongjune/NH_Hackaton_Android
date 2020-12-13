package com.nhhackaton.view.investhistory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhhackaton.R;
import com.nhhackaton.data.InvestHistory.Interest.InterestHistory;
import com.nhhackaton.data.InvestHistory.Interest.source.InterestHistoryRepository;
import com.nhhackaton.data.InvestHistory.InvestDeposit.source.InvestDepositRepository;
import com.nhhackaton.data.InvestHistory.InvestFinish.source.InvestFinishRepository;
import com.nhhackaton.data.InvestHistory.InvestHistory;
import com.nhhackaton.data.InvestHistory.source.InvestHistoryRepository;
import com.nhhackaton.listener.OnBasicItemClickListener;
import com.nhhackaton.listener.OnLoadMoreListener;
import com.nhhackaton.util.SharedPreferencesUtils;
import com.nhhackaton.util.ToastUtils;
import com.nhhackaton.view.investhistory.adapter.InvestHistoryAdapter;
import com.nhhackaton.view.investhistory.presenter.InvestHistoryContract;
import com.nhhackaton.view.investhistory.presenter.InvestHistoryPresenter;

public class InvestHistoryFragment extends Fragment implements OnLoadMoreListener, InvestHistoryContract.View {

    private Context context;
    private RecyclerView recyclerView;
    private InvestHistoryAdapter investHistoryAdapter;

    private InvestHistoryContract.Presenter presenter;

    public static InvestHistoryFragment createFragment() {
        InvestHistoryFragment fragment = new InvestHistoryFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_invest_history, container, false);
        init(view);

        return view;
    }

    private void init(View view) {

        context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_invest_history);

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

        presenter = new InvestHistoryPresenter(this,
                InvestFinishRepository.getInstance(),
                InvestDepositRepository.getInstance(),
                InterestHistoryRepository.getInstance());

        presenter.setInvestHistoryAdapterModel(investHistoryAdapter);
        presenter.setInvestHistoryAdapterView(investHistoryAdapter);
        presenter.callReadInterest(SharedPreferencesUtils.readMemberFromEmail(context));
        presenter.callReadInvestDeposit(SharedPreferencesUtils.readMemberFromEmail(context));
        presenter.callReadInvestFinish(SharedPreferencesUtils.readMemberFromEmail(context));

        investHistoryAdapter.setOnBasicItemClickListener(new OnBasicItemClickListener() {
            @Override
            public void onStartItemClick(int position) {
                ToastUtils.showToast(context, "구현 중....");
            }
        });

    }

    @Override
    public void onLoadMore() {
//        new Handler().postDelayed(() -> {
//            presenter.callReadInvestHistory(calculatePageNo());
//        }, 500);
    }

    private int calculatePageNo() {
        final int pageSize = 24;
        int itemCount = investHistoryAdapter.getItemCount();
        return (itemCount == 0) ? 0 : (itemCount / pageSize) + 1;
    }


    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startInvestDetailActivity(Long boardId) {
        // 있어야하나?
    }
}
