package com.sungrowpower.kit.empty;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import com.sungrowpower.kit.R;
import com.sungrowpower.kit.button.ButtonDataBean;
import com.sungrowpower.kit.button.SGButton;

import static com.sungrowpower.kit.util.SGDisplayHelper.dpToPx;

public class SGEmptyView extends LinearLayout {
  /**
   * 网络问题布局显示
   */
  public static final int NETWORK_ERROR = 1;
  /**
   * 列表无数据布局显示
   */
  public static final int NODATA_LIST = 2;
  /**
   * 搜索无数据布局显示
   */
  public static final int NODATA_SEARCH = 3;

  /**
   * 辅助类
   */
  private EmptyViewBuilder builder;
  /**
   * 空布局背景
   */
  private LinearLayout emptyLayout;
  /**
   * 空布局图片
   */
  private ImageView emptyImage;
  /**
   * 空布局一级文字
   */
  private TextView emptyTitle;
  /**
   * 空布局二级文字
   */
  private TextView emptyText;
  /**
   * 空布局底部按钮
   */
  private SGButton emptyButton;

  /**
   * 空布局实体类
   */
  private EmptyDataBean emptyDataBean;

  public SGEmptyView(Context context) {
    super(context);
    init();
  }

  public SGEmptyView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    builder = new EmptyViewBuilder(this, attrs);
    init();
  }

  public SGEmptyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    builder = new EmptyViewBuilder(this, attrs);
    init();
  }

  private void init() {
    inflate(getContext(), R.layout.empty_view, this);
    emptyLayout = findViewById(R.id.empty_layout);
    emptyImage = findViewById(R.id.empty_image);
    emptyTitle = findViewById(R.id.empty_title);
    emptyText = findViewById(R.id.empty_text);
    emptyButton = findViewById(R.id.empty_button);
    /**
     * 如果设置的显示的类型，就走这里
     * */
    if (builder.showType != -1) {
      if (builder.showType == NETWORK_ERROR) {
        emptyLayout.setVisibility(View.VISIBLE);
        emptyImage.setVisibility(VISIBLE);
        emptyTitle.setVisibility(TextUtils.isEmpty(builder.mTitleContent) ? GONE : VISIBLE);
        emptyText.setVisibility(TextUtils.isEmpty(builder.mTextContent) ? GONE : VISIBLE);
        emptyButton.setVisibility(TextUtils.isEmpty(builder.mButtonContent) ? GONE : VISIBLE);
        setContainer(builder.mBackColor);
        setIcon(getResources().getDrawable(R.drawable.sgkit_no_net));
        setTitle(builder.mTitleContent, builder.mTitleColor);
        int buttonTextColor = builder.mButtonTextColor;
        CharSequence buttonText = builder.mButtonContent;
        emptyButton.setText(buttonText);
        emptyButton.setTextColor(buttonTextColor);
        if (builder.mButtonTextSize != 0) {
          EmptyUtils.setTextSize(emptyButton, builder.mButtonTextSize);
        }

        emptyButton.setRadius(builder.mButtonRadius).setSolidColor(builder.mButtonBack).build();

        // 设置padding
        emptyButton.setPadding(builder.mButtonTextPaddingLeft, builder.mButtonTextPaddingTop, builder.mButtonTextPaddingRight, builder.mButtonTextPaddingBottom);

        if (builder.buttonWidth != -1) {
          ViewGroup.LayoutParams layoutParams = emptyButton.getLayoutParams();
          layoutParams.width = builder.buttonWidth;
          emptyButton.setLayoutParams(layoutParams);
        }
        if (builder.buttonHeight != -1) {
          ViewGroup.LayoutParams layoutParams = emptyButton.getLayoutParams();
          layoutParams.height = builder.buttonHeight;
          emptyButton.setLayoutParams(layoutParams);
        }
        if (builder.buttonMarginTop != -1) {
          LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) emptyButton.getLayoutParams();
          layoutParams.topMargin = builder.buttonMarginTop;
          emptyButton.setLayoutParams(layoutParams);
        }
        setAttrs();
        emptyButton.setOnClickListener(builder.onClickListener);
      }
      if (builder.showType == NODATA_LIST) {
        showNadataList();
      }
      if (builder.showType == NODATA_SEARCH) {
        showNadataSearch();
      }
    } else {
      setAttrs();
    }
  }

  private void setAttrs() {

    /**
     * 设置背景颜色
     * */
    if (builder.mBackColor != 0) {
      emptyLayout.setBackgroundColor(builder.mBackColor);
    }
    /**
     * 设置头像
     * */
    final Drawable mTopBackDrawable = builder.mTopBackDrawable;
    if (mTopBackDrawable != null) {
      emptyImage.setImageDrawable(mTopBackDrawable);
    }

    /**
     * 设置一级文字
     * */
    final CharSequence titleText = builder.mTitleContent;
    if (titleText != null) {
      emptyTitle.setText(titleText);
    }
    /**
     * 设置一级文字颜色
     * */
    if (builder.mTitleColor != 0) {
      emptyTitle.setTextColor(builder.mTitleColor);
    }
    /**
     * 设置一级文字大小
     * */
    if (builder.titleTextSize != 0) {
      EmptyUtils.setTextSize(emptyTitle, builder.titleTextSize);
    }

    /**
     * 设置二级文字
     * */
    final CharSequence textStr = builder.mTextContent;
    if (textStr != null) {
      emptyText.setText(textStr);
    }
    /**
     * 设置二级文字颜色
     * */
    if (builder.mTextColor != 0) {
      emptyText.setTextColor(builder.mTextColor);
    }

    /**
     * 设置二级文字大小
     * */
    if (builder.textSize != 0) {
      EmptyUtils.setTextSize(emptyText, builder.textSize);
    }
    /**
     * 设置按钮文字
     * */
    final CharSequence buttonText = builder.mButtonContent;
    if (buttonText != null) {
      emptyButton.setVisibility(VISIBLE);
      emptyButton.setText(buttonText);
    }
    /**
     * 设置按钮文字颜色
     * */
    int buttonTextColor = builder.mButtonTextColor;
    emptyButton.setTextColor(buttonTextColor);
    /**
     * 设置按钮文字大小
     * */
    if (builder.buttonTextSize != 0) {
      EmptyUtils.setTextSize(emptyButton, builder.buttonTextSize);
    }

    /**
     * 设置背景填充色
     * */
    if (builder.mButtonBack != 0) {
      emptyButton.setSolidColor(builder.mButtonBack).build();
    }
    /**
     * 设置按钮的宽度
     * */
    if (builder.buttonWidth != -1) {
      ViewGroup.LayoutParams layoutParams = emptyButton.getLayoutParams();
      layoutParams.width = builder.buttonWidth;
      emptyButton.setLayoutParams(layoutParams);
    }
    /**
     * 设置按钮的高度
     * */
    if (builder.buttonHeight != -1) {
      ViewGroup.LayoutParams layoutParams = emptyButton.getLayoutParams();
      layoutParams.height = builder.buttonHeight;
      emptyButton.setLayoutParams(layoutParams);
    }
    /**
     * 设置按钮距离顶部的距离
     * */

    if (builder.buttonMarginTop != -1) {
      LayoutParams layoutParams = (LayoutParams) emptyButton.getLayoutParams();
      layoutParams.topMargin = builder.buttonMarginTop;
      emptyButton.setLayoutParams(layoutParams);
    }
    /**
     * 设置图片距离顶部的距离
     * */
    if (builder.emptyDrawableMarginTop != -1) {
      LayoutParams layoutParams = (LayoutParams) emptyImage.getLayoutParams();
      layoutParams.topMargin = builder.emptyDrawableMarginTop;
      emptyImage.setLayoutParams(layoutParams);
    }
    /**
     * 设置一级文字距离顶部的距离
     * */
    if (builder.titleTextMarginTop != -1) {
      LayoutParams layoutParams = (LayoutParams) emptyTitle.getLayoutParams();
      layoutParams.topMargin = builder.titleTextMarginTop;
      emptyTitle.setLayoutParams(layoutParams);
    }
    /**
     * 设置二级文字距离顶部的距离
     * */
    if (builder.textMarginTop != -1) {
      LayoutParams layoutParams = (LayoutParams) emptyText.getLayoutParams();
      layoutParams.topMargin = builder.textMarginTop;
      emptyText.setLayoutParams(layoutParams);
    }
    /**
     * 设置图片的宽度
     * */
    if (builder.imageWidth != -1) {
      LayoutParams layoutParams = (LayoutParams) emptyImage.getLayoutParams();
      layoutParams.width = builder.imageWidth;
      emptyImage.setLayoutParams(layoutParams);
    }
    /**
     * 设置图片的高度
     * */
    if (builder.imageHeight != -1) {
      LayoutParams layoutParams = (LayoutParams) emptyImage.getLayoutParams();
      layoutParams.height = builder.imageHeight;
      emptyImage.setLayoutParams(layoutParams);
    }
    /**
     * 设置图片的类型
     * */

    if (builder.imageScaleType != null) {
      emptyImage.setScaleType(builder.imageScaleType);
    }

    /**
     * 设置按钮圆角和背景色
     * */
    emptyButton.setRadius(builder.mButtonRadius).build();

    /**
     *设置padding
     * */
    emptyButton.setPadding(builder.mButtonTextPaddingLeft, builder.mButtonTextPaddingTop, builder.mButtonTextPaddingRight, builder.mButtonTextPaddingBottom);
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
  }

  /**
   * 设置空布局类型
   */
  public void show() {
    switch (builder.state) {
      case NETWORK_ERROR:
        showNetworkError();
        break;
      case NODATA_LIST:
        showNadataList();
        break;
      case NODATA_SEARCH:
        showNadataSearch();
        break;
      default:
        break;
    }
  }

  /**
   * 显示默认空布局
   */
  private void showDefault() {
    emptyLayout.setVisibility(View.VISIBLE);
    emptyImage.setVisibility(VISIBLE);
    emptyTitle.setVisibility(VISIBLE);
    emptyText.setVisibility(VISIBLE);
    emptyButton.setVisibility(GONE);
    setIcon(getResources().getDrawable(R.drawable.sgkit_no_data));
    setTitle("No Data", builder.mTitleColor);
  }

  /**
   * 显示网络问题空布局
   */
  private void showNetworkError() {
    emptyLayout.setVisibility(View.VISIBLE);
    emptyImage.setVisibility(VISIBLE);
    emptyTitle.setVisibility(TextUtils.isEmpty(builder.mTitleContent) ? GONE : VISIBLE);
    emptyText.setVisibility(TextUtils.isEmpty(builder.mTextContent) ? GONE : VISIBLE);
    emptyButton.setVisibility(TextUtils.isEmpty(builder.mButtonContent) ? GONE : VISIBLE);
    setContainer(builder.mBackColor);
    setIcon(getResources().getDrawable(R.drawable.sgkit_no_net));
    setTitle(builder.mTitleContent, builder.mTitleColor);
    //setButtonStyle();
    setAttrs();
    emptyButton.setOnClickListener(builder.onClickListener);
  }

  /**
   * 显示列表无数据空布局
   */
  private void showNadataList() {
    emptyLayout.setVisibility(View.VISIBLE);
    emptyImage.setVisibility(VISIBLE);
    emptyTitle.setVisibility(VISIBLE);
    emptyText.setVisibility(VISIBLE);
    emptyButton.setVisibility(TextUtils.isEmpty(builder.mButtonContent) ? GONE : VISIBLE);
    setContainer(builder.mBackColor);
    setIcon(getResources().getDrawable(R.drawable.sgkit_no_data));
    setTitle(builder.mTitleContent, builder.mTitleColor);
    setText(builder.mTextContent, builder.mTextColor);
    setButtonStyle();
    emptyButton.setOnClickListener(builder.onClickListener);
  }

  /**
   * 显示搜索无数据空布局
   */
  private void showNadataSearch() {
    emptyLayout.setVisibility(View.VISIBLE);
    emptyImage.setVisibility(VISIBLE);
    emptyTitle.setVisibility(VISIBLE);
    emptyText.setVisibility(VISIBLE);
    emptyButton.setVisibility(TextUtils.isEmpty(builder.mButtonContent) ? GONE : VISIBLE);
    setContainer(builder.mBackColor);
    setIcon(getResources().getDrawable(R.drawable.sgkit_no_data));
    setTitle(builder.mTitleContent, builder.mTitleColor);
    setText(builder.mTextContent, builder.mTextColor);
    setButtonStyle();
    emptyButton.setOnClickListener(builder.onClickListener);
  }

  /**
   * 设置网络问题空布局
   */
  public EmptyViewBuilder error() {
    return builder.setState(EmptyViewBuilder.ERROR);
  }

  /**
   * 设置列表无数据空布局
   */
  public EmptyViewBuilder empty_list() {
    return builder.setState(EmptyViewBuilder.NODATE_LIST);
  }

  /**
   * 设置搜索无数据空布局
   */
  public EmptyViewBuilder empty_search() {
    return builder.setState(EmptyViewBuilder.NODATE_SEARCH);
  }

  /**
   * 获取辅助类
   */
  public EmptyViewBuilder builder() {
    return builder;
  }

  /**
   * 拿到按钮实例
   */
  public SGButton getButton() {
    return emptyButton;
  }

  /**
   * 设置按钮信息
   */
  public void setButtonData(ButtonDataBean data) {
    emptyButton.setButtonData(data);
  }

  /**
   * 设置空布局实体类
   */
  public SGEmptyView setEmptyData(EmptyDataBean data) {
    emptyDataBean = data;
    setEmptyView(emptyDataBean);
    return this;
  }

  /**
   * 设置空布局实体类
   */
  private void setEmptyView(EmptyDataBean emptyDataBean) {
    emptyLayout.setVisibility(View.VISIBLE);
    emptyImage.setVisibility(emptyDataBean.hasTopDrawable ? VISIBLE : GONE);
    emptyTitle.setVisibility(emptyDataBean.hasTitleView ? VISIBLE : GONE);
    emptyText.setVisibility(emptyDataBean.hasTextView ? VISIBLE : GONE);
    emptyButton.setVisibility(emptyDataBean.hasButtonView ? VISIBLE : GONE);
    setContainer(emptyDataBean.mBackColor);
    setIcon(getResources().getDrawable(R.drawable.sgkit_no_data));
    setTitle(emptyDataBean.mTitleContent, emptyDataBean.mTitleColor);
    setText(emptyDataBean.mTextContent, emptyDataBean.mTextColor);
    emptyButton.setOnClickListener(builder.onClickListener);
  }

  @Override
  public void setVisibility(int visibility) {
    super.setVisibility(visibility);
  }

  /**
   * 设置空布局背景颜色
   */
  private void setContainer(@ColorInt int backgroundColor) {
    emptyLayout.setGravity(builder.gravity);
    emptyLayout.setBackgroundColor(backgroundColor);
  }

  /**
   * 设置空布局图片
   */
  private void setIcon(Drawable drawable) {
    if (emptyImage.getVisibility() != VISIBLE) {
      return;
    }
    if (drawable == null) {
      emptyImage.setVisibility(GONE);
      return;
    }
    emptyImage.setVisibility(VISIBLE);
    emptyImage.setImageDrawable(drawable);
  }

  /**
   * 设置空布局一级文字
   */
  private void setTitle(CharSequence text, @ColorInt int textColor) {
    if (emptyTitle.getVisibility() != VISIBLE) {
      return;
    }
    if (TextUtils.isEmpty(text)) {
      emptyTitle.setVisibility(GONE);
      return;
    }
    emptyTitle.setVisibility(VISIBLE);
    emptyTitle.setText(text);
    emptyTitle.setTextColor(textColor);
    emptyTitle.setTypeface(builder.font);
    if (builder.titleTextSize != 0) {
      EmptyUtils.setTextSize(emptyTitle, builder.titleTextSize);
    }
  }

  /**
   * 设置空布局二级文字
   */
  private void setText(CharSequence text, @ColorInt int textColor) {
    if (emptyText.getVisibility() != VISIBLE) {
      return;
    }
    if (TextUtils.isEmpty(text)) {
      emptyText.setVisibility(GONE);
      return;
    }
    emptyText.setVisibility(VISIBLE);
    emptyText.setText(text);
    emptyText.setTextColor(textColor);
    emptyText.setTypeface(builder.font);
    if (builder.textSize != 0) {
      EmptyUtils.setTextSize(emptyText, builder.textSize);
    }
  }

  /**
   * 设置空布局按钮
   */
  private void setButtonStyle() {
    int buttonTextColor = builder.mButtonTextColor;
    CharSequence buttonText = builder.mButtonContent;
    emptyButton.setText(buttonText);
    emptyButton.setTextColor(buttonTextColor);
    if (builder.mButtonTextSize != 0) {
      EmptyUtils.setTextSize(emptyButton, builder.mButtonTextSize);
    }

    emptyButton.setRadius(builder.mButtonRadius).setSolidColor(builder.mButtonBack).build();

    emptyButton.setPadding(builder.mButtonTextPaddingLeft, builder.mButtonTextPaddingTop, builder.mButtonTextPaddingRight, builder.mButtonTextPaddingBottom);

    if (builder.buttonWidth != -1) {
      ViewGroup.LayoutParams layoutParams = emptyButton.getLayoutParams();
      layoutParams.width = builder.buttonWidth;
      emptyButton.setLayoutParams(layoutParams);
    }

    if (builder.buttonHeight != -1) {
      ViewGroup.LayoutParams layoutParams = emptyButton.getLayoutParams();
      layoutParams.height = builder.buttonHeight;
      emptyButton.setLayoutParams(layoutParams);
    }

    if (builder.buttonMarginTop != -1) {
      LayoutParams layoutParams = (LayoutParams) emptyButton.getLayoutParams();
      layoutParams.topMargin = builder.buttonMarginTop;
      emptyButton.setLayoutParams(layoutParams);
    }
  }
}
