package me.looorielovbb.boom.multitype;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;

import java.util.List;

import me.looorielovbb.boom.R;
import me.looorielovbb.boom.data.bean.zhihu.TopStoriesBean;
import me.looorielovbb.boom.utils.ImgUtils;

/**
 * Created by Lulei on 2016/12/9.
 * time : 16:31
 * date : 2016/12/9
 * mail to lulei4461@gmail.com
 */

// ConvenientBanner 组件加载网络图片holder
public class NetworkImageHolder implements Holder<String> {

    boolean clickable;
    private ImageView imageView;
    private TextView tvTitle;
    private List<TopStoriesBean> banner;

    public NetworkImageHolder(List<TopStoriesBean> banner, boolean clickable) {
        super();
        this.banner = banner;
        this.clickable = clickable;
    }

    @Override
    public View createView(Context context) {
        //可以通过layout文件来创建，也可以用代码创建，不一定是Image，任何控件都可以进行翻页
        View v = View.inflate(context, R.layout.view_banner,null);
        imageView = (ImageView) v.findViewById(R.id.iv_pic);
        tvTitle = (TextView) v.findViewById(R.id.tv_title);
        return v;
    }

    @Override
    public void UpdateUI(final Context context, final int position, String data) {
        ImgUtils.LoadNetImg(context, data, imageView);
        tvTitle.setText(banner.get(position).getTitle());

    }
}
