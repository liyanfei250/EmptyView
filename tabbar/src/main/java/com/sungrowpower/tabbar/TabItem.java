package com.sungrowpower.tabbar;

import android.text.TextUtils;
import android.view.View;


public class TabItem {
    public static final int  NORMAL=0;
    public static final int  MIDDLE=1;
    public String title;
    public int selectedIcon;
    public int unSelectedIcon;
     public int tabType=0;//0是正常。1是中间
    public String lottieSource;
    private View tabView;
    private int tabPosition;
    public TabItem(String title, int selectedIcon, int unSelectedIcon, int tabType) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
        this.tabType = tabType;
    }
    public TabItem(String title, int selectedIcon, int unSelectedIcon, String lottieSource , int tabType) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
        this.tabType = tabType;
        this.lottieSource=lottieSource;
    }


    public void setTabView(View tabView) {
        this.tabView = tabView;
    }

    public View getTabView() {
        return tabView;
    }

    public void setTabPosition(int tabPosition) {
        this.tabPosition = tabPosition;
    }

    public int getTabPosition() {
        return tabPosition;
    }

    public String getTabTitle() {
        return title;
    }


    public int getTabSelectedIcon() {
        return selectedIcon;
    }


    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }


    public String getLottieSource() {
        return lottieSource;
    }


    public boolean hasLottie() {
        return !TextUtils.isEmpty(lottieSource);
    }


    public int getTabType() {
        return tabType;
    }

    public void setTabType(int tabType) {
        this.tabType = tabType;
    }
}
