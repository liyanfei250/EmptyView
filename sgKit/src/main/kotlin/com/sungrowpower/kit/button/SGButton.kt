package com.sungrowpower.kit.button

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import com.sungrowpower.kit.R
import com.sungrowpower.kit.util.SGViewHelper

/**
 */
class SGButton : AppCompatButton {
  /**
   * 按下的状态和颜色
   * */
  private var mPressStatusText: Int = -1
  private var mPressTextColor: Int = -1

  /**
   * 抬起的状态和颜色
   * */
  private var mUnPressStatusText: Int = -1
  private var mUnPressTextColor: Int = -1

  /**
   * 禁用的状态和颜色
   * */
  private var mDisableStatusText: Int = -1
  private var mDisableTextColor: Int = -1

  private var mDefaultStateTextColor: Int = -1

  // override lateinit var mLayoutHelper: SGLayoutHelper
  private var mRoundBg: SGButtonDrawable? = null
  private var buttonDataBean: ButtonDataBean? = null

  constructor(context: Context) : super(context) {
    init(context, null, 0)
  }

  constructor(
    context: Context,
    attrs: AttributeSet?,
  ) : super(context, attrs) {
    init(context, attrs, 0)
  }

  constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
  ) : super(context, attrs, defStyleAttr) {
    init(context, attrs, defStyleAttr)
  }

  private fun init(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
  ) {
    // mLayoutHelper = SGLayoutHelper(context, attrs, defStyleAttr, this)
    //从xml中解析出背景色以及边框色
    val returnValue = SGButtonDrawable.fromAttributeSet(context, attrs, null, defStyleAttr)
    mRoundBg = returnValue.first
    buttonDataBean = returnValue.second

    if (buttonDataBean?.autoResetMinHeight == true) {
      (this as Button).minHeight = 0
      (this as Button).minimumHeight = 0
    }

    val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SGButton, defStyleAttr, 0)
    val indexCount: Int = typedArray.indexCount
    var pressTextColor = ButtonColorMap.pressColor
    var unPressTextColor = ButtonColorMap.unPressColor
    var disableStateTextColor = ButtonColorMap.disableStateColor
    var defaultStateTextColor = 0
    var pressStatusText = -1
    var unPressStatusText = -1
    var disableStatusText = -1
    for (i in 0 until indexCount) {
      val index: Int = typedArray.getIndex(i)
      when (index) {
        //按下状态颜色
        R.styleable.SGButton_sgkit_pressTextStateColor -> {
          pressTextColor = typedArray.getColor(index, buttonDataBean?.mPressTextColor ?: -1)
        }
        //抬起状态颜色
        R.styleable.SGButton_sgkit_unPressTextStateColor -> {
          unPressTextColor = typedArray.getColor(index, buttonDataBean?.mUnPressTextColor ?: -1)
        }
        //禁用状态颜色
        R.styleable.SGButton_sgkit_disableTextStateColor -> {
          disableStateTextColor = typedArray.getColor(index, buttonDataBean?.mDisableTextColor ?: -1)
        }
        //按下的状态是数  使用&获取到所有设置和按下一致的状态
        R.styleable.SGButton_sgkit_pressTextState -> {
          pressStatusText = typedArray.getInt(index, buttonDataBean?.mPressStatusText ?: -1)
        }
        //抬起的状态是数  使用&获取到所有设置和抬起一致的状态
        R.styleable.SGButton_sgkit_unPressTextState -> {
          unPressStatusText = typedArray.getInt(index, buttonDataBean?.mUnPressStatusText ?: -1)
        }
        //禁用的状态是数  使用&获取到所有设置和禁用一致的状态
        R.styleable.SGButton_sgkit_disableTextState -> {
          disableStatusText = typedArray.getInt(index, buttonDataBean?.mDisableStatusText ?: -1)
        }
        R.styleable.SGButton_sgkit_defaultTextColor -> {
          defaultStateTextColor = typedArray.getColor(index, buttonDataBean?.mDefaultStateTextColor ?: -1)
        }
      }
    }

    //如果设置了按压色，禁用色，抬起色，则给对应的属性赋值按压状态，禁用状态，抬起状态
    if (typedArray.hasValue(R.styleable.SGButton_sgkit_disableTextStateColor) && !typedArray.hasValue(R.styleable.SGButton_sgkit_disableTextState)) {
      disableStatusText = disabled
    }
    if (typedArray.hasValue(R.styleable.SGButton_sgkit_pressTextStateColor) && !typedArray.hasValue(R.styleable.SGButton_sgkit_pressTextState)) {
      pressStatusText = pressed
    }
    if (typedArray.hasValue(R.styleable.SGButton_sgkit_unPressTextStateColor) && !typedArray.hasValue(R.styleable.SGButton_sgkit_unPressTextState)) {
      unPressStatusText = unpressed
    }

    typedArray.recycle()

    //背景色
    if (pressStatusText != -1 || unPressStatusText != -1 || disableStatusText != -1) {
      if (defaultStateTextColor == 0) {
        defaultStateTextColor = textColors.defaultColor
      }
      //赋值后，给后续再次变更使用
      mPressStatusText = pressStatusText
      mPressTextColor = pressTextColor

      mUnPressStatusText = pressStatusText
      mUnPressTextColor = pressTextColor

      mDisableStatusText = disableStatusText
      mDisableTextColor = disableStateTextColor
      mDefaultStateTextColor = defaultStateTextColor
      setCustomTextColor(pressStatusText, unPressStatusText, disableStatusText, pressTextColor, unPressTextColor, disableStateTextColor, defaultStateTextColor)
    }
    SGViewHelper.setBackgroundKeepingPadding(this, mRoundBg)
  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
  }

  private fun setCustomTextColor(
    pressStatusText: Int,
    unPressStatusText: Int,
    disableStatusText: Int,
    pressTextColor: Int,
    unPressTextColor: Int,
    disableStateTextColor: Int,
    defaultStateTextColor: Int,
  ) {
    val colorStateList = SGButtonDrawable.getColorStateList(
      pressStatusText, unPressStatusText, disableStatusText,
      pressTextColor, unPressTextColor, disableStateTextColor, defaultStateTextColor)
    setTextColor(colorStateList)
  }

  /**
   * 设置按下状态集以及对应的颜色值
   * */
  fun setPressStatusTextColor(
    status: Int,
    color: Int,
  ): SGButton {
    mPressStatusText = status
    mPressTextColor = color
    return this
  }

  /**
   * 设置抬起状态集以及对应的颜色值
   * */
  fun setUnPressStatusTextColor(
    status: Int,
    color: Int,
  ): SGButton {
    mUnPressStatusText = status
    mUnPressTextColor = color
    return this
  }

  /**
   * 设置不可用状态集以及对应的颜色值
   * */
  fun setDisableStatusTextColor(
    status: Int,
    color: Int,
  ): SGButton {
    mDisableStatusText = status
    mDisableTextColor = color
    return this
  }

  /**
   * 设置文字默认状态的颜色值
   * */
  fun setDefaultStateTextColor(color: Int): SGButton {
    mDefaultStateTextColor = color
    return this
  }

  /**
   * 设置按压状态的颜色值
   * */
  fun setPressColor(color: Int): SGButton {
    buttonDataBean?.pressColor = color
    return this
  }

  /**
   * 设置抬起的颜色值
   * */
  fun setUnPressColor(color: Int): SGButton {
    buttonDataBean?.pressColor = color
    return this
  }

  /**
   * 设置不可用状态的颜色值
   * */
  fun setDisableStateColor(color: Int): SGButton {
    buttonDataBean?.disableStateColor = color
    return this
  }

  /**
   * 设置背景的默认颜色
   * */
  fun setDefaultStateColor(color: Int): SGButton {
    buttonDataBean?.defaultStateColor = color
    return this
  }

  /**
   * 八种状态，按位与后的int值
   * */
  fun setPressStatus(status: Int): SGButton {
    buttonDataBean?.pressStatus = status
    return this
  }

  /**
   * 设置不可按压状态的值
   * */
  fun setUnPressStatus(status: Int): SGButton {
    buttonDataBean?.unPressStatus = status
    return this
  }

  /**
   * 设置和不可用状态
   * */
  fun setDisableStatus(status: Int): SGButton {
    buttonDataBean?.disableStatus = status
    return this
  }

  /**
   * 设置背景填充色
   * */
  fun setSolidColor(color: Int): SGButton {
    buttonDataBean?.solidColor = color
    return this
  }

  /**
   * 设置线的颜色
   * */
  fun setStrokeColor(color: Int): SGButton {
    buttonDataBean?.strokeColor = color
    return this
  }

  /**
   * 设置背景按压的颜色值
   * */
  fun setPressBorderColor(color: Int): SGButton {
    buttonDataBean?.pressBorderColor = color
    return this
  }

  /**
   * 设置抬起的颜色值
   * */
  fun setUnPressBorderColor(color: Int): SGButton {
    buttonDataBean?.unPressBorderColor = color
    return this
  }

  /**
   * 设置不可用状态的颜色值
   * */
  fun setDisableStateBorderColor(color: Int): SGButton {
    buttonDataBean?.disableStateBorderColor = color
    return this
  }

  /**
   * 设置按下状态时线的状态集
   * */
  fun setPressStatusBorder(status: Int): SGButton {
    buttonDataBean?.pressStatusBorder = status
    return this
  }

  /**
   * 设置抬起状态时线的状态集
   * */
  fun setUnPressStatusBorder(status: Int): SGButton {
    buttonDataBean?.unPressStatusBorder = status
    return this
  }

  /**
   * 设置抬起状态时线的状态集
   * */
  fun setDisableStatusBorder(status: Int): SGButton {
    buttonDataBean?.disableStatusBorder = status
    return this
  }

  /**
   * 设置线的宽度
   * */
  fun setBorderWidth(width: Int): SGButton {
    buttonDataBean?.borderWidth = width
    return this
  }

  /**
   * 设置padding的时候，自动设置minHeight为0
   * */
  fun autoResetMinHeight(flag: Boolean): SGButton {
    buttonDataBean?.autoResetMinHeight = flag
    return this
  }

  /**
   * 设置圆角的大小
   * */
  fun setRadius(radius: Int): SGButton {
    buttonDataBean?.mRadius = radius
    return this
  }

  fun setRadiusTopLeft(radius: Int): SGButton {
    buttonDataBean?.mRadiusTopLeft = radius
    return this
  }

  fun setRadiusTopRight(radius: Int): SGButton {
    buttonDataBean?.mRadiusTopRight = radius
    return this
  }

  fun setRadiusBottomLeft(radius: Int): SGButton {
    buttonDataBean?.mRadiusBottomLeft = radius
    return this
  }

  fun setRadiusBottomRight(radius: Int): SGButton {
    buttonDataBean?.mRadiusBottomRight = radius
    return this
  }

  fun setRadiusAdjustBounds(isRadiusAdjustBounds: Boolean): SGButton {
    buttonDataBean?.isRadiusAdjustBounds = isRadiusAdjustBounds
    return this
  }

  fun setButtonData(data: ButtonDataBean): SGButton {
    buttonDataBean = data
    return this
  }

  /**
   * 构建新的颜色和背景色
   * */
  fun build() {
    setCustomTextColor(mPressStatusText, mUnPressStatusText, mDisableStatusText, mPressTextColor, mUnPressTextColor, mDisableTextColor, mDefaultStateTextColor)
    val createDrawable = SGButtonDrawable.createDrawable(buttonDataBean ?: ButtonDataBean())
    SGViewHelper.setBackgroundKeepingPadding(this, createDrawable)
  }

  companion object {
    /**
     *文字，背景色，线框色的八种状态
     * */
    const val pressed = 0x00000001
    const val focused = 0x00000010
    const val enabled = 0x00000100
    const val selected = 0x00001000
    const val unpressed = 0x00010000
    const val unfocused = 0x00100000
    const val disabled = 0x01000000
    const val unselected = 0x10000000
  }
}


