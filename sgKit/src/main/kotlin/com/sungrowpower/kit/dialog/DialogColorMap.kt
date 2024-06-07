package com.sungrowpower.kit.dialog

import org.json.JSONObject

/**
 * 处理默认的颜色和字体大小类，以及解析本地的json类
 * */
object DialogColorMap {

  /**
   * 内容区域文字颜色
   * */
  var mContentColor: Int = -1

  /**
   * 宽度的比例
   * */
  var mWidthRatio: Float = 0.9f

  /**
   * 最大高度的比例
   * */
  var mMaxHeightRatio: Float = 0.9f

  /**
   *
   * 头部标题颜色
   * */
  var mTitleColor: Int = -1

  /**
   * 右边内容颜色
   * */
  var mRightContentColor: Int = -1

  /**
   * 分割线的颜色
   * */
  var mDividerColor: Int = -1

  /**
   * 左边按钮的文字颜色
   * */
  var mLeftContentColor: Int = -1

  /**
   * 是否可以取消
   * */
  var mCancelAble: Boolean = true

  /**
   * 是否有底部的按钮
   * */
  var hasFooterView: Boolean = true

  /**
   * 点击确定和取消是否自动隐藏
   * */
  var mAutoDismiss: Boolean = true

  /**
   * 整体背景的圆角大小
   * */
  var mRadius: Int = -1

  fun getColorByName(colorKey: String) {
  }

  fun initJson(content: String) {
    try {
      val jsonObject = JSONObject(content)
    } catch (e: Exception) {
    }
  }
}