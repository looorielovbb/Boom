package me.looorielovbb.boom.utils;

import android.annotation.TargetApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;


/**
 * Created by lei.lu on 2016/3/23.
 * time : 14:39
 * date : 2016/3/23
 * mail to lulei4461@gmail.com
 */
public class ToolbarUtils {
    /**
     * 初始化Toolbar
     */
    @SuppressWarnings("ConstantConditions")
    @TargetApi(19)
    public static void initToolBar(final AppCompatActivity context, Toolbar toolbar, String title) {
        initToolBar(context, toolbar, -1, title, null);
    }

    /**
     * 初始化Toolbar
     */
    @SuppressWarnings("ConstantConditions")
    @TargetApi(19)
    public static void initToolBar(final AppCompatActivity context, Toolbar toolbar, int resId,
                                   String title, View.OnClickListener listener) {
        initToolBarWithToggle(context, toolbar, title);

        if (null != listener) {
            toolbar.setNavigationOnClickListener(listener);
        } else {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onBackPressed();
                }
            });
        }
        //设置默认返回图标
        if (resId == -1) {
            //设置默认返回图标
            //            context.getSupportActionBar().setHomeAsUpIndicator(R.drawable
            // .ic_navigate_before);
        } else {
            context.getSupportActionBar().setHomeAsUpIndicator(resId);
        }
    }

    /**
     * 初始化Toolbar 显示Toolbar上的左边Toggle，一般配合DrawerLayout使用
     *
     * @param context
     * @param toolbar
     */
    @SuppressWarnings("ConstantConditions")
    @TargetApi(19)
    public static void initToolBarWithToggle(final AppCompatActivity context, Toolbar toolbar,
                                             String title) {
//        if (!PreferencesUtils.getBoolean(context, Constants.THEME_MODE, false)) {
//            toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
//        } else {
//            toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.night_colorPrimary));
//        }
        context.setSupportActionBar(toolbar);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context.getSupportActionBar().setHomeButtonEnabled(true);
        context.setTitle(title);

//        TextView tvTitle = (TextView) toolbar.findViewById(R.id.universal_bar_title);
//        tvTitle.setText(title);
    }

    /**
     * 初始化Toolbar 自定义左边icon
     */
    @SuppressWarnings("ConstantConditions")
    @TargetApi(19)
    public static void initToolBar(final AppCompatActivity context, Toolbar toolbar, int resId,
                                   String title) {
        initToolBar(context, toolbar, resId, title, null);
    }

    /**
     * 初始化Toolbar 不显示Toolbar上的左边返回键
     *
     * @param context
     * @param toolbar
     */
    @SuppressWarnings("ConstantConditions")
    @TargetApi(19)
    public static void initToolBarNoBack(final AppCompatActivity context,
                                         Toolbar toolbar,
                                         String title) {

        context.setSupportActionBar(toolbar);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        context.getSupportActionBar().setHomeButtonEnabled(false);
        context.setTitle(title);
    }
}
