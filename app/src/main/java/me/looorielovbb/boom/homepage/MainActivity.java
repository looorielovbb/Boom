package me.looorielovbb.boom.homepage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.elvishew.xlog.XLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.config.Constants;
import me.looorielovbb.boom.utils.PreferencesUtils;

public class MainActivity extends AppCompatActivity implements MainContract.View {
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
                        break;
                    case R.id.navi2:
                        break;
                    case R.id.navi3:
                        break;
                    case R.id.navi4:
                        break;
                    case R.id.navi5:
                        break;
                }
                return true;
            }
        });
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
