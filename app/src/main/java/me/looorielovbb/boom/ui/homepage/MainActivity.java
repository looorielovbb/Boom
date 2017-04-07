package me.looorielovbb.boom.ui.homepage;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.ui.homepage.cleaner.CleanerFragment;
import me.looorielovbb.boom.ui.homepage.main.MainFragment;
import me.looorielovbb.boom.ui.homepage.meizi.MeiziFragment;
import me.looorielovbb.boom.ui.homepage.mine.MineFragment;
import me.looorielovbb.boom.ui.homepage.movie.MovieFragment;
import me.looorielovbb.boom.ui.uitools.BottomNavigationViewHelper;
import me.looorielovbb.boom.ui.uitools.TabFragmentManager;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.appbarlayout) AppBarLayout appBarLayout;
    @BindView(R.id.bottomnavi) BottomNavigationView bottomNavi;
    //    @BindView(R.id.stateview) StateView mStateView;
    Fragment[] fragments = new Fragment[5];
    FragmentManager fragmentManager;
    TabFragmentManager tabFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BottomNavigationViewHelper.disableShiftMode(bottomNavi);
        fragmentManager = getSupportFragmentManager();
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
        if (null != savedInstanceState) {
            fragments[0] = fragmentManager.getFragment(savedInstanceState, "MainFragment");
            fragments[1] = fragmentManager.getFragment(savedInstanceState, "MeiziFragment");
            fragments[2] = fragmentManager.getFragment(savedInstanceState, "CleanerFragment");
            fragments[3] = fragmentManager.getFragment(savedInstanceState, "MovieFragment");
            fragments[4] = fragmentManager.getFragment(savedInstanceState, "MineFragment");
        } else {
            fragments[0] = MainFragment.newInstance();
            fragments[1] = MeiziFragment.newInstance();
            fragments[2] = CleanerFragment.newInstance();
            fragments[3] = MovieFragment.newInstance();
            fragments[4] = MineFragment.newInstance();
        }
        tabFragmentManager = new TabFragmentManager(this, fragments, R.id.maincontent);
        bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                mStateView.showContent();
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
    protected void onDestroy() {
        super.onDestroy();
        fixInputMethodManagerLeak(this);
    }

    public static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }

        InputMethodManager imm
                = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f = null;
        Object obj_get = null;
        for (int i = 0; i < arr.length; i++) {
            String param = arr[i];
            try {
                f = imm
                        .getClass()
                        .getDeclaredField(param);
                if (f.isAccessible() == false) {
                    f.setAccessible(true);
                } // author: sodino mail:sodino@qq.com
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get.getContext() ==
                        destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                        break;
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isAdded()) {
                getSupportFragmentManager().putFragment(outState,
                                                        fragment
                                                                .getClass()
                                                                .getSimpleName(),
                                                        fragment);
            }
        }
    }

}
