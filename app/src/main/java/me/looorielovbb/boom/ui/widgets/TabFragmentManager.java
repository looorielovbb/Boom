/*
 * Copyright (c) 2016.
 * 南京码动通信科技版权所有
 * 开发人员：马腾飞
 *
 */

package me.looorielovbb.boom.ui.widgets;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class TabFragmentManager {

    private Fragment[] fragments; // 一个tab页面对应一个Fragment
    private FragmentActivity fragmentActivity; // Fragment所属的Activity
    private int fragmentContentId; // Activity中所要被替换的区域的id
    private int currentTab; // 当前Tab页面索引

    public TabFragmentManager(FragmentActivity fragmentActivity,
                              Fragment[] fragments,
                              @IdRes int fragmentContentId) {
        this.fragments = fragments;
        this.fragmentActivity = fragmentActivity;
        this.fragmentContentId = fragmentContentId;
        // 默认显示第一页
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        ft.add(fragmentContentId, fragments[0],fragments[0].getClass().getCanonicalName());
        ft.commit();
    }

    public void setCurrentItem(int i) {
        Fragment fragment = fragments[i];
        FragmentTransaction ft = obtainFragmentTransaction(i);
        getCurrentFragment().onPause(); // 暂停当前tab
        if (fragment.isAdded()) {
            fragment.onResume(); // 启动目标tab的onResume()
        } else {
            ft.add(fragmentContentId, fragment);
            ft.commit();
        }
        showFragment(i);
    }

    /**
     * 获取一个带动画的FragmentTransaction
     *
     * @param index
     * @return
     */
    private FragmentTransaction obtainFragmentTransaction(int index) {
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        // 设置切换动画
        if (index > currentTab) {
            // ft.setCustomAnimations(R.anim.slide_left_in,
            // R.anim.slide_left_out);
        } else {
            // ft.setCustomAnimations(R.anim.slide_right_in,
            // R.anim.slide_right_out);
        }
        return ft;
    }

    public Fragment getCurrentFragment() {
        return fragments[currentTab];
    }

    private void showFragment(int idx) {
        for (int i = 0; i < fragments.length; i++) {
            Fragment fragment = fragments[i];
            FragmentTransaction ft = obtainFragmentTransaction(idx);
            if (idx == i) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commit();
        }
        currentTab = idx; // 更新目标tab为当前tab
    }
}




