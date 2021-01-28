package me.looorielovbb.boom.multitype;

import android.content.Context;
import android.content.Intent;
import androidx.core.app.ActivityOptionsCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import java.util.List;

import me.looorielovbb.boom.R;
import me.looorielovbb.boom.data.bean.zhihu.TopStoriesBean;
import me.looorielovbb.boom.ui.zhihudetail.ZhihuDetailActivity;
import me.looorielovbb.boom.utils.ImgUtils;

/**
 * Created by Lulei on 2016/12/9.
 * time : 16:31
 * date : 2016/12/9
 * mail to lulei4461@gmail.com
 */

// ConvenientBanner 组件加载网络图片holder
public class NetworkImageHolder extends Holder<TopStoriesBean> {

    private ImageView imageView;
    private TextView tvTitle;
    private Context mContext;

    public NetworkImageHolder(View itemview) {
        super(itemview);
    }

    @Override
    protected void initView(View itemView) {
        mContext = itemView.getContext();
        imageView = itemView.findViewById(R.id.iv_pic);
        tvTitle = itemView.findViewById(R.id.tv_title);
    }

    @Override
    public void updateUI(TopStoriesBean data) {
        if (mContext==null) {
            return;
        }
        if (data!=null) {
            ImgUtils.LoadNetImg(mContext, data.getImage(), imageView);
            tvTitle.setText(data.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, ZhihuDetailActivity.class);
                    intent.putExtra("id", data.getId());
                    intent.putExtra("isNotTransition", true);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(
                            v, v.getWidth() , v.getHeight() , 0, 0);
                    mContext.startActivity(intent,options.toBundle());
                }
            });
        }

    }
}
