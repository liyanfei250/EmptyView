package com.sungrowpower.tabbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.sungrowpower.tabbar.databinding.ActivityTabBarBinding;


import com.sungrowpower.tabbar.listener.OnTabSelectListener;

import java.util.ArrayList;

public class TabBarActivity extends AppCompatActivity {

    private String[] mTitles = {"首页", "消息", "联系人", "更多"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};

    ActivityTabBarBinding barBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_bar);
        barBinding = DataBindingUtil.setContentView(this, R.layout.activity_tab_bar);
        initDate();
    }

    private void initDate() {
        ArrayList<TabItem> mTabEntities1 = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities1.add(new TabItem(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i], "message.json", TabItem.NORMAL));
        }
        barBinding.tb1.setTabData(mTabEntities1);

        ArrayList<TabItem> mTabEntities11 = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities11.add(new TabItem(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i],  TabItem.NORMAL));
        }
        barBinding.tb11.setTabData(mTabEntities11);
        barBinding.tb11.setCurrentTab(1);


        ArrayList<TabItem> mTabEntities2 = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities2.add(new TabItem(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i], TabItem.NORMAL));
        }
        barBinding.tb2.setTabData(mTabEntities2);


        ArrayList<TabItem> mTabEntities3 = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities3.add(new TabItem(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i], TabItem.NORMAL));
        }
        barBinding.tb3.setTabData(mTabEntities3);

        ArrayList<TabItem> mTabEntities4 = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities4.add(new TabItem(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i], TabItem.NORMAL));
        }
        barBinding.tb4.setTabData(mTabEntities4);
        //设置未读红点
        barBinding.tb4.showBadge(0);
        //设置两位数
        barBinding.tb4.showBadge(1, 55);
        barBinding.tb4.setBadgeMargin(1, 0, 5);
        //设置三位数
        barBinding.tb4.showBadge(2, 200,155);
        barBinding.tb4.setBadgeMargin(2, -15, 5);
        //设置红点位置
        barBinding.tb4.showBadge(3, 100);
        barBinding.tb4.setBadgeMargin(3, 5, 5);


        ArrayList<TabItem> mTabEntities5 = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {

            mTabEntities5.add(new TabItem(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i], TabItem.NORMAL));
        }
        barBinding.tb5.setTabData(mTabEntities5);

        ArrayList<TabItem> mTabEntities51 = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {

            mTabEntities51.add(new TabItem(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i], TabItem.NORMAL));
        }
        barBinding.tb51.setTabData(mTabEntities51);


        ArrayList<TabItem> mTabEntities6 = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities6.add(new TabItem(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i], TabItem.NORMAL));
        }
        mTabEntities6.add(2, new TabItem(mTitles[0], mIconSelectIds[0], mIconUnselectIds[0], TabItem.MIDDLE));

        barBinding.tb6.setTabData(mTabEntities6);

        barBinding.tb6.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(TabItem item) {
                Toast.makeText(TabBarActivity.this,item.getTabTitle(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabreleased(TabItem item) {

            }
        });



    }
}