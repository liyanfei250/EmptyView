package com.sungrowpower.kit.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.WindowManager.LayoutParams
import android.widget.AdapterView.OnItemClickListener
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import com.sungrowpower.kit.R
import com.sungrowpower.kit.R.id
import com.sungrowpower.kit.R.layout
import com.sungrowpower.kit.button.SGButton
import com.sungrowpower.kit.button.SGButtonDrawable

class SGDialog(
  context: Context,
  val dialogBuilder: SGDialogBuilder,
) : AppCompatDialog(context), OnClickListener {
  lateinit var tvDialogTitleSgKit: TextView
  lateinit var flContent: FrameLayout
  lateinit var flFooter: FrameLayout
  lateinit var rootView: View

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val inflater = LayoutInflater.from(context)
    rootView = inflater.inflate(R.layout.sgkit_tip_dialog, null)
    tvDialogTitleSgKit = rootView.findViewById(R.id.tv_dialogTitleSgKit)
    flContent = rootView.findViewById(R.id.fl_content)
    flFooter = rootView.findViewById(R.id.fl_footer)
    initDialog()
    initData()
    setContentView(rootView)
  }

  /**
   * 设置dialog相关的属性
   * */
  private fun initDialog() {
    val window = window ?: return
    this.setCancelable(dialogBuilder.mCancelAble)
    if (dialogBuilder.mWindowBackground != null) {
      window.setBackgroundDrawable(dialogBuilder.mWindowBackground)
    } else {
      window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
    val wm = window.windowManager
    val display = wm.defaultDisplay
    val size = Point()
    display.getSize(size)
    val maxWidth = size.x * dialogBuilder.mWidthRatio
    val windowHeight = size.y * dialogBuilder.mMaxHeightRatio
    val lp = LayoutParams()
    lp.copyFrom(getWindow()!!.attributes)
    lp.width = maxWidth.toInt()
    lp.height = windowHeight.toInt()
    getWindow()!!.attributes = lp
  }

  /**
   * 设置dialog中view的相关属性以及view的初始化
   * */
  private fun initData() {
    //构建标题
    if (dialogBuilder.mTitle.isNotEmpty()) {
      tvDialogTitleSgKit.text = dialogBuilder.mTitle
      tvDialogTitleSgKit.setTextColor(dialogBuilder.mTitleColor)
      tvDialogTitleSgKit.textSize = dialogBuilder.mTitleSize
      dialogBuilder.mCustomTypeface?.let {
        tvDialogTitleSgKit.setTypeface(it)
      }
    } else {
      tvDialogTitleSgKit.visibility = View.GONE
    }

    //构建内容部分
    var contentView = if (dialogBuilder.mContentView == null) {
      if (dialogBuilder.mContentViewType == SGDialogBuilder.NORMAL_CONTENT) {
        createNormalContentView()
      } else if (dialogBuilder.mContentViewType == SGDialogBuilder.IMAGE_CONTENT) {
        createImageContentView()
      } else if (dialogBuilder.mContentViewType == SGDialogBuilder.EDIT_CONTENT) {
        createEditContentView()
      } else {
        createNormalContentView()
      }
    } else {
      dialogBuilder.mContentView!!
    }

    //设置padding 和margin
    if (dialogBuilder.mPadding != null) {
      contentView.setPadding(
        dialogBuilder.mPadding?.left ?: 0,
        dialogBuilder.mPadding?.top ?: 0,
        dialogBuilder.mPadding?.right ?: 0,
        dialogBuilder.mPadding?.bottom ?: 0)
    }
    if (dialogBuilder.mMargin != null) {
      val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
      layoutParams.leftMargin = dialogBuilder.mMargin?.left ?: 0
      layoutParams.topMargin = dialogBuilder.mMargin?.top ?: 0
      layoutParams.rightMargin = dialogBuilder.mMargin?.right ?: 0
      layoutParams.bottomMargin = dialogBuilder.mMargin?.bottom ?: 0
      contentView.layoutParams = layoutParams
    }
    flContent.addView(contentView)

    //配置包含底部按钮，再去初始化
    if (dialogBuilder.hasFooterView) {
      //如果有底部按钮，但是底部按钮的view为空再需要添加默认的
      var view = if (dialogBuilder.mFooterView == null) {
        if (dialogBuilder.mButtonViewType == SGDialogBuilder.NORMAL_FOOTER) {
          createNormalButtonType()
        } else if (dialogBuilder.mButtonViewType == SGDialogBuilder.MULTI_FOOTER) {
          createMultiButtonType()
        } else if (dialogBuilder.mButtonViewType == SGDialogBuilder.SINGAL_LINE_FOOTER) {
          createLineButtonType()
        } else {
          createNormalContentView()
        }
      } else {
        dialogBuilder.mFooterView
      }
      flFooter.addView(view)
    }

    val color = Color.parseColor("#ffffff")
    val sgButtonDrawable = SGButtonDrawable()
    val colorStateList = SGButtonDrawable.getColorStateList(-1, -1, -1, -1, -1, -1, color)
    sgButtonDrawable.setBgData(colorStateList)
    sgButtonDrawable.setIsRadiusAdjustBounds(false)
    sgButtonDrawable.cornerRadius = (dialogBuilder.mRadius).toFloat()
    rootView.setBackgroundDrawable(sgButtonDrawable)
  }

  /**
   * 创建一行一个button的底部样式
   * */
  private fun createLineButtonType(): View {
    val view = View.inflate(context, R.layout.sgkit_tip_dialog_single_footer, null)
    val primaryButton = view.findViewById<SGButton>(R.id.sgkit_primaryButton)
    val assistButton = view.findViewById<SGButton>(R.id.sgkit_assistButton)
    if (dialogBuilder.mPrimaryText != null) {
      primaryButton.setText(dialogBuilder.mPrimaryText)
      dialogBuilder.mPrimaryStyle?.let {
        primaryButton.setButtonData(it).build()
      }
    } else {
      primaryButton.visibility = View.GONE
    }
    if (dialogBuilder.mAssistText != null) {
      assistButton.setText(dialogBuilder.mAssistText)
      dialogBuilder.mAssistStyle?.let {
        assistButton.setButtonData(it).build()
      }
    }
    return view
  }

  /**
   * 创建多个按钮的底部view
   * */
  private fun createMultiButtonType(): View {
    val view = View.inflate(context, R.layout.sgkit_listview_content, null)
    val listView = view.findViewById<ListView>(R.id.lv_dialogFooterListview)
    listView.adapter = DialogButtonListAdapter(context,
      dialogBuilder.mButtonList ?: ArrayList(),
      dialogBuilder.buttonStyleList)
    listView.onItemClickListener = OnItemClickListener { parent, view, position, id ->
      dialogBuilder.mButtonListListener?.onItemListener(dialogBuilder.mButtonList?.get(position) ?: "", position)
      if (dialogBuilder.mAutoDismiss) {
        this@SGDialog.dismiss()
      }
    }
    return view
  }

  /**
   * 创建一个普通的textview的内容
   * */
  private fun createNormalContentView(): View {
    val textView = TextView(context)
    textView.text = dialogBuilder.mContent
    dialogBuilder.mCustomTypeface?.let {
      textView.setTypeface(it)
    }
    textView.setTextColor(dialogBuilder.mContentColor)
    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dialogBuilder.mContentTextSize)
    return textView
  }

  /**
   *创建一个图片内容的区域
   * */
  private fun createImageContentView(): View {
    val imageView = ImageView(context)
    imageView.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
    if (dialogBuilder.mImageResource != 0) {
      imageView.setImageResource(dialogBuilder.mImageResource)
    } else if (dialogBuilder.mImageDrawable != null) {
      imageView.setImageDrawable(dialogBuilder.mImageDrawable)
    }
    return imageView
  }

  /**
   * 创建一个可编辑的内容区域
   * */
  private fun createEditContentView(): View {
    val footer = View.inflate(context, layout.sgkit_edit_content_dialog, null)
    footer.findViewById<EditText>(R.id.et_contentDialogSgKit)
    footer.findViewById<EditText>(R.id.tv_contentDialogSgKit)

    return footer
  }

  /**
   * 设置左右普通样式的弹框样式
   * */
  private fun createNormalButtonType(): View {
    val footer = View.inflate(context, layout.sgkit_tip_dialog_footer, null)
    val exdConfirm = footer.findViewById<TextView>(id.exd_confirm)
    val exdCancel = footer.findViewById<TextView>(id.exd_cancel)
    val dividerVertical = footer.findViewById<View>(id.view_dividerVertical)
    val dividerHorizontal = footer.findViewById<View>(id.view_dividerHorizontal)

    //构建底部确定和取消
    if (dialogBuilder.isSingle) {
      exdCancel.visibility = View.GONE
      dividerVertical.visibility = View.GONE
    }

    //初始化左边的按钮
    if (dialogBuilder.mLeftContent.isNotEmpty()) {
      exdCancel.setText(dialogBuilder.mLeftContent)
      dialogBuilder.mCustomTypeface?.let {
        exdCancel.setTypeface(it)
      }
    }

    if (dialogBuilder.mLeftContentColor != -1) {
      exdCancel.setTextColor(dialogBuilder.mLeftContentColor)
      exdCancel.setTextSize(TypedValue.COMPLEX_UNIT_PX, dialogBuilder.mLeftContentTextSize)
    }

    //初始化右边的按钮
    if (dialogBuilder.mRightContent.isNotEmpty()) {
      exdConfirm.setText(dialogBuilder.mRightContent)
      dialogBuilder.mCustomTypeface?.let {
        exdConfirm.setTypeface(it)
      }
    }
    if (dialogBuilder.mRightContentColor != -1) {
      exdConfirm.setTextColor(dialogBuilder.mRightContentColor)
      exdConfirm.setTextSize(TypedValue.COMPLEX_UNIT_PX, dialogBuilder.mRightContentTextSize)
    }
    //设置确定和取消之前线的颜色
    if (dialogBuilder.mDividerColor != -1) {
      dividerVertical.setBackgroundColor(dialogBuilder.mDividerColor)
      dividerHorizontal.setBackgroundColor(dialogBuilder.mDividerColor)
    }
    exdConfirm.setOnClickListener(this)
    exdCancel.setOnClickListener(this)
    return footer
  }

  override fun onClick(v: View?) {
    v?.let {
      when (it.id) {
        R.id.exd_cancel -> {
          if (dialogBuilder.mAutoDismiss) {
            dismiss()
          }
          dialogBuilder.mLeftListener?.onClick(it)
        }
        R.id.exd_confirm -> {
          if (dialogBuilder.mAutoDismiss) {
            dismiss()
          }
          dialogBuilder.mRightListener?.onClick(it)
        }
        else -> {
        }
      }

    }
  }
}