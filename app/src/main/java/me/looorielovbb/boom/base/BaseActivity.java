package me.looorielovbb.boom.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import me.looorielovbb.boom.ui.widgets.statusbar.StatusBarUtil;


/**
 * Created by LooorieDov on 2016/9/23.
 * Time : 0:51
 * mail to : lulei4461@gmail.com
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucentForCoordinatorLayout(this,0);
    }
}
