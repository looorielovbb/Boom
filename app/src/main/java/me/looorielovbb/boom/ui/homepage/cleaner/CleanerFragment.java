package me.looorielovbb.boom.ui.homepage.cleaner;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.adapter.MeiziAdapter;
import me.looorielovbb.boom.data.bean.gank.Meizi;
import me.looorielovbb.boom.ui.uitools.loadmore.OnVerticalScrollListener;
import me.looorielovbb.boom.ui.uitools.loadmore.SupportLoadMoreLinearLayoutManager;
import me.looorielovbb.boom.utils.ToastUtils;
import me.solidev.statusviewlayout.StatusViewLayout;


public class CleanerFragment extends Fragment
        implements CleanerContract.View, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.refreshLayout) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.stateview) StatusViewLayout stateview;
    Unbinder unbinder;
    MeiziAdapter adapter;
    private int mCurrentPage = 1;
    private CleanerContract.Presenter mPresenter;

    public CleanerFragment() {

    }

    public static Fragment newInstance() {
        return new CleanerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        setPresenter(new CleanerPresenter(this));
        refreshLayout.setOnRefreshListener(this);
        stateview.setOnRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        initRecyclerView();
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initRecyclerView() {
        adapter = new MeiziAdapter(getActivity());
        SupportLoadMoreLinearLayoutManager layout = new SupportLoadMoreLinearLayoutManager(
                getContext(),
                OrientationHelper.VERTICAL,
                false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolledDownToLastItem() {
                super.onScrolledDownToLastItem();
                if (adapter.isEnableLoadMore()) {
                    mCurrentPage++;
                    mPresenter.loaddata(mCurrentPage);
                }
            }
        });
    }

    @Override
    public void setPresenter(CleanerContract.Presenter presenter) {
        if (presenter != null) {
            this.mPresenter = presenter;
        } else {
            throw new NullPointerException("presenter can not be null");
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.clearListData();
        adapter.updateLoadingStatus(false);
        mCurrentPage = 1;
        mPresenter.loaddata(mCurrentPage);
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
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadComplete() {
        adapter.updateLoadingStatus(true);
    }
}
