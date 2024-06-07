package com.sungrowpower.kit.dialog

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import com.sungrowpower.kit.R
import com.sungrowpower.kit.SGKit

class DialogDataBean {
  /**
   * 默认的文字大小
   * */
  val textSize = SGKit.getResource().getDimension(R.dimen.sgkit_textSize_17).toFloat()
  val divider = SGKit.getResource().getDimensionPixelSize(R.dimen.divider_height)

  /**
   * 是否为一个按钮
   * */
  var isSingle: Boolean = mDefaultStyle?.isSingle ?: false

  /**
   * 内容区域的文本
   * */
  var mContent: String = mDefaultStyle?.mContent ?: ""

  /**
   * 内容区域文字颜色
   * */
  var mContentColor: Int = if (mDefaultStyle == null) {
    DialogColorMap.mContentColor
  } else {
    mDefaultStyle?.mContentColor ?: -1
  }

  /**
   * 内容区域文字大小
   * */
  var mContentTextSize: Float = mDefaultStyle?.mContentTextSize ?: textSize

  /**
   * 宽度的比例
   * */
  var mWidthRatio: Float = mDefaultStyle?.mWidthRatio ?: 0.9f

  /**
   * 最大高度的比例
   * */
  var mMaxHeightRatio: Float = mDefaultStyle?.mMaxHeightRatio ?: 0.9f

  /**
   *  dialog的窗口背景
   * */
  var mWindowBackground: Drawable? = mDefaultStyle?.mWindowBackground

  /**
   * 自定义的字体
   * */
  var mCustomTypeface: Typeface? = mDefaultStyle?.mCustomTypeface

  /**
   *
   * 头部标题颜色
   * */
  var mTitleColor: Int = if (mDefaultStyle == null) {
    DialogColorMap.mTitleColor
  } else {
    mDefaultStyle?.mTitleColor ?: -1
  }

  var mTitleSize: Float = mDefaultStyle?.mTitleSize ?: textSize

  /**
   * 右边内容颜色
   * */
  var mRightContentColor: Int = if (mDefaultStyle == null) {
    DialogColorMap.mRightContentColor
  } else {
    mDefaultStyle?.mRightContentColor ?: -1
  }

  /**
   * 右边内容字体
   * */
  var mRightContentSize: Float = mDefaultStyle?.mRightContentTextSize ?: textSize

  /**
   * 分割线的颜色
   * */
  var mDividerColor: Int = if (mDefaultStyle == null) {
    DialogColorMap.mDividerColor
  } else {
    mDefaultStyle?.mDividerColor ?: -1
  }

  var mDividerWidth: Int = mDefaultStyle?.mDividerWidth ?: divider

  /**
   * 左边按钮的文字颜色
   * */
  var mLeftContentColor: Int = if (mDefaultStyle == null) {
    DialogColorMap.mLeftContentColor
  } else {
    mDefaultStyle?.mLeftContentColor ?: -1
  }
  var mLeftContentTextSize: Float = mDefaultStyle?.mLeftContentTextSize ?: textSize

  /**
   * 是否可以取消
   * */
  var mCancelAble: Boolean = mDefaultStyle?.mCancelAble ?: true

  /**
   * 点击确定和取消是否自动隐藏
   * */
  var mAutoDismiss: Boolean = mDefaultStyle?.mAutoDismiss ?: true

  /**
   * 是否有底部的按钮
   * */
  var hasFooterView: Boolean = mDefaultStyle?.hasFooterView ?: true

  /**
   * 左边按钮的文字内容
   * */
  var mLeftContent: String = mDefaultStyle?.mLeftContent ?: ""

  /**
   * 右边按钮的文字内容
   * */
  var mRightContent: String = mDefaultStyle?.mRightContent ?: ""

  /**
   * 标题文字内容
   * */
  var mTitle: String = mDefaultStyle?.mTitle ?: ""

  /**
   * 整体背景的圆角大小
   * */
  var mRadius: Int = mDefaultStyle?.mRadius ?: 20

  companion object {
    var dialogDataBean: DialogDataBean = DialogDataBean()

    /**
     * 设置全局默认的数据
     * */
    @SuppressLint("StaticFieldLeak") var mDefaultStyle: SGDialogBuilder? = null
    fun setDefaultStyle(view: SGDialogBuilder) {
      mDefaultStyle = view
      dialogDataBean = DialogDataBean()
      // DialogColorMap.setDefaultStyle(view)
    }
  }
}