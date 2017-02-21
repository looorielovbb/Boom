package me.looorielovbb.boom.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.looorielovbb.boom.R;

/**
 * Created by Lulei on 2017/2/20.
 * time : 14:52
 * date : 2017/2/20
 * mail to lulei4461@gmail.com
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {


    List<Object> list;
    public void setList(List<Object> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_main, null));
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (list != null) {
            holder.setItem(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void setItem(Object item) {

        }
    }
}
