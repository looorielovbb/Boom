package me.looorielovbb.boom.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.data.bean.Meizi;
import me.looorielovbb.boom.ui.uitools.loadmore.LoadMoreAdapter;
import me.looorielovbb.boom.utils.ImgUtils;

/**
 * Created by Lulei on 2017/4/6.
 * time : 15:03
 * date : 2017/4/6
 * mail to lulei4461@gmail.com
 */

public class MeiziAdapter extends LoadMoreAdapter<Meizi> {
    private Context context;


    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder itemholder = (ViewHolder) holder;
            Meizi meizi = items.get(position);
            ImgUtils.LoadNetImg(context,meizi.getUrl(),itemholder.imageView);
            itemholder.tvDate.setText(items.indexOf(meizi) + 1 + "");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = View.inflate(context, R.layout.item_meinv, null);
        return new ViewHolder(view);
    }


    public void updateLoadingStatus(boolean isLoadcomplete) {
        this.isLoadcomplete = isLoadcomplete;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img) ImageView imageView;
        @BindView(R.id.tv_date) TextView tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
