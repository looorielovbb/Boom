package me.looorielovbb.boom.ui.home.movieandbooks.top250;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.adapter.MovieTop250Adapter;
import me.looorielovbb.boom.base.LazyLoadFragment;
import me.looorielovbb.boom.config.Constants;
import me.looorielovbb.boom.data.bean.douban.MovieInfo;
import me.looorielovbb.boom.ui.widgets.loadmore.OnVerticalScrollListener;
import me.looorielovbb.boom.ui.widgets.loadmore.SupportLoadMoreLinearLayoutManager;
import me.looorielovbb.boom.utils.ToastUtils;
import me.solidev.statusviewlayout.StatusViewLayout;

/**
 * Created by Lulei on 2017/7/3.
 */

public class Top250Fragment extends LazyLoadFragment implements Top250Contract.View, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.stateview)
    StatusViewLayout stateview;

    Unbinder unbinder;
    MovieTop250Adapter adapter;
    private Top250Contract.Presenter mPresenter;
    private int mCurrentPage;

    public static Top250Fragment newInstance() {
        Bundle args = new Bundle();
        Top250Fragment fragment = new Top250Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, v);
        setPresenter(new Top250Presenter(this));
        initView();
        return v;
    }

    private void initView() {
        refreshLayout.setOnRefreshListener(this);
        stateview.setOnRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        adapter = new MovieTop250Adapter(getActivity());
        //设置图片
        SupportLoadMoreLinearLayoutManager layout = new SupportLoadMoreLinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolledDownToLastItem() {
                super.onScrolledDownToLastItem();
                if (mCurrentPage == 1) {
                    mCurrentPage++;
                    adapter.updateLoadingStatus(false);
                    mPresenter.loaddata(mCurrentPage);
                    //Count 为item数量加上Bottom Item 若为其他值 最后一页没有加载10条
                } else if (adapter.getItemCount() % Constants.PAGE_COUNT == 1) {
                    mCurrentPage++;
                    adapter.updateLoadingStatus(false);
                    mPresenter.loaddata(mCurrentPage);
                } else {
                    adapter.updateLoadingStatus(true);
                }
            }
        });
    }

    @Override
    public void requestData() {
        mCurrentPage = 1;
        mPresenter.loaddata(mCurrentPage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public void showList(List<MovieInfo> list) {
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadComplete() {
        adapter.updateLoadingStatus(true);
    }

    @Override
    public void setPresenter(Top250Contract.Presenter presenter) {
        if (presenter != null) {
            this.mPresenter = presenter;
        } else {
            throw new NullPointerException("presenter can not be null");
        }
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
    public void onRefresh() {
        mPresenter.clear();
        mCurrentPage = 1;
        mPresenter.loaddata(mCurrentPage);
    }
}
