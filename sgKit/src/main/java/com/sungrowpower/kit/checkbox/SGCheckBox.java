package com.sungrowpower.kit.checkbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.sungrowpower.kit.R;
import com.sungrowpower.kit.fonticon.SGFontIcon;

/**
 * 自定义CheckBox组件
 *
 * @author liuyuan1
 * @date 2023/7/18
 */
public class SGCheckBox extends LinearLayout {

  private ConstraintLayout mLayout;
  private SGFontIcon mFIIcon;
  private TextView mTvText;
  /**
   * 展示模式  0:图标在左边、1:图标在右边  默认在左边
   */
  private int mMode;
  private String mText;
  /**
   * 文本大小
   */
  private int mTextSize;
  /**
   * 图标大小
   */
  private int mIconSize;
  /**
   * 未选中状态下图标
   */
  private String mUnSelectIcon;
  /**
   * 未选中状态下图标颜色
   */
  private int mUnSelectIconColor = getResources().getColor(R.color.sgkit_unselect);
  /**
   * 选中状态下图标
   */
  private String mSelectedIcon;
  /**
   * 选中状态下图标颜色
   */
  private int mSelectedIconColor = getResources().getColor(R.color.sgkit_brand_routine);

  public SGCheckBox(@NonNull Context context) {
    this(context, null);
  }

  public SGCheckBox(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SGCheckBox(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SGCheckBox,
        defStyleAttr, 0);
    View inflate = LayoutInflater.from(context).inflate(R.layout.sgkit_checkbox_view, this, true);
    mLayout = inflate.findViewById(R.id.sgkit_rl_checkbox);
    mFIIcon = inflate.findViewById(R.id.sgkit_fv_icon);
    mTvText = inflate.findViewById(R.id.sgkit_tv_text);
    mText = typedArray.getString(R.styleable.SGCheckBox_sgkit_text);
    mTextSize = typedArray.getDimensionPixelSize(R.styleable.SGCheckBox_sgkit_textSize,
        getResources().getDimensionPixelSize(R.dimen.px48));
    mIconSize = typedArray.getDimensionPixelSize(R.styleable.SGCheckBox_sgkit_iconSize,
        getResources().getDimensionPixelSize(R.dimen.px48));
    mMode = typedArray.getInt(R.styleable.SGCheckBox_sgkit_mode, 0);
    mUnSelectIcon = typedArray.getString(R.styleable.SGCheckBox_sgkit_unSelectIcon);
    mUnSelectIconColor = typedArray.getColor(R.styleable.SGCheckBox_sgkit_unSelectIconColor,
        mUnSelectIconColor);
    mSelectedIcon = typedArray.getString(R.styleable.SGCheckBox_sgkit_selectedIcon);
    mSelectedIconColor = typedArray.getColor(R.styleable.SGCheckBox_sgkit_selectedIconColor,
        mSelectedIconColor);
    initView();
    initAction();
  }

  private void initView() {
    setText();
    setTextSize();
    setIconSize();
    setLocation();
    setIconTextStyle();
  }

  private void initAction() {
    mLayout.setOnClickListener(v -> {
      setSelected(!mLayout.isSelected());
    });
  }

  public void setText() {
    if (mTvText != null) {
      mTvText.setText(mText);
    }
  }

  /**
   * 设置文本字体大小
   */
  public void setTextSize() {
    if (mTvText != null) {
      mTvText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
    }
  }

  /**
   * 设置图标大小
   */
  public void setIconSize() {
    if (mFIIcon != null) {
      mFIIcon.setTextSize(TypedValue.COMPLEX_UNIT_PX,mIconSize);
    }
  }

  @Override
  public void setSelected(boolean selected) {
    super.setSelected(selected);
    if (mLayout.isEnabled()) {
      mLayout.setSelected(selected);
      mFIIcon.setSelected(selected);
      setIconTextStyle();
    }
  }

  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);
    mLayout.setEnabled(enabled);
    setIconTextStyle();
  }

  /**
   * 设置图标和文字的相对位置
   */
  private void setLocation() {
    //图标在右边
    if (mMode == 1) {
      //设置图标的位置
      ConstraintLayout.LayoutParams lpIcon = new ConstraintLayout.LayoutParams(
          LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
      lpIcon.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
      lpIcon.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
      mFIIcon.setLayoutParams(lpIcon);
      //设置文本的位置
      ConstraintLayout.LayoutParams lpText = new ConstraintLayout.LayoutParams(
          LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
      lpText.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
      lpText.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
      mTvText.setLayoutParams(lpText);
    }
  }

  /**
   * 设置图标和字体的样式
   */
  private void setIconTextStyle() {
    if(mLayout == null || mTvText == null || mFIIcon == null){
      return;
    }
    //可设置
    if (mLayout.isEnabled()) {
      mTvText.setTextColor(getContext().getResources().getColor(R.color.sgkit_text_title));
      //已选中情况下，设置图标的字体和颜色
      if (mFIIcon.isSelected()) {
        mFIIcon.setText(mSelectedIcon != null ? mSelectedIcon : "&#xe983;");
        mFIIcon.setTextColor(mSelectedIconColor);
      } else {
        mFIIcon.setText(mUnSelectIcon != null ? mUnSelectIcon : "&#xe6bc;");
        mFIIcon.setTextColor(mUnSelectIconColor);
      }
      //不可设置
    } else {
      mTvText.setTextColor(getContext().getResources().getColor(R.color.sgkit_text_disabled));
      //已选中情况下，设置图标的字体和颜色
      if (mFIIcon.isSelected()) {
        mFIIcon.setText(mSelectedIcon != null ? mSelectedIcon : "&#xe983;");
        mFIIcon.setTextColor(getContext().getResources().getColor(R.color.sgkit_disable_select));
      } else {
        mFIIcon.setText(mUnSelectIcon != null ? mUnSelectIcon : "&#xe6bc;");
        mFIIcon.setTextColor(getContext().getResources().getColor(R.color.sgkit_disable_unselect));
      }
    }
  }

  /**
   * 设置展示模式
   *
   * @param mode
   */
  public SGCheckBox setMode(int mode) {
    mMode = mode;
    setLocation();
    return this;
  }

  /**
   * 设置未选中状态下的图标
   *
   * @param unSelectIcon
   */
  public SGCheckBox setUnSelectIcon(String unSelectIcon) {
    this.mUnSelectIcon = unSelectIcon;
    return this;
  }

  /**
   * 设置未选中状态下图标颜色
   *
   * @param unSelectIconColor
   */
  public SGCheckBox setUnSelectIconColor(int unSelectIconColor) {
    mUnSelectIconColor = unSelectIconColor;
    setIconTextStyle();
    return this;
  }

  /**
   * 设置选中状态下图标
   *
   * @param selectedIcon
   */
  public SGCheckBox setSelectedIcon(String selectedIcon) {
    mSelectedIcon = selectedIcon;
    setIconTextStyle();
    return this;
  }

  /**
   * 设置选中状态下图标颜色
   *
   * @param selectedIconColor
   */
  public SGCheckBox setSelectedIconColor(int selectedIconColor) {
    mSelectedIconColor = selectedIconColor;
    setIconTextStyle();
    return this;
  }

}
