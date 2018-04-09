package me.looorielovbb.boom.ui.zhihucomments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.adapter.FpAdapter;
import me.looorielovbb.boom.ui.zhihucomments.conmentlist.CommentListFragment;
import me.looorielovbb.boom.utils.ToolbarUtils;

public class CommentsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_zhihu_comment)
    TabLayout tabZhihuComment;
    @BindView(R.id.vp_zhihu_comment)
    ViewPager vpZhihuComment;
    private Fragment[] fragments = new Fragment[2];
    private String[] titles = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);
        ToolbarUtils.initToolBar(this, toolbar, "精选评论");

        int shortNum = Objects.requireNonNull(getIntent().getExtras()).getInt("shortNum");
        int longNum = Objects.requireNonNull(getIntent().getExtras()).getInt("longNum");
        titles[0] = String.format(Locale.CHINESE, "短评论(%d)", shortNum);
        titles[1] = String.format(Locale.CHINESE, "长评论(%d)", longNum);
        fragments[0] =  CommentListFragment.newInstance(true);
        fragments[1] =  CommentListFragment.newInstance(false);
        FpAdapter fpAdapter = new FpAdapter(getSupportFragmentManager(), fragments, titles);
        vpZhihuComment.setAdapter(fpAdapter);
        tabZhihuComment.setupWithViewPager(vpZhihuComment);

    }
}
