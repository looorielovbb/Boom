package me.looorielovbb.boom.ui.zhihucomments.conmentlist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import me.looorielovbb.boom.adapter.ZhihuCommentsAdapter;
import me.looorielovbb.boom.data.bean.zhihu.Comment;


public class CommentListFragment extends Fragment implements CommentConstract.View {
    private static final String ISHORT = "ishort";
    @BindView(R.id.list)
    RecyclerView list;
    Unbinder unbinder;

    private boolean ishort;
    private CommentConstract.Presenter mPresenter;
    private int id = 0;
    private ZhihuCommentsAdapter adapter;

    public CommentListFragment() {
    }


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

    private void initView() {
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ZhihuCommentsAdapter();
        list.setAdapter(adapter);
        if (ishort) {
            mPresenter.fetchShortCommentInfo(id);
        } else {
            mPresenter.fetchLongCommentInfo(id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_list, container, false);
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
}
