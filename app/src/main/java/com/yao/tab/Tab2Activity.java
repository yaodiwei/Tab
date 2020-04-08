package com.yao.tab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.yao.tab.ui.dashboard.DashboardFragment;
import com.yao.tab.ui.home.HomeFragment;
import com.yao.tab.ui.notifications.NotificationsFragment;

import java.util.Objects;

public class Tab2Activity extends AppCompatActivity {

    private static final int FRAGMENT_COUNT = 3;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_2);

        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);

        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());

        //设置左边和右边各缓存多少个页面。
        //设置成2后，可以保证3个Tab滑动到哪个Tab下，其他Tab都不会被回收。
        mViewPager.setOffscreenPageLimit(2);

        mTabLayout.setupWithViewPager(mViewPager, false);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @Override
            public int getCount() {
                return FRAGMENT_COUNT;
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                //https://mp.weixin.qq.com/s/MOWdbI5IREjQP1Px-WJY1Q
                if (position == 0) {
                    return new HomeFragment();
                } else if (position == 1) {
                    return new DashboardFragment();
                } else if (position == 2) {
                    return new NotificationsFragment();
                } else {
                    throw new IllegalStateException("FragmentPagerAdapter getItem position is illegal");
                }
            }
        };
        mViewPager.setAdapter(adapter);

        //需要放在 mTabLayout.setupWithViewPager 后面，否则标题会被替换掉。
        Objects.requireNonNull(mTabLayout.getTabAt(0)).setText(R.string.title_home).setIcon(R.drawable.ic_home);
        Objects.requireNonNull(mTabLayout.getTabAt(1)).setText(R.string.title_dashboard).setIcon(R.drawable.ic_dashboard);
        Objects.requireNonNull(mTabLayout.getTabAt(2)).setText(R.string.title_notifications).setIcon(R.drawable.ic_notifications);
    }
}
