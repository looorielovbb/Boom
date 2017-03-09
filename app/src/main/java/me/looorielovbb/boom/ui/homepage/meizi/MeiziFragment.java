package me.looorielovbb.boom.ui.homepage.meizi;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.data.bean.Meizi;
import me.looorielovbb.boom.data.source.DataRepository;
import me.looorielovbb.boom.utils.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeiziFragment extends Fragment implements MeiziContract.View {


    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.refreshLayout) SwipeRefreshLayout refreshLayout;

    MeiziContract.Presenter mPresenter;
    Dialog dialog;

    public MeiziFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new MeiziFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meizi, container, false);
        ButterKnife.bind(this, view);
        setPresenter(new MeiziPresenter(DataRepository.getInstance(), this));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(MeiziContract.Presenter presenter) {
        if (presenter != null) {
            this.mPresenter = presenter;
        } else {
            throw new NullPointerException("presenter can not be null");
        }
    }

    @Override
    public void showloading() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void dismissLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showerror(String error) {
        ToastUtils.show(error);
    }

    @Override
    public void showList(List<Meizi> list) {

    }
}
