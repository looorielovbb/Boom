package me.looorielovbb.boom.ui.homepage;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.ui.uitools.BottomNavigationViewHelper;
import me.looorielovbb.boom.ui.uitools.TabFragmentManager;

public class MainActivity extends AppCompatActivity {

    //    @BindView(R.id.switcher)
//    Switch switcher;
    @BindView(R.id.bottomnavi)
    BottomNavigationView bottomNavi;
    Fragment[] fragments = new Fragment[5];
    TabFragmentManager tabFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BottomNavigationViewHelper.disableShiftMode(bottomNavi);

//        if (PreferencesUtils.getBoolean(this, Constants.THEME_MODE, false)) {
//            switcher.setChecked(true);
//        } else {
//            switcher.setChecked(false);
//        }
//        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    PreferencesUtils.putBoolean(MainActivity.this, Constants.THEME_MODE, true);
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//
//                } else {
//                    PreferencesUtils.putBoolean(MainActivity.this, Constants.THEME_MODE, false);
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                }
//                getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
//                recreate();
//            }
//        });
        fragments[0] = MainFragment.newInstance();
        fragments[1] = MeiziFragment.newInstance();
        fragments[2] = CleanerFragment.newInstance();
        fragments[3] = MovieFragment.newInstance();
        fragments[4] = MineFragment.newInstance();
        tabFragmentManager = new TabFragmentManager(this, fragments,R.id.maincontent);
        bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navi1:
                        tabFragmentManager.setCurrentItem(0);
                        break;
                    case R.id.navi2:
                        tabFragmentManager.setCurrentItem(1);
                        break;
                    case R.id.navi3:
                        tabFragmentManager.setCurrentItem(2);
                        break;
                    case R.id.navi4:
                        tabFragmentManager.setCurrentItem(3);
                        break;
                    case R.id.navi5:
                        tabFragmentManager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isAdded()) {
                getSupportFragmentManager().putFragment(outState, fragment.getClass().getSimpleName(), fragment);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
