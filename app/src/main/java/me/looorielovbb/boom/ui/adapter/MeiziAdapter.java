package me.looorielovbb.boom.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.data.bean.Meizi;
import me.looorielovbb.boom.utils.ImgUtils;

/**
 * Created by Lulei on 2017/3/10.
 * time : 15:29
 * date : 2017/3/10
 * mail to lulei4461@gmail.com
 */

public class MeiziAdapter extends LoadMoreAdapter<Meizi> {

    @Override
    public RecyclerView.ViewHolder getItemHolder(ViewGroup parent, int viewType) {
        View rootView = View.inflate(parent.getContext(), R.layout.item_meizi, null);
        return new ViewHolder(rootView);
    }

    @Override
    protected void OnBindItemHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemholder = (ViewHolder) holder;
        itemholder.bindItem(mList.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item) ImageView imageView;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(Meizi item) {
            ImgUtils.LoadNetImg(context, item.getUrl(), imageView);
        }

    }

    public void setList(List<Meizi> mList){
        if (mList==null){
            mList = new LinkedList<>();
        }
        this.mList = mList;
        notifyDataSetChanged();
    }
}
