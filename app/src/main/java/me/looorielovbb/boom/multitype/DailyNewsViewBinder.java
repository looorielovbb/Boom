package me.looorielovbb.boom.multitype;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.data.bean.zhihu.StoriesBean;
import me.looorielovbb.boom.utils.ImgUtils;
import me.looorielovbb.boom.utils.ToastUtils;

/**
 * Created by Lulei on 2017/4/18.
 * time : 15:31
 * date : 2017/4/18
 * mail to lulei4461@gmail.com
 */
public class DailyNewsViewBinder
        extends ItemViewBinder<StoriesBean, DailyNewsViewBinder.ViewHolder> {

    private Context context;

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
                                            @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_story_list_content, parent, false);
        context = root.getContext();
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull StoriesBean story) {
        holder.storyTitleTv.setText(story.getTitle());
        if (story.getImages()!=null) {
            ImgUtils.LoadNetImg(context,
                                story
                                        .getImages()
                                        .get(0),
                                holder.storyIv);
            if (story
                        .getImages()
                        .size() > 1) {
                holder.multiPicIv.setVisibility(View.VISIBLE);
            } else {
                holder.multiPicIv.setVisibility(View.GONE);
            }

            holder.storyFrameIv.setVisibility(View.VISIBLE);
        } else {
            holder.storyFrameIv.setVisibility(View.GONE);
        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("点击了该项目");
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.story_title_tv) TextView storyTitleTv;
        @BindView(R.id.story_iv) ImageView storyIv;
        @BindView(R.id.multi_pic_iv) ImageView multiPicIv;
        @BindView(R.id.story_frame_iv) FrameLayout storyFrameIv;
        @BindView(R.id.card) CardView card;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
