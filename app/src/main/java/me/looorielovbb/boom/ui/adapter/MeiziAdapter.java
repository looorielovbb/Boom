package me.looorielovbb.boom.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.data.bean.gank.Meizi;
import me.looorielovbb.boom.ui.picturepage.PicActivity;
import me.looorielovbb.boom.ui.uitools.loadmore.LoadMoreAdapter;
import me.looorielovbb.boom.utils.ImgUtils;

/**
 * Created by Lulei on 2017/4/6.
 * time : 15:03
 * date : 2017/4/6
 * mail to lulei4461@gmail.com
 */

public class MeiziAdapter extends LoadMoreAdapter<Meizi> {
    private Activity context;

    public MeiziAdapter(Activity activity){
        this.context = activity;
    }

    @Override
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final ViewHolder itemholder = (ViewHolder) holder;
            final Meizi meizi = items.get(position);
            ImgUtils.LoadNetImg(context, meizi.getUrl(), itemholder.imageView);
            itemholder.tvDate.setText(items
                                              .get(position)
                                              .getDate());
            itemholder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = PicActivity.newIntent(context, meizi.getUrl(), meizi.getDesc());
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(
                            itemholder.itemView,
                            itemholder.itemView.getWidth() ,
                            itemholder.itemView.getHeight() ,
                            0,
                            0);
                    context.startActivity(intent, options.toBundle());
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_meizi, null);
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
