package com.sungrowpower.kit.empty;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import org.json.JSONObject;

public class EmptyColorMap {

  /**
   * 背景的颜色
   */
  public int mBackColor = -1;
  /**
   * 顶部图片
   */
  public Drawable mTopBackDrawable;

  /**
   * 头部标题颜色
   */
  public int mTitleColor = -1;
  /**
   * 底部标题颜色
   */
  public int mTextColor = -1;
  /**
   * 底部按钮文字颜色
   */
  public int mButtonTextColor = -1;
  /**
   * 底部按钮背景颜色
   */
  public int mButtonBackColor = -1;

  /**
   * 底部按钮宽度
   */
  public int buttonWidth = -1;
  /**
   * 底部按钮高度
   */
  public int buttonHeight = -1;
  /**
   * 图片离顶部距离
   */
  public int emptyDrawableMarginTop = 0;
  /**
   * 一级标题离顶部的距离
   */
  public int titleTextMarginTop = 0;
  /**
   * 二级标题离顶部的距离
   */
  public int textButtonMarginTop = 0;
  /**
   * 按钮离顶部的距离
   */
  public int buttonMarginTop = 0;
  /**
   * 按钮的圆角弧度
   */
  public int mButtonRadius = 0;
  /**
   * 按钮文字顶部内边距
   */
  public int mButtonTextPaddingTop = 0;
  /**
   * 按钮文字左边内边距
   */
  public int mButtonTextPaddingLeft = 0;
  /**
   * 按钮文字右侧内边距
   */
  public int mButtonTextPaddingRight = 0;
  /**
   * 按钮文字底部内边距
   */
  public int mButtonTextPaddingBottom = 0;
  /**
   * 图片宽
   */
  public int imageWidth = 0;
  /**
   * 图片高
   */
  public int imageHeight = 0;
  /**
   * 图片显示类型
   */
  public ImageView.ScaleType imageScaleType = null;

  /**
   * 是否有底部的按钮
   */
  public boolean hasFooterView = true;

  /** 私有化构建函数 */
  private EmptyColorMap() {
  }

  /** 通过静态内部类获取实例 */
  private static class SingleHolder {
    private static final EmptyColorMap INSTANCE = new EmptyColorMap();
  }

  public static EmptyColorMap getInstance() {
    return SingleHolder.INSTANCE;
  }

  public static void initJson(String content) {
    try {
      JSONObject jsonObject = new JSONObject(content);
    } catch (Exception e) {
    }
  }
}
