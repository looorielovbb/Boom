package me.looorielovbb.boom.ui.home.movieandbooks.Intheaters;


import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.adapter.IntheaterMovieAdapter;
import me.looorielovbb.boom.adapter.decoration.GridMarginDecoration;
import me.looorielovbb.boom.base.LazyLoadFragment;
import me.looorielovbb.boom.data.bean.douban.MovieInfo;
import me.looorielovbb.boom.utils.ToastUtils;
import me.solidev.statusviewlayout.StatusViewLayout;


public class InTheatersMovieListFragment extends LazyLoadFragment
        implements SwipeRefreshLayout.OnRefreshListener, InTheatersContract.View {


    @BindView(R.id.recyclerView)
    RecyclerView rvMovies;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.stateview)
    StatusViewLayout stateview;
    Unbinder unbinder;
    IntheaterMovieAdapter adapter;
    private InTheatersContract.Presenter mPresenter;

    public InTheatersMovieListFragment() {

    }

    public static InTheatersMovieListFragment newInstance() {
        Bundle args = new Bundle();
        InTheatersMovieListFragment fragment = new InTheatersMovieListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        setPresenter(new InTheatersPresenter(this));
        initView();
        return view;
    }

    private void initView() {
        refreshLayout.setOnRefreshListener(this);
        adapter = new IntheaterMovieAdapter();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        rvMovies.setLayoutManager(layoutManager);
        rvMovies.addItemDecoration(new GridMarginDecoration(getContext().getResources().getDimensionPixelOffset(R.dimen.gridlayout_margin_decoration)));
        rvMovies.setAdapter(adapter);
    }

    @Override
    public void requestData() {
        mPresenter.loaddata("null");
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
        adapter.setMovieInfoList(list);
    }

    @Override
    public void setPresenter(InTheatersContract.Presenter presenter) {
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
        mPresenter.loaddata("null");
    }
}
