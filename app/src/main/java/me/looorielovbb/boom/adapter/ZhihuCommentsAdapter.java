package me.looorielovbb.boom.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.data.bean.zhihu.Comment;
import me.looorielovbb.boom.utils.DateUtils;
import me.looorielovbb.boom.utils.ImgUtils;

/**
 * Created by Lulei on 2017/5/23.
 * time : 11:01
 * date : 2017/5/23
 * mail to lulei4461@gmail.com
 */

public class ZhihuCommentsAdapter extends RecyclerView.Adapter<ZhihuCommentsAdapter.ViewHolder> {
    private static final int STATE_NULL = 0;    //未知
    private static final int STATE_NONE = 1;    //无需展开
    private static final int STATE_EXPAND = 2;  //已展开
    private static final int STATE_SHRINK = 3;  //已收缩

    private static final int MAX_LINE = 2;  //起始最多显示2行

    private List<Comment> commentList = new ArrayList<>();
    private Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        return new ViewHolder(LayoutInflater
                .from(mContext)
                .inflate(R.layout.item_zhihu_comment, viewGroup, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Comment item = commentList.get(i);
        ImgUtils.LoadNetImg(mContext, item.getAvatar(), viewHolder.civCommentFace);
        viewHolder.tvCommentName.setText(item.getAuthor());
        viewHolder.tvCommentContent.setText(item.getContent());
//        viewHolder.tvCommentReply.setText(item.getReply_to());
        viewHolder.tvCommentTime.setText(DateUtils.formatTime2String(item.getTime()));
        viewHolder.tvCommentLike.setText(item.getLikes() + "");
        final TextView tvCommentReply = viewHolder.tvCommentReply;
        final TextView tvCommentExpand = viewHolder.tvCommentExpand;
        if (item.getReply_to() != null && item.getReply_to().getId() != 0) {
            tvCommentReply.setVisibility(View.VISIBLE);
            SpannableString ss = new SpannableString("@" + item.getReply_to().getAuthor()
                    + ": " + item.getReply_to().getContent());
            ss.setSpan(new ForegroundColorSpan(ContextCompat
                            .getColor(mContext, R.color.comment_at)),
                    0, item.getReply_to().getAuthor().length() + 2,
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            tvCommentReply.setText(ss);
            if (item.getReply_to().getExpandState() == STATE_NULL) {    //未知
                tvCommentReply.post(new Runnable() {
                    @Override
                    public void run() {
                        if (tvCommentReply.getLineCount() > MAX_LINE) {
                            tvCommentReply.setMaxLines(MAX_LINE);
                            tvCommentExpand.setVisibility(View.VISIBLE);
                            tvCommentExpand.setText("展开");
                            item.getReply_to().setExpandState(STATE_SHRINK);
                            tvCommentExpand.setOnClickListener(new OnStateClickListener(
                                    viewHolder.getAdapterPosition(), tvCommentReply));
                        } else {
                            tvCommentExpand.setVisibility(View.GONE);
                            item.getReply_to().setExpandState(STATE_NONE);
                        }
                    }
                });
            } else if (item.getReply_to().getExpandState() == STATE_NONE) {  //无需展开
                tvCommentExpand.setVisibility(View.GONE);
            } else if (item.getReply_to().getExpandState() == STATE_EXPAND) {    //已展开
                tvCommentReply.setMaxLines(Integer.MAX_VALUE);
                tvCommentExpand.setText("收起");
                tvCommentExpand.setVisibility(View.VISIBLE);
                tvCommentExpand.setOnClickListener(new OnStateClickListener(i, tvCommentReply));
            } else {    //已收缩
                tvCommentReply.setMaxLines(MAX_LINE);
                tvCommentExpand.setText("展开");
                tvCommentExpand.setVisibility(View.VISIBLE);
                tvCommentExpand.setOnClickListener(new OnStateClickListener(i, tvCommentReply));
            }
        } else {
            tvCommentReply.setVisibility(View.GONE);
            tvCommentExpand.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return commentList == null ? 0 : commentList.size();
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.civ_comment_face)
        CircleImageView civCommentFace;
        @BindView(R.id.tv_comment_name)
        TextView tvCommentName;
        @BindView(R.id.tv_comment_content)
        TextView tvCommentContent;
        @BindView(R.id.tv_comment_reply)
        TextView tvCommentReply;
        @BindView(R.id.tv_comment_time)
        TextView tvCommentTime;
        @BindView(R.id.tv_comment_expand)
        TextView tvCommentExpand;
        @BindView(R.id.tv_comment_like)
        TextView tvCommentLike;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private class OnStateClickListener implements View.OnClickListener {

        TextView replyView;
        int position;

        public OnStateClickListener(int position, TextView replyView) {
            this.position = position;
            this.replyView = replyView;
        }

        @Override
        public void onClick(View view) {
            TextView tv = (TextView) view;
            if (commentList.get(position).getReply_to().getExpandState() == STATE_SHRINK) {
                tv.setText("收起");
                replyView.setMaxLines(Integer.MAX_VALUE);
                replyView.setEllipsize(null);
                commentList.get(position).getReply_to().setExpandState(STATE_EXPAND);
            } else {
                tv.setText("展开");
                replyView.setMaxLines(MAX_LINE);
                replyView.setEllipsize(TextUtils.TruncateAt.END);
                commentList.get(position).getReply_to().setExpandState(STATE_SHRINK);
            }
        }
    }
}
