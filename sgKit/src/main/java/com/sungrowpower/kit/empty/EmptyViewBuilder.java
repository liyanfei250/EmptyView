package com.sungrowpower.kit.empty;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import com.sungrowpower.kit.R;
import com.sungrowpower.kit.button.ButtonDataBean;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class EmptyViewBuilder {

  public static final int CENTER = 17;

  public static final int ERROR = 1;
  public static final int NODATE_LIST = 2;

  public static final int NODATE_SEARCH = 3;
  @State int state = -1;
  private final SGEmptyView emptyView;
  private final Context context;
  List<View> children;
  int gravity;

  /**
   * 显示的类型
   */
  int showType = -1;
  /**
   * 一级文字大小
   */
  float titleTextSize /*= EmptyDataBean.emptyDataBean.mTitleSize*/;
  /**
   * 二级文字大小
   */
  float textSize /*= EmptyDataBean.emptyDataBean.mTextSize*/;
  /**
   * 按钮文字大小
   */
  float buttonTextSize /*= EmptyDataBean.emptyDataBean.mButtonTextSize*/;
  /**
   * 字体
   */
  Typeface font;
  View.OnClickListener onClickListener;

  /**
   * 是否显示顶部图片
   */
  Boolean hasTopDrawable;

  /**
   * 是否显示一级文字
   */
  Boolean hasTitleView;

  /**
   * 是否显示二级文字
   */
  Boolean hasTextView;
  /**
   * 界面背景颜色
   */
  @ColorInt int mBackColor;
  /**
   * 顶部背景图片
   */
  Drawable mTopBackDrawable;

  /**
   * 头部标题
   */
  CharSequence mTitleContent;
  /**
   * 头部标题颜色
   */
  @ColorInt int mTitleColor;

  /**
   * 头部标题字体大小
   */
  float mTitleSize;

  /**
   * 底部标题
   */
  CharSequence mTextContent;
  /**
   * 底部标题颜色
   */
  @ColorInt int mTextColor;

  /**
   * 底部标题字体大小
   */
  float mTextSize;
  /**
   * 底部按钮是否显示
   */
  boolean hasButtonView;
  /**
   * 底部按钮文字
   */
  CharSequence mButtonContent;
  /**
   * 底部按钮文字颜色
   */
  int mButtonTextColor;
  /**
   * 底部按钮文字大小
   */
  float mButtonTextSize;
  /**
   * 底部按钮背景颜色
   */
  int mButtonBack;
  /**
   * 底部按钮宽度
   */
  int buttonWidth = -1;
  /**
   * 底部按钮高度
   */
  int buttonHeight = -1;
  /**
   * 图片离顶部距离
   */
  int emptyDrawableMarginTop = -1;
  /**
   * 一级标题离顶部的距离
   */
  int titleTextMarginTop = -1;
  /**
   * 二级标题离顶部的距离
   */
  int textMarginTop = -1;
  /**
   * 按钮离顶部的距离
   */
  int buttonMarginTop = -1;
  /**
   * 按钮的圆角弧度
   */
  int mButtonRadius = 30;
  /**
   * 按钮文字顶部内边距
   */
  int mButtonTextPaddingTop = 0;
  /**
   * 按钮文字左边内边距
   */
  int mButtonTextPaddingLeft = 0;
  /**
   * 按钮文字右侧内边距
   */
  int mButtonTextPaddingRight = 0;
  /**
   * 按钮文字底部内边距
   */
  int mButtonTextPaddingBottom = 0;
  /**
   * 图片宽
   */
  int imageWidth = -1;
  /**
   * 图片高
   */
  int imageHeight = -1;
  /**
   * 图片显示类型
   */
  ImageView.ScaleType imageScaleType = null;
  /**
   * 按钮实体类
   */
  ButtonDataBean buttonDataBean;

  private EmptyViewBuilder(SGEmptyView emptyView) {
    this.emptyView = emptyView;
    this.context = emptyView.getContext();
    this.children = new ArrayList<>();
  }

  EmptyViewBuilder(SGEmptyView emptyView, @NonNull AttributeSet attributeSet) {
    this(emptyView);

    TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.EmptyView);
    try {
      Resources resources = context.getResources();
      int defaultTextColor = resources.getColor(android.R.color.secondary_text_dark);
      gravity = a.getInt(R.styleable.EmptyView_ev_gravity, CENTER);
      if (a.hasValue(R.styleable.EmptyView_ev_empty_titleTextSize)) {
        titleTextSize = a.getDimension(R.styleable.EmptyView_ev_empty_titleTextSize, 0);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_empty_textSize)) {
        textSize = a.getDimension(R.styleable.EmptyView_ev_empty_textSize, 0);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_buttonTextSize)) {
        buttonTextSize = a.getDimension(R.styleable.EmptyView_ev_buttonTextSize, 0);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_buttonWidth)) {
        buttonWidth = a.getDimensionPixelSize(R.styleable.EmptyView_ev_buttonWidth, 0);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_buttonHeight)) {
        buttonHeight = a.getDimensionPixelSize(R.styleable.EmptyView_ev_buttonHeight, 0);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_buttonMarginTop)) {
        buttonMarginTop = a.getDimensionPixelSize(R.styleable.EmptyView_ev_buttonMarginTop, 0);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_emptyDrawableMarginTop)) {
        emptyDrawableMarginTop = a.getDimensionPixelSize(R.styleable.EmptyView_ev_emptyDrawableMarginTop, -1);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_titleTextMarginTop)) {
        titleTextMarginTop = a.getDimensionPixelSize(R.styleable.EmptyView_ev_titleTextMarginTop, -1);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_textButtonMarginTop)) {
        textMarginTop = a.getDimensionPixelSize(R.styleable.EmptyView_ev_textButtonMarginTop, -1);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_buttonRadius)) {
        mButtonRadius = a.getDimensionPixelSize(R.styleable.EmptyView_ev_buttonRadius, 0);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_buttonTextPaddingLeft)) {
        mButtonTextPaddingLeft = a.getDimensionPixelSize(R.styleable.EmptyView_ev_buttonTextPaddingLeft, 0);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_buttonTextPaddingTop)) {
        mButtonTextPaddingTop = a.getDimensionPixelSize(R.styleable.EmptyView_ev_buttonTextPaddingTop, 0);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_buttonTextPaddingRight)) {
        mButtonTextPaddingRight = a.getDimensionPixelSize(R.styleable.EmptyView_ev_buttonTextPaddingRight, 0);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_buttonTextPaddingBottom)) {
        mButtonTextPaddingBottom = a.getDimensionPixelSize(R.styleable.EmptyView_ev_buttonTextPaddingBottom, 0);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_imageWidth)) {
        imageWidth = a.getDimensionPixelSize(R.styleable.EmptyView_ev_imageWidth, -1);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_imageHeight)) {
        imageHeight = a.getDimensionPixelSize(R.styleable.EmptyView_ev_imageHeight, -1);
      }
      int scaleTypeIndex = a.getInt(R.styleable.EmptyView_ev_image_scaleType, -1);
      if (scaleTypeIndex >= 0 && scaleTypeIndex < ImageView.ScaleType.values().length) {
        imageScaleType = ImageView.ScaleType.values()[scaleTypeIndex];
      }
      if (a.hasValue(R.styleable.EmptyView_ev_show_type)) {
        showType = a.getInt(R.styleable.EmptyView_ev_show_type, -1);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_empty_title)) {
        mTitleContent = a.getText(R.styleable.EmptyView_ev_empty_title);
      }
      mTitleColor = a.getColor(R.styleable.EmptyView_ev_empty_titleTextColor,
          defaultTextColor);
      if (a.hasValue(R.styleable.EmptyView_ev_empty_text)) {
        mTextContent = a.getText(R.styleable.EmptyView_ev_empty_text);
      }
      mTextColor = a.getColor(R.styleable.EmptyView_ev_empty_textColor, defaultTextColor);
      if (a.hasValue(R.styleable.EmptyView_ev_empty_button)) {
        mButtonContent = a.getText(R.styleable.EmptyView_ev_empty_button);
      }
      mButtonTextColor = a.getColor(R.styleable.EmptyView_ev_empty_buttonTextColor,
          defaultTextColor);
      mButtonBack = a.getColor(R.styleable.EmptyView_ev_empty_buttonBackgroundColor,
          0);
      if (a.hasValue(R.styleable.EmptyView_ev_empty_drawable)) {
        mTopBackDrawable = a.getDrawable(R.styleable.EmptyView_ev_empty_drawable);
      }
      if (a.hasValue(R.styleable.EmptyView_ev_empty_backgroundColor)) {
        mBackColor = a.getColor(R.styleable.EmptyView_ev_empty_backgroundColor, 0);
      }
    } finally {
      a.recycle();
    }
  }

  /**
   * 设置空布局状态
   */
  public EmptyViewBuilder setState(@State int state) {
    this.state = state;
    return this;
  }

  /**
   * 设置点击事件
   */
  public EmptyViewBuilder setOnClickListener(View.OnClickListener onClickListener) {
    this.onClickListener = onClickListener;
    return this;
  }

  /**
   * 设置一级文字内容
   */
  public EmptyViewBuilder setEmptyTitle(@StringRes int id) {
    return setEmptyTitle(EmptyUtils.getString(context, id));
  }

  /**
   * 设置一级文字内容
   */
  public EmptyViewBuilder setEmptyTitle(CharSequence emptyTitle) {
    this.mTitleContent = emptyTitle;
    return this;
  }

  /**
   * 设置一级文字颜色
   */
  public EmptyViewBuilder setTitleColor(int titleColor) {
    this.mTitleColor = titleColor;
    return this;
  }

  /**
   * 设置二级文字内容
   */
  public EmptyViewBuilder setEmptyText(@StringRes int id) {
    return setEmptyText(EmptyUtils.getString(context, id));
  }

  /**
   * 设置二级文字内容
   */
  public EmptyViewBuilder setEmptyText(CharSequence emptyText) {
    this.mTextContent = emptyText;
    return this;
  }

  /**
   * 设置按钮文字
   */
  public EmptyViewBuilder setButtonText(@StringRes int id) {
    return setButtonText(EmptyUtils.getString(context, id));
  }

  /**
   * 设置按钮文字
   */
  public EmptyViewBuilder setButtonText(CharSequence buttonText) {
    this.mButtonContent = buttonText;
    return this;
  }

  /**
   * 设置按钮文字颜色
   */
  public EmptyViewBuilder setButtonTextColor(int buttonTextColor) {
    this.mButtonTextColor = buttonTextColor;
    return this;
  }

  /**
   * 设置按钮文字大小
   */
  public EmptyViewBuilder setButtonTextSize(int buttonTextSize) {
    this.mButtonTextSize = buttonTextSize;
    return this;
  }

  /**
   * 设置按钮背景色
   */
  public EmptyViewBuilder setButtonBackColor(int buttonBack) {
    this.mButtonBack = buttonBack;
    return this;
  }

  /**
   * 设置按钮实体类
   */
  public EmptyViewBuilder setButtonBean(ButtonDataBean buttonDataBean) {
    this.buttonDataBean = buttonDataBean;
    return this;
  }

  /**
   * 设置顶部图片
   */
  public EmptyViewBuilder setEmptyDrawable(@DrawableRes int id) {
    return setEmptyDrawable(EmptyUtils.getDrawable(context, id));
  }

  /**
   * 设置顶部图片
   */
  public EmptyViewBuilder setEmptyDrawable(Drawable emptyDrawable) {
    this.mTopBackDrawable = emptyDrawable;
    return this;
  }

  /**
   * 设置按钮宽度
   */
  public EmptyViewBuilder setButtonWidth(int width) {
    this.buttonWidth = width;
    return this;
  }

  /**
   * 设置按钮高度
   */
  public EmptyViewBuilder setButtonHeight(int height) {
    this.buttonHeight = height;
    return this;
  }

  /**
   * 设置图片到顶部的距离
   */
  public EmptyViewBuilder setEmptyDrawableMarginTop(int marginTop) {
    this.emptyDrawableMarginTop = marginTop;
    return this;
  }

  /**
   * 设置一级文字到顶部的距离
   */
  public EmptyViewBuilder setTitleTextMarginTop(int marginTop) {
    this.titleTextMarginTop = marginTop;
    return this;
  }

  /**
   * 设置二级文字到顶部的距离
   */
  public EmptyViewBuilder setTextMarginTop(int marginTop) {
    this.textMarginTop = marginTop;
    return this;
  }

  /**
   * 设置按钮距离底部的距离
   */
  public EmptyViewBuilder setButtonMarginTop(int marginTop) {
    this.buttonMarginTop = marginTop;
    return this;
  }

  /**
   * 设置按钮文字距离顶部的距离
   */
  public EmptyViewBuilder setButtonTextPaddingTop(int paddingTop) {
    this.mButtonTextPaddingTop = paddingTop;
    return this;
  }

  /**
   * 设置按钮圆角弧度
   */
  public EmptyViewBuilder setButtonRadius(int buttonRadius) {
    this.mButtonRadius = buttonRadius;
    return this;
  }

  /**
   * 设置按钮文字距离左侧的距离
   */
  public EmptyViewBuilder setButtonTextPaddingLeft(int paddingLeft) {
    this.mButtonTextPaddingLeft = paddingLeft;
    return this;
  }

  /**
   * 设置按钮文字距离右部的距离
   */
  public EmptyViewBuilder setButtonTextPaddingRight(int paddingRight) {
    this.mButtonTextPaddingRight = paddingRight;
    return this;
  }

  /**
   * 设置按钮文字距离底部的距离
   */
  public EmptyViewBuilder setButtonTextPaddingBottom(int paddingBottom) {
    this.mButtonTextPaddingBottom = paddingBottom;
    return this;
  }

  /**
   * 设置图片宽度值
   */
  public EmptyViewBuilder setImageWidth(int width) {
    this.imageWidth = width;
    return this;
  }

  /**
   * 设置图片高度值
   */
  public EmptyViewBuilder setImageHeight(int height) {
    this.imageHeight = height;
    return this;
  }

  /**
   * 设置图片格式
   */
  public EmptyViewBuilder setImageScaleType(ImageView.ScaleType scaleType) {
    this.imageScaleType = scaleType;
    return this;
  }

  /**
   * 显示空布局
   */
  public void show() {
    emptyView.show();
  }

  /**
   * 设置全局默认的数据
   */
  public static void setDefaultStyle(EmptyViewBuilder builder) {
    EmptyDataBean.setDefaultStyle(builder);
  }

  @IntDef({ ERROR, NODATE_LIST, NODATE_SEARCH })
  @Retention(RetentionPolicy.SOURCE)
  public @interface State {
  }
}

