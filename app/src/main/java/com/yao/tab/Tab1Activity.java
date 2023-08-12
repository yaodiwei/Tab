package com.yao.tab;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.yao.tab.ui.dashboard.DashboardFragment;
import com.yao.tab.ui.home.HomeFragment;
import com.yao.tab.ui.news.news.NewsFragment;
import com.yao.tab.ui.notifications.NotificationsFragment;
import com.yao.tab.ui.profile.ProfileFragment;
import com.yao.tab.util.ResUtil;

public class Tab1Activity extends AppCompatActivity implements View.OnClickListener {

    private static final int FRAGMENT_COUNT = 5;

    private ViewPager mViewPager;

    private RadioButton mRbHome;
    private RadioButton mRbDashboard;
    private RadioButton mRbNotifications;
    private RadioButton mRbNews;
    private RadioButton mRbProfile;

    private final SparseArray<Fragment> fragmentList = new SparseArray<>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tab_1);

        mViewPager = findViewById(R.id.view_pager);

        mRbHome = findViewById(R.id.rb_home);
        mRbDashboard = findViewById(R.id.rb_dashboard);
        mRbNotifications = findViewById(R.id.rb_notifications);
        mRbNews = findViewById(R.id.rb_news);
        mRbProfile = findViewById(R.id.rb_profile);

        //设置 RadioButton 图标的大小
        Drawable drawableHome = ResUtil.getDrawable(R.drawable.ic_home);
        drawableHome.setBounds(0, 0, ResUtil.getDimen(R.dimen.tab_icon_size), ResUtil.getDimen(R.dimen.tab_icon_size));
        mRbHome.setCompoundDrawables(null, drawableHome, null, null);

        Drawable drawableDashboard = ResUtil.getDrawable(R.drawable.ic_dashboard);
        drawableDashboard.setBounds(0, 0, ResUtil.getDimen(R.dimen.tab_icon_size), ResUtil.getDimen(R.dimen.tab_icon_size));
        mRbDashboard.setCompoundDrawables(null, drawableDashboard, null, null);

        Drawable drawableNotifications = ResUtil.getDrawable(R.drawable.ic_notifications);
        drawableNotifications.setBounds(0, 0, ResUtil.getDimen(R.dimen.tab_icon_size), ResUtil.getDimen(R.dimen.tab_icon_size));
        mRbNotifications.setCompoundDrawables(null, drawableNotifications, null, null);

        Drawable drawableNews = ResUtil.getDrawable(R.drawable.ic_news);
        drawableNews.setBounds(0, 0, ResUtil.getDimen(R.dimen.tab_icon_size), ResUtil.getDimen(R.dimen.tab_icon_size));
        mRbNews.setCompoundDrawables(null, drawableNews, null, null);

        Drawable drawableProfile = ResUtil.getDrawable(R.drawable.ic_profile);
        drawableProfile.setBounds(0, 0, ResUtil.getDimen(R.dimen.tab_icon_size), ResUtil.getDimen(R.dimen.tab_icon_size));
        mRbProfile.setCompoundDrawables(null, drawableProfile, null, null);

        mRbHome.setOnClickListener(this);
        mRbDashboard.setOnClickListener(this);
        mRbNotifications.setOnClickListener(this);
        mRbNews.setOnClickListener(this);
        mRbProfile.setOnClickListener(this);

        //设置左边和右边各缓存多少个页面。
        //设置成2后，可以保证3个Tab滑动到哪个Tab下，其他Tab都不会被回收。
        mViewPager.setOffscreenPageLimit(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    onClick(mRbHome);
                } else if (position == 1) {
                    onClick(mRbDashboard);
                } else if (position == 2) {
                    onClick(mRbNotifications);
                } else if (position == 3) {
                    onClick(mRbNews);
                } else if (position == 4) {
                    onClick(mRbProfile);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}

            @Override
            public void onPageScrollStateChanged(int arg0) {}
        });

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
                } else if (position == 3) {
                    return new NewsFragment();
                } else if (position == 4) {
                    return new ProfileFragment();
                } else {
                    throw new IllegalStateException("FragmentPagerAdapter getItem position is illegal");
                }
            }
        };
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_home:
                mViewPager.setCurrentItem(0);
                mRbHome.setChecked(true);
                break;
            case R.id.rb_dashboard:
                mViewPager.setCurrentItem(1);
                mRbDashboard.setChecked(true);
                break;
            case R.id.rb_notifications:
                mViewPager.setCurrentItem(2);
                mRbNotifications.setChecked(true);
                break;
            case R.id.rb_news:
                mViewPager.setCurrentItem(3);
                mRbNews.setChecked(true);
                break;
            case R.id.rb_profile:
                mViewPager.setCurrentItem(4);
                mRbProfile.setChecked(true);
                break;
            default:
                break;
        }
    }

    public ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
