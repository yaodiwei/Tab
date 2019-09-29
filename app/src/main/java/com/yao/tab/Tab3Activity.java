package com.yao.tab;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.yao.tab.ui.dashboard.DashboardFragment;
import com.yao.tab.ui.home.HomeFragment;
import com.yao.tab.ui.notifications.NotificationsFragment;
import com.yao.tab.util.FragmentSwitchTool;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Tab3Activity extends AppCompatActivity {

    private static final String TAG = "Tab3Activity";

    private List<Fragment> mFragments = new ArrayList<>();

    private LinearLayout mLayoutHome;
    private LinearLayout mLayoutDashboard;
    private LinearLayout mLayoutNotifications;
    //当前被选中的Layout，包括图片和文字。图片需要是个Selector才能实现选中状态。
    private LinearLayout mLayoutCurrent;

    private FragmentSwitchTool mFragmentSwitchTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tab_3);

        mLayoutHome = findViewById(R.id.layout_home);
        mLayoutDashboard = findViewById(R.id.layout_dashboard);
        mLayoutNotifications = findViewById(R.id.layout_notifications);

        mFragmentSwitchTool = new FragmentSwitchTool.Builder()
                .fragmentManager(getSupportFragmentManager())
                .containerId(R.id.layout_container)
                .clickableViews(mLayoutHome, mLayoutDashboard, mLayoutNotifications)
                .fragments(new HomeFragment(), new DashboardFragment(), new NotificationsFragment())
                .showAnimator(true)
                .onClickListener(new FragmentSwitchTool.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.e(TAG, "Tab3Activity.java - onClick() ----- view:" + getResources().getResourceName(view.getId()));
                    }
                })
                .onDoubleClickListener(new FragmentSwitchTool.OnDoubleClickListener() {
                    @Override
                    public void onDoubleClick(View view) {
                        String viewName = getResources().getResourceName(view.getId());
                        Log.e(TAG, "Tab3Activity.java - onDoubleClick() ----- view:" + viewName);
                        Log.e(TAG, viewName + " 被双击，可以执行列表页面的回到顶部操作");
                    }
                })
                .build();

        mFragmentSwitchTool.changeTag(mLayoutHome);
    }

}
