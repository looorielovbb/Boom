package me.looorielovbb.boom.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;
import java.security.Permission;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.ui.home.meizi.MeiziFragment;
import me.looorielovbb.boom.ui.home.mine.MineFragment;
import me.looorielovbb.boom.ui.home.movieandbooks.MBFragment;
import me.looorielovbb.boom.ui.home.zhihu.ZhihuFragment;
import me.looorielovbb.boom.ui.widgets.BottomNavigationViewHelper;
import me.looorielovbb.boom.utils.ToastUtils;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomnavi)
    BottomNavigationView bottomNavi;

    Fragment[] fragments = new Fragment[4];
    FragmentManager fragmentManager;
    private int mCurrentIndex = 0;
    private int PERMISSIONS_REQUEST_INTERNET = 10;

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
        for (String param : arr) {
            try {
                f = imm.getClass().getDeclaredField(param);
                if (!f.isAccessible()) {
                    f.setAccessible(true);
                }
                obj_get = f.get(imm);
                if (obj_get instanceof View) {
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
        if (checkSelfPermission(Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.INTERNET)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog dialog =  new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("请授予网络权限，软件方可正常运行")
                        .create();
                dialog.show();
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET},
                        PERMISSIONS_REQUEST_INTERNET);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
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


        bottomNavi.setOnNavigationItemSelectedListener(item -> {
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
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_INTERNET){
            ToastUtils.show("已开启网络权限");
        }
    }
}
