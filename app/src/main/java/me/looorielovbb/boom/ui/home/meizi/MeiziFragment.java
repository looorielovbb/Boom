package me.looorielovbb.boom.ui.home.meizi;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.adapter.MeiziAdapter;
import me.looorielovbb.boom.base.LazyLoadFragment;
import me.looorielovbb.boom.config.Constants;
import me.looorielovbb.boom.data.bean.gank.Girl;
import me.looorielovbb.boom.ui.widgets.loadmore.OnVerticalScrollListener;
import me.looorielovbb.boom.ui.widgets.loadmore.SupportLoadMoreLinearLayoutManager;
import me.looorielovbb.boom.utils.ToastUtils;
import me.looorielovbb.boom.widget.StatusViewLayout;


public class MeiziFragment extends LazyLoadFragment
        implements MeiziContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.stateview)
    StatusViewLayout mStatusView;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.refreshLayout) SwipeRefreshLayout refreshLayout;

    MeiziContract.Presenter mPresenter;
    MeiziAdapter girlAdapter;
    private int mCurrentPage;

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
        setPresenter(new GirlPresenter(this));
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
        girlAdapter = new MeiziAdapter(getActivity());
        //设置图片
        SupportLoadMoreLinearLayoutManager layout = new SupportLoadMoreLinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(girlAdapter);
        recyclerView.addOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolledDownToLastItem() {
                super.onScrolledDownToLastItem();
                if (mCurrentPage == 1) {
                    mCurrentPage++;
                    girlAdapter.updateLoadingStatus(false);
                    mPresenter.loaddata(mCurrentPage);
                    //Count 为item数量加上Bottom Item 若为其他值 最后一页没有加载10条
                } else if (girlAdapter.getItemCount() % Constants.PAGE_COUNT == 1) {
                    mCurrentPage++;
                    girlAdapter.updateLoadingStatus(false);
                    mPresenter.loaddata(mCurrentPage);
                } else {
                    girlAdapter.updateLoadingStatus(true);
                }
            }
        });
    }


    @Override
    public void onRefresh() {
        mPresenter.clearListData();
        mCurrentPage = 1;
        mPresenter.loaddata(mCurrentPage);
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
    public void showList(List<Girl> list) {
        girlAdapter.setList(list);
        girlAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadComplete() {
        girlAdapter.updateLoadingStatus(true);
    }

    @Override
    public void requestData() {
        mCurrentPage = 1;
        mPresenter.loaddata(mCurrentPage);
    }
}
