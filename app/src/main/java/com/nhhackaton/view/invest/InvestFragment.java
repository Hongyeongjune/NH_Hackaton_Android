package com.nhhackaton.view.invest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nhhackaton.R;
import com.nhhackaton.util.ToastUtils;
import com.nhhackaton.view.home.HomeFragment;
import com.nhhackaton.view.invest.presenter.InvestContract;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class InvestFragment extends Fragment implements InvestContract.View {

    private Context context;
    private EditText etMoney;
    private Button btnInvest;
    private Button btnAccountUpdate;

    public static InvestFragment createFragment() {
        InvestFragment fragment = new InvestFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_invest, container, false);
        init(view);

        return view;
    }

    private void init(View view) {

        context = view.getContext();

        etMoney = (EditText) view.findViewById(R.id.et_invest_money);
        btnInvest = (Button) view.findViewById(R.id.btn_invest);
        btnAccountUpdate = (Button) view.findViewById(R.id.btn_invest_account_create);

    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startAccountCreateActivity() {

    }

    @Override
    public void startHomeFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(
                InvestFragment.this).commit();
        fragmentManager.popBackStack();
    }


}
