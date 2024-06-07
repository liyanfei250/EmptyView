package com.sungrowpower.kit.button

/**
 *
 * 实体类中的默认值引用ButtonColorMap类中从Json文件中解析出来的值
 * */
class ButtonDataBean {
  //背景色以及状态
  var pressColor = ButtonColorMap.pressColor
  var unPressColor = ButtonColorMap.unPressColor
  var disableStateColor = ButtonColorMap.disableStateColor
  var defaultStateColor = 0
  var pressStatus = -1
  var unPressStatus = -1
  var disableStatus = -1
  var solidColor = ButtonColorMap.solidColor
  var strokeColor = ButtonColorMap.strokeColor

  //边框的状态
  var pressBorderColor = ButtonColorMap.pressBorderColor
  var unPressBorderColor = ButtonColorMap.unPressBorderColor
  var disableStateBorderColor = ButtonColorMap.disableStateBorderColor
  var pressStatusBorder = -1
  var unPressStatusBorder = -1
  var disableStatusBorder = -1

  // 边框颜色以及边距
  var borderWidth = 0
  var mRadius = 0
  var mRadiusTopLeft = -1
  var mRadiusTopRight = -1
  var mRadiusBottomLeft = -1
  var mRadiusBottomRight = -1
  var isRadiusAdjustBounds = false

  /**
   * 按下的状态和颜色
   * */
  var mPressStatusText: Int = -1
  var mPressTextColor: Int = -1

  /**
   * 抬起的状态和颜色
   * */
  var mUnPressStatusText: Int = -1
  var mUnPressTextColor: Int = -1

  /**
   * 禁用的状态和颜色
   * */
  var mDisableStatusText: Int = -1
  var mDisableTextColor: Int = -1

  var mDefaultStateTextColor: Int = -1

  /**
   * 设置padding的时候，自动设置minHeight为0
   * */
  var autoResetMinHeight: Boolean = true
}