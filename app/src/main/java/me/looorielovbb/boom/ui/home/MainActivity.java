package me.looorielovbb.boom.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.ui.home.meizi.MeiziFragment;
import me.looorielovbb.boom.ui.home.mine.MineFragment;
import me.looorielovbb.boom.ui.home.movieandbooks.MBFragment;
import me.looorielovbb.boom.ui.home.zhihu.ZhihuFragment;
import me.looorielovbb.boom.ui.widgets.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomnavi)
    BottomNavigationView bottomNavi;

    Fragment[] fragments = new Fragment[4];
    FragmentManager fragmentManager;
    private int mCurrentIndex = 0;

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
        Field f;
        Object obj_get;
        for (int i = 0; i < arr.length; i++) {
            String param = arr[i];
            try {
                f = imm.getClass().getDeclaredField(param);
                if (!f.isAccessible()) {
                    f.setAccessible(true);
                }
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get.getContext() == destContext) {
                        // 被InputMethodManager持有引用的context是想要目标销毁的
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        BottomNavigationViewHelper.disableShiftMode(bottomNavi);

        if (savedInstanceState != null) {
            for (int i = 0; i < fragments.length; i++) {
                fragments[i] = fragmentManager.findFragmentByTag("HomeTab" + i);
            }
            mCurrentIndex = savedInstanceState.getInt("mCurrentIndex",0);
        } else {
            fragments[0] = ZhihuFragment.newInstance();
            fragments[1] = MeiziFragment.newInstance();
            fragments[2] = MBFragment.newInstance();
            fragments[3] = MineFragment.newInstance();
        }
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

        for (int i = 0; i < fragments.length; i++) {
            if (fragments[i] != null && !fragments[i].isAdded()) {
                fragmentManager
                        .beginTransaction()
                        .add(R.id.content, fragments[i], "HomeTab" + i)
                        .commit();
            }
        }


        bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navi1:
                        showHomeTabByIndex(0);
                        break;
                    case R.id.navi2:
                        showHomeTabByIndex(1);
                        break;
                    case R.id.navi3:
                        showHomeTabByIndex(2);
                        break;
                    case R.id.navi4:
                        showHomeTabByIndex(3);
                        break;
                }
                return true;
            }
        });
        showHomeTabByIndex(mCurrentIndex);

    }

    private void showHomeTabByIndex(int index) {
        mCurrentIndex = index;
        fragmentManager
                .beginTransaction()
                .show(fragments[index])
                .commit();
        for (int i = 0; i < fragments.length; i++) {
            if (i != index) {
                fragmentManager
                        .beginTransaction()
                        .hide(fragments[i])
                        .commit();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fixInputMethodManagerLeak(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        for (int i = 0; i < 4; i++) {
            if (fragments[i] != null && fragments[i].isAdded()) {
                fragmentManager.putFragment(outState, "HomeTab" + i, fragments[i]);
            }
        }
        outState.putInt("mCurrentIndex",mCurrentIndex);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lulei","onRestart");
    }
}
