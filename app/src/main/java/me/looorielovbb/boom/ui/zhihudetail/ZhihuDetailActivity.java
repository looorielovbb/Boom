package me.looorielovbb.boom.ui.zhihudetail;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.elvishew.xlog.XLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.data.bean.zhihu.DetailExtraBean;
import me.looorielovbb.boom.data.bean.zhihu.ZhihuDetailBean;
import me.looorielovbb.boom.ui.zhihucomments.CommentsActivity;
import me.looorielovbb.boom.utils.HtmlUtil;
import me.looorielovbb.boom.utils.ImgUtils;
import me.looorielovbb.boom.utils.ToastUtils;
import me.looorielovbb.boom.utils.ToolbarUtils;

public class ZhihuDetailActivity extends AppCompatActivity implements ZdetailContract.View {

    @BindView(R.id.detail_bar_image)
    ImageView detailBarImage;
    @BindView(R.id.detail_bar_copyright)
    TextView detailBarCopyright;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.wv_detail_content)
    WebView wvDetailContent;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    @BindView(R.id.activity_editor_detail)
    CoordinatorLayout activityEditorDetail;

    ZdetailContract.Presenter mPresenter;
    boolean isNotTransition = false;
    private String imgUrl;
    private String shareUrl;
    private int id = 0;
    private int shortNum, longNum = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu_detail);
        ButterKnife.bind(this);
        mPresenter = new ZdetailPresenter(this);
        ToolbarUtils.initToolBar(this, toolBar, "");
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    private void initView() {

        WebSettings settings = wvDetailContent.getSettings();
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
//        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        //加载
        id = getIntent().getIntExtra("id", 0);
        isNotTransition = getIntent().getBooleanExtra("isNotTransition", false);
        mPresenter.getStoryContent(id);
        mPresenter.getStoryExtras(id);
    }

    @Override
    public void showContent(ZhihuDetailBean zhihuDetailBean) {
        imgUrl = zhihuDetailBean.getImage();
        shareUrl = zhihuDetailBean.getShare_url();
        ImgUtils.LoadNetImg(this, zhihuDetailBean.getImage(), detailBarImage);
        toolBar.setTitle(zhihuDetailBean.getTitle());
        collapsingToolbar.setTitle(zhihuDetailBean.getTitle());
//        detailBarTitle.setText(zhihuDetailBean.getTitle());
        detailBarCopyright.setText(zhihuDetailBean.getImage_source());
        String htmlData = HtmlUtil.createHtmlData(zhihuDetailBean.getBody(), zhihuDetailBean.getCss(), zhihuDetailBean.getJs());
        wvDetailContent.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }

    @Override
    public void showStoryExtras(DetailExtraBean detailExtraBean) {
//        commentProvider.setNumInt(String.valueOf(detailExtraBean.getComments()));
//        praiseProvider.setNumInt(String.valueOf(detailExtraBean.getPopularity()));
        shortNum = detailExtraBean.getShort_comments();
        longNum = detailExtraBean.getLong_comments();
    }

    @Override
    public void showError(Throwable e) {
        AlertDialog d = new AlertDialog
                .Builder(this)
                .setCancelable(true)
                .setMessage(e.getMessage())
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        d.show();
    }

    @Override
    public void setPresenter(ZdetailContract.Presenter presenter) {
        if (presenter != null) {
            this.mPresenter = presenter;
        } else {
            throw new NullPointerException("presenter can not be null");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.zhihu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.comments:
                Intent intent = new Intent();
                intent.setClass(this, CommentsActivity.class);
                if (id == 0 || shortNum == -1 || longNum == -1) {
                    XLog.e(id + "  ||  " + shortNum + "  ||  " + longNum);
                    ToastUtils.show("数据加载中...");
                    break;
                }
                intent.putExtra("id", id);
                intent.putExtra("shortNum", shortNum);
                intent.putExtra("longNum", longNum);
                startActivity(intent);
                break;
            case R.id.share:
                ToastUtils.show("分享");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
