package me.looorielovbb.boom.ui.homepage.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.MultiTypeAdapter;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.data.bean.zhihu.DailyListBean;
import me.looorielovbb.boom.multitype.BannerViewBinder;


public class MainFragment extends Fragment implements  SwipeRefreshLayout.OnRefreshListener,
        MainContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView rvMain;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private MultiTypeAdapter adapter;
    MainContract.Presenter mPresenter;


    public MainFragment() {

    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        refreshLayout.setOnRefreshListener(this);
        adapter = new MultiTypeAdapter();
        adapter.register(DailyListBean.class,new BannerViewBinder());
        rvMain.setAdapter(adapter);
    }



    @Override
    public void onRefresh() {
        mPresenter.loadLatestData();
    }

    @Override
    public void showloading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showerror(String error) {

    }

    @Override
    public void showList(DailyListBean dailyListBean) {

    }

    @Override
    public void loadComplete() {

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        if (presenter != null) {
            this.mPresenter = presenter;
        } else {
            throw new NullPointerException("presenter can not be null");
        }
    }
}
