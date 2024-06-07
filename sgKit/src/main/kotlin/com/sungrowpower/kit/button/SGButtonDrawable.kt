/*
 * Tencent is pleased to support the open source community by making QMUI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sungrowpower.kit.button

import android.R.attr
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import com.sungrowpower.kit.R
import com.sungrowpower.kit.button.SGButton.Companion.disabled
import com.sungrowpower.kit.button.SGButton.Companion.enabled
import com.sungrowpower.kit.button.SGButton.Companion.focused
import com.sungrowpower.kit.button.SGButton.Companion.pressed
import com.sungrowpower.kit.button.SGButton.Companion.selected
import com.sungrowpower.kit.button.SGButton.Companion.unfocused
import com.sungrowpower.kit.button.SGButton.Companion.unpressed
import com.sungrowpower.kit.button.SGButton.Companion.unselected

/**
 * 可以方便地生成圆角矩形/圆形 [android.graphics.drawable.Drawable]。
 *
 *
 *
 *  * 使用 [.setBgData] 设置背景色。
 *  * 使用 [.setStrokeData] 设置描边大小、描边颜色。
 *  * 使用 [.setIsRadiusAdjustBounds] 设置圆角大小是否自动适应为 [android.view.View] 的高度的一半, 默认为 true。
 *
 */
class SGButtonDrawable : GradientDrawable() {
  /**
   * 圆角大小是否自适应为 View 的高度的一般
   */
  private var mRadiusAdjustBounds = true
  private val mFillColors: ColorStateList? = null
  var strokeWidth = 0
    private set
  private var mStrokeColors: ColorStateList? = null

  /**
   * 设置按钮的背景色(只支持纯色,不支持 Bitmap 或 Drawable)
   */
  fun setBgData(colors: ColorStateList?) {
    super.setColor(colors)
  }

  /**
   * 设置按钮的描边粗细和颜色
   */
  fun setStrokeData(
    width: Int,
    colors: ColorStateList?,
  ) {
    strokeWidth = width
    mStrokeColors = colors
    super.setStroke(width, colors)
  }

  fun setStrokeColors(colors: ColorStateList?) {
    setStrokeData(strokeWidth, colors)
  }

  /**
   * 设置圆角大小是否自动适应为 View 的高度的一半
   */
  fun setIsRadiusAdjustBounds(isRadiusAdjustBounds: Boolean) {
    mRadiusAdjustBounds = isRadiusAdjustBounds
  }

  override fun onStateChange(stateSet: IntArray): Boolean {
    var superRet: Boolean = super.onStateChange(stateSet)
    if (mFillColors != null) {
      val color: Int = mFillColors.getColorForState(stateSet, 0)
      setColor(color)
      superRet = true
    }
    if (mStrokeColors != null) {
      val color: Int = mStrokeColors!!.getColorForState(stateSet, 0)
      setStroke(strokeWidth, color)
      superRet = true
    }
    return superRet
  }

  override fun isStateful(): Boolean {
    return (mFillColors != null && mFillColors.isStateful
      || mStrokeColors != null && mStrokeColors!!.isStateful
      || super.isStateful())
  }

  override fun onBoundsChange(r: Rect) {
    super.onBoundsChange(r)
    if (mRadiusAdjustBounds) {
      // 修改圆角为短边的一半
      cornerRadius = (Math.min(r.width(), r.height()) / 2).toFloat()
    }
  }

  companion object {
    fun fromAttributeSet(
      context: Context,
      attrs: AttributeSet?,
      buttonDataBean: ButtonDataBean?,
      defStyleAttr: Int,
    ): Pair<SGButtonDrawable, ButtonDataBean> {
      val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SGDrawable, defStyleAttr, 0)
      val indexCount: Int = typedArray.indexCount
      var buttonInnerButtonDataBean: ButtonDataBean = buttonDataBean ?: ButtonDataBean()
/*
      var pressColor = buttonInnerButtonDataBean.pressColor
      var unPressColor = buttonInnerButtonDataBean.unPressColor
      var disableStateColor = buttonInnerButtonDataBean.disableStateColor
      var defaultStateColor = 0
      var pressStatus = -1
      var unPressStatus = -1
      var disableStatus = -1
      var solidColor = buttonInnerButtonDataBean.solidColor
      var strokeColor = buttonInnerButtonDataBean.strokeColor
      //边框的状态
      var pressBorderColor = buttonInnerButtonDataBean.pressBorderColor
      var unPressBorderColor = buttonInnerButtonDataBean.unPressBorderColor
      var disableStateBorderColor = buttonInnerButtonDataBean.disableStateBorderColor
      var pressStatusBorder = -1
      var unPressStatusBorder = -1
      var disableStatusBorder = -1
      var borderWidth = 0
      var mRadius = 0
      var mRadiusTopLeft = -1
      var mRadiusTopRight = -1
      var mRadiusBottomLeft = -1
      var mRadiusBottomRight = -1
      var isRadiusAdjustBounds = false*/
      for (i in 0 until indexCount) {
        val index: Int = typedArray.getIndex(i)
        when (index) {
          //按下状态颜色
          R.styleable.SGDrawable_sgkit_pressBackgroundStateColor -> {
            buttonInnerButtonDataBean.pressColor = typedArray.getColor(index, buttonInnerButtonDataBean.pressColor)
          }
          //抬起状态颜色
          R.styleable.SGDrawable_sgkit_unPressBackgroundStateColor -> {
            buttonInnerButtonDataBean.unPressColor = typedArray.getColor(index, buttonInnerButtonDataBean.unPressColor)
          }
          //禁用状态颜色
          R.styleable.SGDrawable_sgkit_disableBackgroundStateColor -> {
            buttonInnerButtonDataBean.disableStateColor = typedArray.getColor(index, buttonInnerButtonDataBean.disableStateColor)
          }
          //默认颜色
          R.styleable.SGDrawable_sgkit_defaultBackgroundColor -> {
            buttonInnerButtonDataBean.defaultStateColor = typedArray.getColor(index, buttonInnerButtonDataBean.defaultStateColor)
          }
          //按下的状态是数  使用&获取到所有设置和按下一致的状态
          R.styleable.SGDrawable_sgkit_pressBackgroundState -> {
            buttonInnerButtonDataBean.pressStatus = typedArray.getInt(index, buttonInnerButtonDataBean.pressStatus)
          }
          //抬起的状态是数  使用&获取到所有设置和抬起一致的状态
          R.styleable.SGDrawable_sgkit_unPressBackgroundState -> {
            buttonInnerButtonDataBean.unPressStatus = typedArray.getInt(index, buttonInnerButtonDataBean.unPressStatus)
          }
          //禁用的状态是数  使用&获取到所有设置和禁用一致的状态
          R.styleable.SGDrawable_sgkit_disableBackgroundState -> {
            buttonInnerButtonDataBean.disableStatus = typedArray.getInt(index, buttonInnerButtonDataBean.disableStatus)
          }
          //填充颜色
          R.styleable.SGDrawable_sgkit_shapeSolidColor -> {
            buttonInnerButtonDataBean.solidColor = typedArray.getColor(index, buttonInnerButtonDataBean.solidColor)
          }
          //边框颜色
          R.styleable.SGDrawable_sgkit_defaultBorderColor -> {
            buttonInnerButtonDataBean.strokeColor = typedArray.getColor(index, buttonInnerButtonDataBean.strokeColor)
          }
          //边框状态
          //按下状态颜色
          R.styleable.SGDrawable_sgkit_pressBorderStateColor -> {
            buttonInnerButtonDataBean.pressBorderColor = typedArray.getColor(index, buttonInnerButtonDataBean.pressColor)
          }
          //抬起状态颜色
          R.styleable.SGDrawable_sgkit_unPressBorderStateColor -> {
            buttonInnerButtonDataBean.unPressBorderColor = typedArray.getColor(index, buttonInnerButtonDataBean.unPressColor)
          }
          //禁用状态颜色
          R.styleable.SGDrawable_sgkit_disableBorderStateColor -> {
            buttonInnerButtonDataBean.disableStateBorderColor = typedArray.getColor(index, buttonInnerButtonDataBean.disableStateColor)
          }
          //按下的状态是数  使用&获取到所有设置和按下一致的状态
          R.styleable.SGDrawable_sgkit_pressBorderState -> {
            buttonInnerButtonDataBean.pressStatusBorder = typedArray.getInt(index, buttonInnerButtonDataBean.pressStatusBorder)
          }
          //抬起的状态是数  使用&获取到所有设置和抬起一致的状态
          R.styleable.SGDrawable_sgkit_unPressBorderState -> {
            buttonInnerButtonDataBean.unPressStatusBorder = typedArray.getInt(index, buttonInnerButtonDataBean.unPressStatusBorder)
          }
          //禁用的状态是数  使用&获取到所有设置和禁用一致的状态
          R.styleable.SGDrawable_sgkit_disableBorderState -> {
            buttonInnerButtonDataBean.disableStatusBorder = typedArray.getInt(index, buttonInnerButtonDataBean.disableStatusBorder)
          }
          //填充颜色
          R.styleable.SGDrawable_sgkit_strokeWidth -> {
            buttonInnerButtonDataBean.borderWidth = typedArray.getDimensionPixelSize(index, buttonInnerButtonDataBean.borderWidth)
          }
          //圆角
          R.styleable.SGDrawable_sgkit_shapeRadius -> {
            buttonInnerButtonDataBean.mRadius = typedArray.getDimensionPixelSize(index, buttonInnerButtonDataBean.mRadius)
          }
          R.styleable.SGDrawable_sgkit_shapeTopLeftRadius -> {
            buttonInnerButtonDataBean.mRadiusTopLeft = typedArray.getDimensionPixelSize(index, buttonInnerButtonDataBean.mRadiusTopLeft)
          }
          R.styleable.SGDrawable_sgkit_shapeTopRightRadius -> {
            buttonInnerButtonDataBean.mRadiusTopRight = typedArray.getDimensionPixelSize(index, buttonInnerButtonDataBean.mRadiusTopRight)
          }
          R.styleable.SGDrawable_sgkit_shapeBottomLeftRadius -> {
            buttonInnerButtonDataBean.mRadiusBottomLeft = typedArray.getDimensionPixelSize(index, buttonInnerButtonDataBean.mRadiusBottomLeft)
          }
          R.styleable.SGDrawable_sgkit_shapeBottomRightRadius -> {
            buttonInnerButtonDataBean.mRadiusBottomRight = typedArray.getDimensionPixelSize(index, buttonInnerButtonDataBean.mRadiusBottomRight)
          }
          R.styleable.SGDrawable_sgkit_isRadiusAdjustBounds -> {
            buttonInnerButtonDataBean.isRadiusAdjustBounds = typedArray.getBoolean(index, buttonInnerButtonDataBean.isRadiusAdjustBounds)
          }
        }
      }
      //填充色的默认值赋值，如果设置了颜色，没有状态时，赋值默认状态
      if (typedArray.hasValue(R.styleable.SGDrawable_sgkit_pressBackgroundStateColor) && !typedArray.hasValue(R.styleable.SGDrawable_sgkit_pressBackgroundState)) {
        buttonInnerButtonDataBean.pressStatus = pressed
      }
      if (typedArray.hasValue(R.styleable.SGDrawable_sgkit_unPressBackgroundStateColor) && !typedArray.hasValue(R.styleable.SGDrawable_sgkit_unPressBackgroundState)) {
        buttonInnerButtonDataBean.unPressStatus = unpressed
      }
      if (typedArray.hasValue(R.styleable.SGDrawable_sgkit_disableBackgroundStateColor) && !typedArray.hasValue(R.styleable.SGDrawable_sgkit_disableBackgroundState)) {
        buttonInnerButtonDataBean.disableStatus = disabled
      }

//边框色的默认值赋值，如果设置了颜色，没有状态时，赋值默认状态
      if (typedArray.hasValue(R.styleable.SGDrawable_sgkit_pressBorderStateColor) && !typedArray.hasValue(R.styleable.SGDrawable_sgkit_pressBorderState)) {
        buttonInnerButtonDataBean.pressStatusBorder = pressed
      }
      if (typedArray.hasValue(R.styleable.SGDrawable_sgkit_unPressBorderStateColor) && !typedArray.hasValue(R.styleable.SGDrawable_sgkit_unPressBorderState)) {
        buttonInnerButtonDataBean.unPressStatusBorder = unpressed
      }
      if (typedArray.hasValue(R.styleable.SGDrawable_sgkit_disableBorderStateColor) && !typedArray.hasValue(R.styleable.SGDrawable_sgkit_disableBorderState)) {
        buttonInnerButtonDataBean.disableStatusBorder = disabled
      }

      typedArray.recycle()
      val bg = createDrawable(buttonInnerButtonDataBean)

      return Pair(bg, buttonInnerButtonDataBean)
    }

    fun createDrawable(buttonInnerButtonDataBean: ButtonDataBean): SGButtonDrawable {
      //背景色
      val bg = SGButtonDrawable()
      if (buttonInnerButtonDataBean.pressStatus != -1 || buttonInnerButtonDataBean.unPressStatus != -1 || buttonInnerButtonDataBean.disableStatus != -1) {
        if (buttonInnerButtonDataBean.defaultStateColor == 0) {
          //如果没有设置取solidColor
          buttonInnerButtonDataBean.defaultStateColor = buttonInnerButtonDataBean.solidColor
        }
        val colorStateList = getColorStateList(
          buttonInnerButtonDataBean.pressStatus, buttonInnerButtonDataBean.unPressStatus, buttonInnerButtonDataBean.disableStatus,
          buttonInnerButtonDataBean.pressColor, buttonInnerButtonDataBean.unPressColor, buttonInnerButtonDataBean.disableStateColor, buttonInnerButtonDataBean.defaultStateColor)
        bg.setBgData(colorStateList)
      } else {
        bg.setColor(buttonInnerButtonDataBean.solidColor)
      }

      if (buttonInnerButtonDataBean.pressStatusBorder != -1 || buttonInnerButtonDataBean.unPressStatusBorder != -1 || buttonInnerButtonDataBean.disableStatusBorder != -1) {
        val colorStateList1 = getColorStateList(
          buttonInnerButtonDataBean.pressStatusBorder, buttonInnerButtonDataBean.unPressStatusBorder, buttonInnerButtonDataBean.disableStatusBorder,
          buttonInnerButtonDataBean.pressBorderColor, buttonInnerButtonDataBean.unPressBorderColor, buttonInnerButtonDataBean.disableStateBorderColor,
          buttonInnerButtonDataBean.strokeColor)
        bg.setStrokeData(buttonInnerButtonDataBean.borderWidth, colorStateList1)
      } else {
        bg.setStroke(buttonInnerButtonDataBean.borderWidth, buttonInnerButtonDataBean.strokeColor)
      }

      if (buttonInnerButtonDataBean.mRadiusTopLeft > 0 || buttonInnerButtonDataBean.mRadiusTopRight > 0 || buttonInnerButtonDataBean.mRadiusBottomLeft > 0 || buttonInnerButtonDataBean.mRadiusBottomRight > 0) {
        val radii = floatArrayOf(
          buttonInnerButtonDataBean.mRadiusTopLeft.toFloat(), buttonInnerButtonDataBean.mRadiusTopLeft.toFloat(),
          buttonInnerButtonDataBean.mRadiusTopRight.toFloat(), buttonInnerButtonDataBean.mRadiusTopRight.toFloat(),
          buttonInnerButtonDataBean.mRadiusBottomRight.toFloat(), buttonInnerButtonDataBean.mRadiusBottomRight.toFloat(),
          buttonInnerButtonDataBean.mRadiusBottomLeft.toFloat(), buttonInnerButtonDataBean.mRadiusBottomLeft.toFloat())
        bg.cornerRadii = radii
        buttonInnerButtonDataBean.isRadiusAdjustBounds = false
      } else {
        bg.cornerRadius = buttonInnerButtonDataBean.mRadius.toFloat()
        if (buttonInnerButtonDataBean.mRadius > 0) {
          buttonInnerButtonDataBean.isRadiusAdjustBounds = false
        }
      }
      bg.setIsRadiusAdjustBounds(buttonInnerButtonDataBean.isRadiusAdjustBounds)
      return bg
    }

    fun getColorStateList(
      pressStatus: Int,
      unPressStatus: Int,
      disableStatus: Int,
      pressColor: Int,
      unPressColor: Int,
      disableStateColor: Int,
      defaultStateColor: Int,
    ): ColorStateList {
      // 创建ColorStateList对象
      var statesList = ArrayList<IntArray>()
      var colorList = ArrayList<Int>()

      if (pressStatus != -1) {
        statesList.add(getStateArray(pressStatus))
        colorList.add(pressColor)
      }
      if (disableStatus != -1) {
        statesList.add(getStateArray(unPressStatus))
        colorList.add(unPressColor)
      }
      if (disableStatus != -1) {
        statesList.add(getStateArray(disableStatus))
        colorList.add(disableStateColor)
      }
      statesList.add(intArrayOf())
      colorList.add(defaultStateColor)
      val toArray = statesList.toArray(Array<IntArray>(statesList.size) { intArrayOf() })
      return ColorStateList(toArray, colorList.toList().toIntArray())
    }

    /*<flag name="pressed" value="0x00000001" />
      <flag name="focused" value="0x00000010" />
      <flag name="enable" value="0x00000100" />
      <flag name="selected" value="0x00001000" />
      <flag name="unpressed" value="0x00010000" />
      <flag name="unfocused" value="0x00100000" />
      <flag name="unEnable" value="0x01000000" />
      <flag name="unselected" value="0x10000000" />*/
    private fun getStateArray(pressStatus: Int): IntArray {
      val objects = ArrayList<Int>()
      //pressed
      if (pressStatus and pressed == pressed) {
        objects.add(attr.state_pressed)
      }
      //focused
      if (pressStatus and focused == focused) {
        objects.add(attr.state_focused)
      }
      //enable
      if (pressStatus and enabled == enabled) {
        objects.add(attr.state_enabled)
      }
      //selected
      if (pressStatus and selected == selected) {
        objects.add(attr.state_selected)
      }
      //unpressed
      if (pressStatus and unpressed == unpressed) {
        objects.add(-attr.state_pressed)
      }
      //unfocused
      if (pressStatus and unfocused == unfocused) {
        objects.add(-attr.state_focused)
      }
      //unEnable
      if (pressStatus and disabled == disabled) {
        objects.add(-attr.state_enabled)
      }
      //unselected
      if (pressStatus and unselected == unselected) {
        objects.add(-attr.state_selected)
      }
      val ints = IntArray(objects.size)
      for (i in objects.indices) {
        ints[i] = objects[i]
      }
      return ints
    }
  }
}