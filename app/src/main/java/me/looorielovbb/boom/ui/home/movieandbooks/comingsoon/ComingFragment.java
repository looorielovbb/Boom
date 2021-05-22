package me.looorielovbb.boom.ui.home.movieandbooks.comingsoon;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.adapter.MovieComingAdapter;
import me.looorielovbb.boom.base.LazyLoadFragment;
import me.looorielovbb.boom.config.Constants;
import me.looorielovbb.boom.data.bean.douban.MovieInfo;
import me.looorielovbb.boom.ui.widgets.loadmore.OnVerticalScrollListener;
import me.looorielovbb.boom.ui.widgets.loadmore.SupportLoadMoreLinearLayoutManager;
import me.looorielovbb.boom.utils.ToastUtils;
import me.looorielovbb.boom.widget.StatusViewLayout;

/**
 * Created by Lulei on 2017/7/3.
 */

public class ComingFragment extends LazyLoadFragment implements ComingContract.View, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.stateview)
    StatusViewLayout stateview;

    Unbinder unbinder;
    MovieComingAdapter adapter;
    private ComingContract.Presenter mPresenter;
    private int mCurrentPage;

    public static ComingFragment newInstance() {
        Bundle args = new Bundle();
        ComingFragment fragment = new ComingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, v);
        setPresenter(new ComingPresenter(this));
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
        adapter = new MovieComingAdapter(getActivity());
        //设置图片
        SupportLoadMoreLinearLayoutManager layout = new SupportLoadMoreLinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false);
        recyclerView.setLayoutManager(layout);
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
    public void setPresenter(ComingContract.Presenter presenter) {
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        mPresenter.clear();
        mCurrentPage = 1;
        mPresenter.loaddata(mCurrentPage);
    }
}
