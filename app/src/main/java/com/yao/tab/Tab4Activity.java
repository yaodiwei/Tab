package com.yao.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import androidx.appcompat.app.AppCompatActivity;
import com.yao.tab.frame.BaseFrame;
import com.yao.tab.frame.ChatFrame;
import com.yao.tab.frame.ContactFrame;
import com.yao.tab.frame.MomentFrame;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Tab4Activity extends AppCompatActivity implements TabContentFactory {

    private static final String TAG = "Tab4Activity";

    private final Map<String, BaseFrame> mFrameMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tab_4);

        TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup(); // 对TabHost进行初始化
        LayoutInflater inflater = LayoutInflater.from(this); // 这里的LayoutInflater类型的变量是用于加载布局的
        inflater.inflate(R.layout.tab_item_1, tabHost.getTabContentView());
        inflater.inflate(R.layout.tab_item_1, tabHost.getTabContentView());
        // 以上调用它的inflate()方法来加载布局,第一个参数就是要加载的布局id，第二个参数是指给该布局的外部再嵌套一层父布局,如果不需要就传入null.这里我们需要把布局文件添加到FrameLayout(TabContent)中,所以利用getTabContentView()方法来传入.
        tabHost.addTab(tabHost.newTabSpec(ChatFrame.class.getName()).setIndicator("消息").setContent(tabContentFactory));
        tabHost.addTab(tabHost.newTabSpec(ContactFrame.class.getName()).setIndicator("联系人").setContent(tabContentFactory));
        tabHost.addTab(tabHost.newTabSpec(MomentFrame.class.getName()).setIndicator("动态").setContent(tabContentFactory));
        //最后两句是向TabHost添加选项卡.还可以这样写
		    /*TabHost.TabSpec tabSpec = tabHost.newTabSpec("tab1");//给选项卡设置tag(标记)
        tabSpec.setIndicator("通知");, //设置选项卡的标签名
        tabSpec.setContent(R.id.left);//为选项卡设置指定的布局文件
        tabHost.addTab(tabSpec);//将次选项卡添加到TabHost中   */
    }

    @Override
    public View createTabContent(String tag) {
        return null;
    }

    private TabContentFactory tabContentFactory = new TabContentFactory() {
        @Override
        public View createTabContent(String tag) {
            if (Objects.equals(tag, ChatFrame.class.getName())) {
                ChatFrame chatFrame = new ChatFrame();
                chatFrame.onCreateView(getLayoutInflater());
                mFrameMap.put(ChatFrame.class.getSimpleName(), chatFrame);
                return chatFrame.getView();
            } else if (Objects.equals(tag, ContactFrame.class.getName())) {
                ContactFrame contactFrame = new ContactFrame();
                contactFrame.onCreateView(getLayoutInflater());
                mFrameMap.put(ContactFrame.class.getSimpleName(), contactFrame);
                return contactFrame.getView();
            } else if (Objects.equals(tag, MomentFrame.class.getName())) {
                MomentFrame momentFrame = new MomentFrame();
                momentFrame.onCreateView(getLayoutInflater());
                mFrameMap.put(MomentFrame.class.getSimpleName(), momentFrame);
                return momentFrame.getView();
            } else {
                throw new IllegalStateException("TabContentFactory createTabContent tag is illegal");
            }
        }
    };
}
