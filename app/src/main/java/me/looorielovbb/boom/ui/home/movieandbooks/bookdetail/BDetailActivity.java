package me.looorielovbb.boom.ui.home.movieandbooks.bookdetail;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elvishew.xlog.XLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.base.BaseActivity;
import me.looorielovbb.boom.data.bean.douban.book.BookDetailBean;
import me.looorielovbb.boom.data.bean.douban.book.BooksBean;
import me.looorielovbb.boom.utils.ImgUtils;
import me.looorielovbb.boom.utils.StringUtils;
import me.looorielovbb.boom.utils.ToolbarUtils;
import me.solidev.statusviewlayout.StatusViewLayout;

/**
 * Created by Lulei on 2017/7/12.
 * time : 10:00
 * date : 2017/7/12
 * mail to lulei4461@gmail.com
 */

public class BDetailActivity extends BaseActivity implements BDetailContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.stateview)
    StatusViewLayout statusView;
    BooksBean book;
    @BindView(R.id.img_item_bg)
    ImageView imgItemBg;
    @BindView(R.id.iv_one_photo)
    ImageView ivOnePhoto;
    @BindView(R.id.tv_one_directors)
    TextView tvOneDirectors;
    @BindView(R.id.tv_one_rating_rate)
    TextView tvOneRatingRate;
    @BindView(R.id.tv_one_rating_number)
    TextView tvOneRatingNumber;
    @BindView(R.id.tv_one_casts)
    TextView tvOneCasts;
    @BindView(R.id.tv_one_day)
    TextView tvOneDay;
    @BindView(R.id.ll_book_detail)
    LinearLayout llBookDetail;
    @BindView(R.id.tv_book_summary)
    TextView tvBookSummary;

    @BindView(R.id.bookcontent)
    LinearLayout mBookContent;
    @BindView(R.id.tv_author_info)
    TextView tvAuthorInfo;
    @BindView(R.id.tv_book_catalog)
    TextView tvBookCatalog;
    @BindView(R.id.scrollview)
    NestedScrollView mScrollview;


    private BDetailContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book = (BooksBean) getIntent().getSerializableExtra("book");
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);
        setPresenter(new BDetailPresenter(this));
        ToolbarUtils.initToolBar(this, toolbar, book == null ? "" : book.getTitle());
        mPresenter.loaddata(book.getId());
        statusView.setOnRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loaddata(book.getId());
            }
        });

        mScrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX,
                                       int oldScrollY) {
                //变化率
                float headerBarOffsetY = getResources().getDimensionPixelOffset(R.dimen.space_220);
                //Toolbar与header高度的差值
                float offset = 1 - Math.max((headerBarOffsetY - scrollY) / headerBarOffsetY, 0f);
                //Toolbar背景色透明度
                XLog.e("offset:" + offset);
                int color = ContextCompat.getColor(BDetailActivity.this, R.color.colorPrimary);
                toolbar.setBackgroundColor(
                        Color.argb((int) (offset * 255), Color.red(color), Color.green(color), Color.blue(color)));
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showContent(BookDetailBean bean) {
        if (bean != null) {
            mBookContent.setVisibility(View.VISIBLE);
            ImgUtils.LoadNetImg(this, bean.getImages().getLarge(), ivOnePhoto);
            tvOneDirectors.setText(getResources().getString(R.string.author) + StringUtils.formatGenres(bean.getAuthor()));
            tvOneRatingRate.setText(getResources().getString(R.string.score) + bean.getRating().getAverage());
            tvOneRatingNumber.setText(bean.getRating().getNumRaters() + "人评论");
            tvOneCasts.setText(getResources().getString(R.string.publish_date) + bean.getPubdate());
            tvOneDay.setText(getResources().getString(R.string.publisher) + bean.getPublisher());

            tvBookSummary.setText(bean.getSummary());
            tvAuthorInfo.setText(bean.getAuthor_intro());
            tvBookCatalog.setText(bean.getCatalog());
        }
    }

    @Override
    public void showError(String e) {
        mBookContent.setVisibility(View.GONE);
        statusView.showError(e);
    }

    @Override
    public void setPresenter(BDetailContract.Presenter presenter) {
        if (presenter != null) {
            this.mPresenter = presenter;
        } else {
            throw new NullPointerException("presenter can not be null");
        }
    }
}
