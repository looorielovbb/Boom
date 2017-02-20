package me.looorielovbb.boom.ui.homepage;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.ui.adapter.MainAdapter;
import me.looorielovbb.boom.utils.ToastUtils;


public class MainFragment extends Fragment implements MainContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView rvMain;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    MainContract.Presenter presenter;
    private MainAdapter adapter;

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
        adapter = new MainAdapter();
        List<Object> list = new LinkedList<>();
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        adapter.setList(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvMain.setLayoutManager(layoutManager);
        rvMain.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        rvMain.setAdapter(adapter);
    }

    @Override
    public void showloading() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void showerror() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastUtils.show("刷新完成");
                refreshLayout.setRefreshing(false);
            }
        }, 1000);
    }
}
