package com.sungrowpower.kit.dialog

import android.content.Context
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.sungrowpower.kit.button.ButtonDataBean

class SGDialogBuilder {
  var SGDialog: SGDialog? = null

  /**
   * 是否为一个按钮
   * */
  var isSingle: Boolean = DialogDataBean.dialogDataBean.isSingle

  /**
   * 内容区域的文本
   * */
  var mContent: String = ""

  /**
   * 内容区域的外边距
   * */
  var mMargin: Rect? = null

  /**
   * 内容区域的内边距
   * */
  var mPadding: Rect? = null

  /**
   * 内容区域文字颜色
   * */
  var mContentColor: Int = DialogDataBean.dialogDataBean.mContentColor

  /**
   * 内容区域文字颜色
   * */
  var mContentTextSize: Float = DialogDataBean.dialogDataBean.mContentTextSize

  /**
   * 宽度的比例
   * */
  var mWidthRatio: Float = DialogDataBean.dialogDataBean.mWidthRatio

  /**
   * 最大高度的比例
   * */
  var mMaxHeightRatio: Float = DialogDataBean.dialogDataBean.mMaxHeightRatio

  /**
   *  dialog的窗口背景
   * */
  var mWindowBackground: Drawable? = DialogDataBean.dialogDataBean.mWindowBackground

  /**
   * 自定义的字体
   * */
  var mCustomTypeface: Typeface? = DialogDataBean.dialogDataBean.mCustomTypeface

  /**
   *
   * 头部标题颜色
   * */
  var mTitleColor: Int = DialogDataBean.dialogDataBean.mTitleColor

  /**
   * 头部标题字体大小
   * */
  var mTitleSize: Float = DialogDataBean.dialogDataBean.mTitleSize

  /**
   * 右边内容颜色
   * */
  var mRightContentColor: Int = DialogDataBean.dialogDataBean.mRightContentColor

  /**
   * 右边内容大小
   * */
  var mRightContentTextSize: Float = DialogDataBean.dialogDataBean.mRightContentSize

  /**
   * 分割线的颜色
   * */
  var mDividerColor: Int = DialogDataBean.dialogDataBean.mDividerColor

  /**
   * 分割线的宽度
   * */
  var mDividerWidth: Int = DialogDataBean.dialogDataBean.mDividerWidth

  /**
   * 左边按钮的文字颜色
   * */
  var mLeftContentColor: Int = DialogDataBean.dialogDataBean.mLeftContentColor

  /**
   * 左边按钮的文字大小
   * */
  var mLeftContentTextSize: Float = DialogDataBean.dialogDataBean.mLeftContentTextSize

  /**
   * 是否可以取消
   * */
  var mCancelAble: Boolean = DialogDataBean.dialogDataBean.mCancelAble

  /**
   * 是否有底部的按钮
   * */
  var hasFooterView: Boolean = DialogDataBean.dialogDataBean.hasFooterView ?: true

  /**
   * 点击确定和取消是否自动隐藏
   * */
  var mAutoDismiss: Boolean = DialogDataBean.dialogDataBean.mAutoDismiss

  /**
   * 中间的view，可以自定义
   * */
  var mContentView: View? = null

  /**
   * 底部按钮自定义view
   * */
  var mFooterView: View? = null
  var mRightListener: View.OnClickListener? = null
  var mLeftListener: View.OnClickListener? = null

  /**
   * 左边按钮的文字内容
   * */
  var mLeftContent: String = DialogDataBean.dialogDataBean.mLeftContent

  /**
   * 右边按钮的文字内容
   * */
  var mRightContent: String = DialogDataBean.dialogDataBean.mRightContent

  /**
   * 标题文字内容
   * */
  var mTitle: String = DialogDataBean.dialogDataBean.mTitle

  /**
   * 整体背景的圆角大小
   * */
  var mRadius: Int = DialogDataBean.dialogDataBean.mRadius

  /**
   * 按钮的样式类型
   * */
  var mButtonViewType: Int = 0

  /**
   * 内容的样式类型
   * */
  var mContentViewType: Int = 0

  /**
   * Image  的资源id
   * */
  var mImageResource: Int = 0

  /**
   * image的资源drawable
   * */
  var mImageDrawable: Drawable? = null

  /**
   * 类型为TipDialogBuilder.MUTIL_FOOTER时，底部的button list
   * */
  var mButtonList: ArrayList<String>? = null

  /**
   * 类型为TipDialogBuilder.MUTIL_FOOTER时，底部的button 样式的list
   * */
  var buttonStyleList: ArrayList<ButtonDataBean>? = null

  /**
   * 类型为TipDialogBuilder.MUTIL_FOOTER时，底部的button item的点击监听
   * */
  var mButtonListListener: DialogItemListClickListener? = null

  var mPrimaryText: String? = ""
  var mAssistText: String? = ""
  var mPrimaryStyle: ButtonDataBean? = null
  var mAssistStyle: ButtonDataBean? = null

  companion object {
    val NORMAL_CONTENT = 0
    val IMAGE_CONTENT = 1
    val EDIT_CONTENT = 2

    val NORMAL_FOOTER = 0
    val MULTI_FOOTER = 1
    val SINGAL_LINE_FOOTER = 2

    /**
     * 设置全局默认的数据
     * */
    // @SuppressLint("StaticFieldLeak") var mDefaultStyle: TipDialogBuilder? = null
    fun setDefaultStyle(view: SGDialogBuilder) {
      // mDefaultStyle = view
      DialogDataBean.setDefaultStyle(view)
    }
  }

  /**
   * 设置标题标题文字内容 不设置则为空，不显示标题
   * */
  fun title(title: String): SGDialogBuilder {
    mTitle = title
    return this
  }

  /**
   * 设置标题标题文字内容 不设置则为空，不显示标题
   * */
  fun titleColor(@ColorInt color: Int): SGDialogBuilder {
    mTitleColor = color
    return this
  }

  /**
   * 设置是否为一个按钮,默认两个按钮 true表示一个按钮  false表示两个按钮
   * */
  fun singleAction(flag: Boolean): SGDialogBuilder {
    isSingle = flag
    return this
  }

  fun singleActionButtonContent(content: String): SGDialogBuilder {
    mRightContent = content
    return this
  }

  fun singleActionColor(@ColorInt color: Int): SGDialogBuilder {
    mRightContentColor = color
    return this
  }

  fun singleActionListener(listener: View.OnClickListener): SGDialogBuilder {
    this.mLeftListener = listener
    return this
  }

  /**
   * 设置类型为NORMAL_CONTENT时内容的文本
   * */
  fun content(content: String): SGDialogBuilder {
    mContent = content
    return this
  }

  /**
   * 设置类型为NORMAL_CONTENT时内容的颜色
   * */
  fun contentColor(@ColorInt color: Int): SGDialogBuilder {
    mContentColor = color
    return this
  }

  /**
   * 设置中间内容的Margin ,利用JvmOverloads重载多个方法
   * */
  @JvmOverloads
  fun contentMargin(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0,
  ): SGDialogBuilder {
    mMargin = Rect(left, top, right, bottom)
    return this
  }

  @JvmOverloads
  fun contentPadding(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0,
  ): SGDialogBuilder {
    mPadding = Rect(left, top, right, bottom)
    return this
  }

  fun dialogWindowBackground(background: Drawable): SGDialogBuilder {
    mWindowBackground = background
    return this
  }

  /**
   * 设置最大高度的比例,只有超过这个比例的时候才会生效，否则为内容的高度
   * */
  fun maxHeightRatio(ratio: Float): SGDialogBuilder {
    mWidthRatio = ratio
    return this
  }

  /**
   * 设置最大宽度的比例
   * */
  fun widthRatio(ratio: Float): SGDialogBuilder {
    mWidthRatio = ratio
    return this
  }

  /**
   * 左边按钮的文字内容，不设置默认取消
   * */
  fun leftContent(title: String): SGDialogBuilder {
    mLeftContent = title
    mPrimaryText = title
    return this
  }

  /**
   * 右边文本的点击事件
   * */
  fun leftContentListener(listener: View.OnClickListener): SGDialogBuilder {
    this.mLeftListener = listener
    return this
  }

  /**
   * 左边按钮的文字颜色，不设置默认黑色
   * */
  fun leftContentColor(@ColorInt color: Int): SGDialogBuilder {
    mLeftContentColor = color
    return this
  }

  /**
   * 右边按钮的文字内容，不设置默认确定
   * */
  fun rightContent(title: String): SGDialogBuilder {
    mRightContent = title
    mAssistText = title
    return this
  }

  /**
   * 右边文本的点击事件
   * */
  fun rightContentListener(listener: View.OnClickListener): SGDialogBuilder {
    this.mRightListener = listener
    return this
  }

  /**
   * 右边按钮的文字内容，不设置默认确定
   * */
  fun rightContentColor(@ColorInt color: Int): SGDialogBuilder {
    mRightContentColor = color
    return this
  }

  /**
   * 确定与取消之间 线的颜色  确定按钮上面的颜色
   * */
  fun dividerColor(@ColorInt color: Int): SGDialogBuilder {
    mDividerColor = color
    return this
  }

  /**
   * 点击确定或者取消按钮之后是否自动dismiss
   * */
  fun autoDismiss(flag: Boolean): SGDialogBuilder {
    mAutoDismiss = flag
    return this
  }

  /**
   * 设置整体背景的圆角大小
   * */
  fun setRadius(radius: Int): SGDialogBuilder {
    mRadius = radius
    return this
  }

  /**
   * 右边按钮的文字内容，不设置默认确定
   * */
  fun setCustomTypeface(color: Typeface): SGDialogBuilder {
    mCustomTypeface = color
    return this
  }

  /**
   * 设置是否可以取消，不设置默认可以取消
   * */
  fun cancelAble(cancelAble: Boolean): SGDialogBuilder {
    mCancelAble = cancelAble
    return this
  }

  /**
   * 设置底部的取消和确定是否需要，不设置默认需要
   * */
  fun hasFooterView(hasFooterView: Boolean): SGDialogBuilder {
    this.hasFooterView = hasFooterView
    return this
  }

  fun setDialogContentView(view: View): SGDialogBuilder {
    mContentView = view
    return this
  }

  fun setDialogFooterViewView(view: View): SGDialogBuilder {
    mFooterView = view
    return this
  }

  /**
   * 设置Image  的资源id
   * */
  fun setImageResource(@DrawableRes res: Int): SGDialogBuilder {
    this.mImageResource = res
    return this
  }

  /**
   * 设置image的资源drawable
   * */
  fun setImageDrawable(drawable: Drawable): SGDialogBuilder {
    this.mImageDrawable = drawable
    return this
  }

  /**
   * 设置 内容的样式类型 @link
   * TipDialogBuilder.NORMAL_CONTENT
   * ,TipDialogBuilder.IMAGE_CONTENT
   * ,TipDialogBuilder.EDIT_CONTENT
   * */
  fun setContentViewType(type: Int): SGDialogBuilder {
    this.mContentViewType = type
    return this
  }

  /**
   * 设置 按钮的样式类型 @link
   * TipDialogBuilder.NORMAL_FOOTER
   * ,TipDialogBuilder.MUTIL_FOOTER
   * ,TipDialogBuilder.SINGAL_LINE_FOOTER
   * */
  fun setButtonViewType(type: Int): SGDialogBuilder {
    this.mButtonViewType = type
    return this
  }

  /**
   * 当底部button的类型为TipDialogBuilder.MUTIL_FOOTER 的时候设置的button list
   * @param  arrayList button的内容list
   * @param buttonStyleList 每个button的样式，不设置则默认白色背景褐色文字
   * @param listener  button的点击回调
   * */
  @JvmOverloads
  fun setButtonList(
    arrayList: ArrayList<String>,
    buttonStyleList: ArrayList<ButtonDataBean> = ArrayList(),
    listener: DialogItemListClickListener,
  ): SGDialogBuilder {
    this.mButtonList = arrayList
    this.buttonStyleList = buttonStyleList
    this.mButtonListListener = listener
    return this
  }

  /**
   * 当底部button的类型为TipDialogBuilder.SINGAL_LINE_FOOTER 的时候设置的button list
   * @param  primaryText  主要按钮文本，可使用setLeftContent设置
   * @param primaryStyle 主要按钮的样式
   * @param assistText  次要按钮文本，可使用setRightContent设置
   * @param assistStyle  辅助按钮样式
   * 对应的回调 主要按钮回调到 mLeftListener  辅助按钮回调到 mRightListener
   * */
  @JvmOverloads
  fun setSingleLineType(
    primaryText: String = mLeftContent,
    primaryStyle: ButtonDataBean?,
    assistText: String = mRightContent,
    assistStyle: ButtonDataBean? = null,
  ): SGDialogBuilder {
    this.mPrimaryText = primaryText
    this.mPrimaryStyle = primaryStyle
    this.mAssistText = assistText
    this.mAssistStyle = assistStyle
    return this
  }

  /**
   * 显示dialog方法
   * */
  fun show(context: Context) {
    if (SGDialog == null) {
      SGDialog = SGDialog(context, this)
    }
    SGDialog?.show()
  }
}