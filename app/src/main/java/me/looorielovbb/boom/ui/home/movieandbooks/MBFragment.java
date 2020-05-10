package me.looorielovbb.boom.ui.home.movieandbooks;


import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.base.LazyLoadFragment;
import me.looorielovbb.boom.ui.home.movieandbooks.Intheaters.InTheatersMovieListFragment;
import me.looorielovbb.boom.ui.home.movieandbooks.books.BookFragment;
import me.looorielovbb.boom.ui.home.movieandbooks.comingsoon.ComingFragment;
import me.looorielovbb.boom.ui.home.movieandbooks.top250.Top250Fragment;

public class MBFragment extends LazyLoadFragment {

    @BindView(R.id.tab_douban)
    TabLayout tabDouban;
    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @BindView(R.id.vp_douban)
    ViewPager vpDouban;
    Unbinder unbinder;
    Fragment[] fragments = new Fragment[4];

    public MBFragment() {
    }

    public static Fragment newInstance() {
        return new MBFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        fragments[0] = InTheatersMovieListFragment.newInstance();
        fragments[1] = ComingFragment.newInstance();
        fragments[2] = Top250Fragment.newInstance();
        fragments[3] = BookFragment.newInstance();
        VpAdapter adapter = new VpAdapter(getChildFragmentManager(), fragments);
        vpDouban.setAdapter(adapter);
        tabDouban.setupWithViewPager(vpDouban);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void requestData() {

    }

    class VpAdapter extends FragmentPagerAdapter {
        String[] titles = {"正在热映", "即将上映", "TOP250", "书籍推荐"};
        Fragment[] fragments;
        FragmentManager fm;

        public VpAdapter(FragmentManager fm, Fragment[] fragments) {
            super(fm);
            this.fm = fm;
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int i) {
            return fragments[i];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @NonNull
        @Override
        public Fragment instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            fm.beginTransaction().show(fragment).commit();
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Fragment fragment = fragments[position];
            fm.beginTransaction().hide(fragment).commit();
        }
    }

}
