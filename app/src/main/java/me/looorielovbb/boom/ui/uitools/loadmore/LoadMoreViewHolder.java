package me.looorielovbb.boom.ui.uitools.loadmore;

import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.utils.ScreenUtils;

/**
 * Created by Lulei on 2017/4/7.
 * time : 16:08
 * date : 2017/4/7
 * mail to lulei4461@gmail.com
 */
public class LoadMoreViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_load_prompt) public TextView tv_load_prompt;
    @BindView(R.id.progress) public ProgressBar progress;

    public LoadMoreViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        LinearLayoutCompat.LayoutParams params
                = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                      (int) ScreenUtils.dpToPx(itemView.getContext(),
                                                                               40));
        itemView.setLayoutParams(params);
    }
}
