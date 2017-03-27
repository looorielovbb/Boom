package me.looorielovbb.boom.ui.homepage.meizi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nukc.stateview.StateView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.data.bean.Meizi;
import me.looorielovbb.boom.data.source.DataRepository;
import me.looorielovbb.boom.ui.adapter.LoadMoreAdapter;
import me.looorielovbb.boom.ui.adapter.MeiziAdapter;
import me.looorielovbb.boom.utils.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeiziFragment extends Fragment
        implements MeiziContract.View, SwipeRefreshLayout.OnRefreshListener,
        StateView.OnRetryClickListener {


    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.refreshLayout) SwipeRefreshLayout refreshLayout;

    MeiziContract.Presenter mPresenter;
    StateView mStateView;
    MeiziAdapter meiziAdapter;
    LinearLayoutManager mLayoutManager;

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
        mStateView = (StateView) getActivity().findViewById(R.id.stateview);
        refreshLayout.setOnRefreshListener(this);
        mStateView.setOnRetryClickListener(this);
        init();
        return view;
    }

    private void init() {
        meiziAdapter = new MeiziAdapter();
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(meiziAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                    if (mLayoutManager.getItemCount() == 1) {
                        if (meiziAdapter != null) {
                            meiziAdapter.updateLoadStatus(LoadMoreAdapter.LOAD_NONE);
                        }
                        return;

                    }
                    if (lastVisibleItem + 1 == mLayoutManager.getItemCount()) {
                        if (meiziAdapter != null) {
                            meiziAdapter.updateLoadStatus(LoadMoreAdapter.LOAD_PULL_TO);
                            // isLoadMore = true;
                            meiziAdapter.updateLoadStatus(LoadMoreAdapter.LOAD_MORE);
                        }
                        mPresenter.loaddata(mPresenter.getCurrentPage());

                    }
                }
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
        mStateView.showRetry();
    }

    @Override
    public void showList(List<Meizi> list) {
        meiziAdapter.setList(list);
    }

    @Override
    public void onRefresh() {
        mPresenter.setCurrentPage(0);
        mPresenter.loaddata(mPresenter.getCurrentPage());
    }

    @Override
    public void onRetryClick() {
        mPresenter.loaddata(mPresenter.getCurrentPage());
    }
}
