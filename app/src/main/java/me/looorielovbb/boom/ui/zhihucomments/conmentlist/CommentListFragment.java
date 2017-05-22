package me.looorielovbb.boom.ui.zhihucomments.conmentlist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.looorielovbb.boom.R;


public class CommentListFragment extends Fragment {
    private static final String ISHORT = "ishort";
    @BindView(R.id.list)
    RecyclerView list;
    Unbinder unbinder;

    private boolean mParam1;


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
        if (getArguments() != null) {
            mParam1 = getArguments().getBoolean(ISHORT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
