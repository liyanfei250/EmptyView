package com.sungrowpower.kit.empty;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.sungrowpower.kit.R;
import com.sungrowpower.kit.SGKit;

public class EmptyDataBean {
  /**
   * 默认的一级文字大小
   */
  public final float titleSize = SGKit.INSTANCE.getResource().getDimensionPixelSize(R.dimen.sgkit_textSize_17);
  /**
   * 默认的二级文字大小
   */
  public final float textSize = SGKit.INSTANCE.getResource().getDimensionPixelSize(R.dimen.sgkit_textSize_14);
  /**
   * 默认的按钮文字大小
   */
  public final float buttonTextSize = SGKit.INSTANCE.getResource().getDimensionPixelSize(R.dimen.sgkit_textSize_16);

  /**
   * 是否显示顶部图片
   */
  public Boolean hasTopDrawable = mDefaultStyle != null ? mDefaultStyle.hasTopDrawable : true;

  /**
   * 是否显示一级文字
   */
  public Boolean hasTitleView = mDefaultStyle != null ? mDefaultStyle.hasTitleView : true;

  /**
   * 是否显示二级文字
   */
  public Boolean hasTextView = mDefaultStyle != null ? mDefaultStyle.hasTextView : true;

  /**
   * 默认背景颜色
   */
  public int mBackColor = mDefaultStyle != null ? mDefaultStyle.mBackColor : EmptyColorMap.getInstance().mBackColor;

  /**
   * 顶部图片
   */
  @Nullable
  public Drawable mTopBackDrawable = mDefaultStyle != null ? mDefaultStyle.mTopBackDrawable : null;

  /**
   * 头部标题颜色
   */
  public int mTitleColor = mDefaultStyle != null ? mDefaultStyle.mTitleColor : EmptyColorMap.getInstance().mTitleColor;
  /**
   * 头部标题
   */
  public CharSequence mTitleContent = mDefaultStyle != null ? mDefaultStyle.mTitleContent : "";

  /**
   * 头部标题文字大小
   */

  public float mTitleSize = mDefaultStyle != null ? mDefaultStyle.mTitleSize : titleSize;
  /**
   * 底部标题
   */
  public CharSequence mTextContent = mDefaultStyle != null ? mDefaultStyle.mTextContent : "";
  /**
   * 底部标题颜色
   */
  public int mTextColor = mDefaultStyle != null ? mDefaultStyle.mTextColor : EmptyColorMap.getInstance().mTextColor;
  /**
   * 底部标题大小
   */
  public float mTextSize = mDefaultStyle != null ? mDefaultStyle.mTextSize : textSize;

  /**
   * 是否有底部的按钮
   */
  public Boolean hasButtonView = mDefaultStyle != null ? mDefaultStyle.hasButtonView : true;
  /**
   * 底部按钮文字
   */
  public CharSequence mButtonContent = mDefaultStyle != null ? mDefaultStyle.mButtonContent : "";
  /**
   * 底部按钮文字颜色
   */
  public int mButtonTextColor = mDefaultStyle != null ? mDefaultStyle.mButtonTextColor : EmptyColorMap.getInstance().mButtonTextColor;
  /**
   * 底部按钮文字大小
   */
  public float mButtonTextSize = mDefaultStyle != null ? mDefaultStyle.mButtonTextSize : buttonTextSize;
  /**
   * 底部按钮背景颜色
   */
  public int mButtonBack = mDefaultStyle != null ? mDefaultStyle.mButtonBack : EmptyColorMap.getInstance().mButtonBackColor;
  /**
   * 底部按钮宽度
   */
  public int buttonWidth = mDefaultStyle != null ? mDefaultStyle.buttonWidth : EmptyColorMap.getInstance().buttonWidth;
  /**
   * 底部按钮高度
   */
  public int buttonHeight = mDefaultStyle != null ? mDefaultStyle.buttonHeight : EmptyColorMap.getInstance().buttonHeight;
  /**
   * 图片离顶部距离
   */
  public int emptyDrawableMarginTop = mDefaultStyle != null ? mDefaultStyle.emptyDrawableMarginTop : EmptyColorMap.getInstance().emptyDrawableMarginTop;
  /**
   * 一级标题离顶部的距离
   */
  public int titleTextMarginTop = mDefaultStyle != null ? mDefaultStyle.titleTextMarginTop : EmptyColorMap.getInstance().titleTextMarginTop;
  /**
   * 二级标题离顶部的距离
   */
  public int textButtonMarginTop = mDefaultStyle != null ? mDefaultStyle.textMarginTop : EmptyColorMap.getInstance().textButtonMarginTop;
  /**
   * 按钮离顶部的距离
   */
  public int buttonMarginTop = mDefaultStyle != null ? mDefaultStyle.buttonMarginTop : EmptyColorMap.getInstance().buttonMarginTop;
  /**
   * 按钮的圆角弧度
   */
  public int mButtonRadius = mDefaultStyle != null ? mDefaultStyle.mButtonRadius : EmptyColorMap.getInstance().mButtonRadius;
  /**
   * 按钮文字顶部内边距
   */
  public int mButtonTextPaddingTop = mDefaultStyle != null ? mDefaultStyle.mButtonTextPaddingTop : EmptyColorMap.getInstance().mButtonTextPaddingTop;
  /**
   * 按钮文字左边内边距
   */
  public int mButtonTextPaddingLeft = mDefaultStyle != null ? mDefaultStyle.mButtonTextPaddingLeft : EmptyColorMap.getInstance().mButtonTextPaddingLeft;
  /**
   * 按钮文字右侧内边距
   */
  public int mButtonTextPaddingRight = mDefaultStyle != null ? mDefaultStyle.mButtonTextPaddingRight : EmptyColorMap.getInstance().mButtonTextPaddingRight;
  /**
   * 按钮文字底部内边距
   */
  public int mButtonTextPaddingBottom = mDefaultStyle != null ? mDefaultStyle.mButtonTextPaddingBottom : EmptyColorMap.getInstance().mButtonTextPaddingBottom;
  /**
   * 图片宽
   */
  public int imageWidth = mDefaultStyle != null ? mDefaultStyle.imageWidth : EmptyColorMap.getInstance().imageWidth;
  /**
   * 图片高
   */
  public int imageHeight = mDefaultStyle != null ? mDefaultStyle.imageHeight : EmptyColorMap.getInstance().imageHeight;
  /**
   * 图片显示类型
   */
  public ImageView.ScaleType imageScaleType = null;
  public static EmptyDataBean emptyDataBean = new EmptyDataBean();

  /**
   * 设置全局默认的数据
   */
  @SuppressLint("StaticFieldLeak")
  private static EmptyViewBuilder mDefaultStyle = null;

  public static void setDefaultStyle(EmptyViewBuilder view) {
    mDefaultStyle = view;
    emptyDataBean = new EmptyDataBean();
  }
}
