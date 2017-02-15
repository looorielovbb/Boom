package me.looorielovbb.boom.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.elvishew.xlog.XLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.NewActivity;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.config.Constants;
import me.looorielovbb.boom.utils.PreferencesUtils;
import me.looorielovbb.boom.utils.ToastUtils;
import me.looorielovbb.boom.utils.ToolbarUtils;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.switcher)
    Switch switcher;
    @BindView(R.id.bottomnavi)
    BottomNavigationView bottomNavi;
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XLog.e("onCreate");
        ButterKnife.bind(this);
        ToolbarUtils.initToolBarNoBack(this, toolbar, "HomePage");

        if (PreferencesUtils.getBoolean(this, Constants.THEME_MODE, false)) {
            switcher.setChecked(true);
        } else {
            switcher.setChecked(false);
        }
        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PreferencesUtils.putBoolean(MainActivity.this, Constants.THEME_MODE, true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                } else {
                    PreferencesUtils.putBoolean(MainActivity.this, Constants.THEME_MODE, false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
                recreate();
            }
        });
        bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navi1:
                        ToastUtils.show("1");
                        break;
                    case R.id.navi2:
                        ToastUtils.show("2");
                        break;
                    case R.id.navi3:
                        ToastUtils.show("3");
                        break;
                }
                return true;
            }
        });
    }


    public void btn(View view) {
        startActivity(new Intent(MainActivity.this, NewActivity.class));
    }

    @Override
    public void showloading() {

    }

    @Override
    public void showerror() {

    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }
}
