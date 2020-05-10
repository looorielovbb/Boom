package me.looorielovbb.boom.multitype;

import android.content.Context;
import android.content.Intent;
import androidx.core.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
public class NetworkImageHolder implements Holder<String> {

    private ImageView imageView;
    private TextView tvTitle;
    private List<TopStoriesBean> topStoriesBeans;
    private View convertView;


    public NetworkImageHolder(List<TopStoriesBean> banner) {
        super();
        this.topStoriesBeans = banner;
    }

    @Override
    public View createView(Context context) {
        //可以通过layout文件来创建，也可以用代码创建，不一定是Image，任何控件都可以进行翻页
        convertView = View.inflate(context, R.layout.view_banner,null);
        imageView = (ImageView) convertView.findViewById(R.id.iv_pic);
        tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
        return convertView;
    }

    @Override
    public void UpdateUI(final Context context, final int position, String data) {
        ImgUtils.LoadNetImg(context, data, imageView);
        tvTitle.setText(topStoriesBeans.get(position).getTitle());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, ZhihuDetailActivity.class);
                intent.putExtra("id", topStoriesBeans.get(position).getId());
                intent.putExtra("isNotTransition", true);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(
                        v,
                        v.getWidth() ,
                        v.getHeight() ,
                        0,
                        0);
                context.startActivity(intent,options.toBundle());
            }
        });

    }
}
