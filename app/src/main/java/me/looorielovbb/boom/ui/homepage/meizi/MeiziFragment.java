package me.looorielovbb.boom.ui.homepage.meizi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
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
import me.looorielovbb.boom.ui.adapter.MeiziAdapter;
import me.looorielovbb.boom.ui.uitools.loadmore.OnVerticalScrollListener;
import me.looorielovbb.boom.ui.uitools.loadmore.SupportLoadMoreLinearLayoutManager;
import me.looorielovbb.boom.utils.ToastUtils;
import me.solidev.statusviewlayout.StatusViewLayout;


public class MeiziFragment extends Fragment
        implements MeiziContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.stateview) StatusViewLayout mStatusView;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.refreshLayout) SwipeRefreshLayout refreshLayout;

    MeiziContract.Presenter mPresenter;
    private int mCurrentPage;
    MeiziAdapter meiziAdapter;

    public MeiziFragment() {
    }

    public static Fragment newInstance() {
        return new MeiziFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        setPresenter(new MeiziPresenter(DataRepository.getInstance(), this));
        refreshLayout.setOnRefreshListener(this);
        init();
        return view;
    }

    private void init() {
        mStatusView.setOnRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onRefresh();
            }
        });
        meiziAdapter = new MeiziAdapter();
        SupportLoadMoreLinearLayoutManager layout = new SupportLoadMoreLinearLayoutManager
                (getContext(),
                  LinearLayoutManager.VERTICAL,
                 false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(meiziAdapter);
        recyclerView.addOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolledDownToLastItem() {
                super.onScrolledDownToLastItem();
                mCurrentPage++;
                mPresenter.loaddata(mCurrentPage);
            }
        });
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
        meiziAdapter.setList(list);
        meiziAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadComplete() {
        meiziAdapter.updateLoadingStatus(true);
    }

    @Override
    public void onRefresh() {
        mPresenter.clearListData();
        mCurrentPage = 1;
        mPresenter.loaddata(mCurrentPage);
    }

}
