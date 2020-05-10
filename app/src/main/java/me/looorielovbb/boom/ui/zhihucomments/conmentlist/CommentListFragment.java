package me.looorielovbb.boom.ui.zhihucomments.conmentlist;


import android.os.Bundle;
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
import me.looorielovbb.boom.adapter.ZhihuCommentsAdapter;
import me.looorielovbb.boom.base.LazyLoadFragment;
import me.looorielovbb.boom.data.bean.zhihu.Comment;
import me.solidev.statusviewlayout.StatusViewLayout;


public class CommentListFragment extends LazyLoadFragment implements CommentConstract.View, SwipeRefreshLayout.OnRefreshListener {
    private static final String ISHORT = "ishort";
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView rvCommentList;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.stateview)
    StatusViewLayout stateview;

    private boolean ishort;
    private CommentConstract.Presenter mPresenter;
    private int id = 0;
    private ZhihuCommentsAdapter adapter;

    public static CommentListFragment newInstance(boolean ishort) {
        CommentListFragment fragment = new CommentListFragment();
        Bundle args = new Bundle();
        args.putBoolean(ISHORT, ishort);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new CommentPresenter(this));
        if (getArguments() != null) {
            ishort = getArguments().getBoolean(ISHORT);
        }
        id = getActivity().getIntent().getExtras().getInt("id");
    }

    @Override
    public void requestData() {
        if (ishort) {
            mPresenter.fetchShortCommentInfo(id);
        } else {
            mPresenter.fetchLongCommentInfo(id);
        }
    }

    private void initView() {
        rvCommentList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ZhihuCommentsAdapter();
        rvCommentList.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void setPresenter(CommentConstract.Presenter presenter) {
        if (presenter != null) {
            this.mPresenter = presenter;
        } else {
            throw new NullPointerException("presenter can not be null");
        }
    }

    @Override
    public void showList(List<Comment> comments) {
        if (comments != null) {
            adapter.setCommentList(comments);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showLoading() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void dissmissLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        requestData();
    }
}
