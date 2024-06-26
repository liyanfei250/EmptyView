# EmptyView
Android空占位图

终于有时间把以往项目中的复用模块抽出来，封装成UI库，供大家学习使用！

直接来看看空布局的界面效果：

![image](https://github.com/liyanfei250/EmptyView/assets/16755185/ac6cccdc-8291-4d78-87e8-ae0c7862a766)

基本的布局就是以下结构：

![image](https://github.com/liyanfei250/EmptyView/assets/16755185/4003011d-2db9-45dd-bf4f-25ace07b10ee)


xml代码布局如下：

```
<?xml version="1.0" encoding="utf-8"?>
<merge xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  >
  <LinearLayout
    android:id="@+id/empty_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:padding="24dp"
    android:visibility="visible"
    >

    <ImageView
      android:id="@+id/empty_image"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:layout_gravity="center_horizontal"
      android:layout_marginBottom="10dp"
      />

    <TextView
      android:id="@+id/empty_title"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:layout_gravity="center_horizontal"
      android:layout_marginTop="8dp"
      android:gravity="center"
      />

    <TextView
      android:id="@+id/empty_text"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:layout_gravity="center_horizontal"
      android:layout_marginStart="28dp"
      android:layout_marginTop="8dp"
      android:layout_marginEnd="20dp"
      android:textSize="12sp"
      android:gravity="center"
      />

    <com.sungrowpower.kit.button.SGButton
      android:id="@+id/empty_button"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:layout_marginTop="4dp"
      android:gravity="center"
      android:textColor="#ffffffff"
      android:text="默认文字"
      android:textAllCaps="false"
      app:sgkit_shapeRadius="30dp"
      app:sgkit_shapeSolidColor="#FF8100"
      />
  </LinearLayout>
</merge>
```
基本使用：

```
<com.sungrowpower.kit.empty.SGEmptyView
  android:id="@+id/empty_view"
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  />
```

```
<com.sungrowpower.kit.empty.SGEmptyView
  android:id="@+id/empty_view_five"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  app:ev_buttonHeight="30dp"
  app:ev_buttonMarginTop="4dp"
  app:ev_buttonRadius="50dp"
  app:ev_buttonTextPaddingBottom="6dp"
  app:ev_buttonTextPaddingLeft="20dp"
  app:ev_buttonTextPaddingRight="20dp"
  app:ev_buttonTextPaddingTop="6dp"
  app:ev_buttonTextSize="14sp"
  app:ev_emptyDrawableMarginTop="10dp"
  app:ev_empty_backgroundColor="#FFFFFFFF"
  app:ev_empty_button="Create Now"
  app:ev_empty_buttonBackgroundColor="@color/sgkit_brand_routine"
  app:ev_empty_buttonTextColor="#FFFFFFFF"
  app:ev_empty_drawable="@drawable/sgkit_no_data"
  app:ev_empty_drawableTint="#FFFFFF"
  app:ev_empty_title="暂无数据"
  app:ev_empty_titleTextColor="@color/sgkit_text_dark_gray"
  app:ev_imageHeight="120dp"
  app:ev_imageWidth="120dp"
  app:ev_image_scaleType="centerCrop"
  app:ev_textButtonMarginTop="1dp"
  app:ev_empty_textSize="12sp"
  app:ev_titleTextMarginTop="1dp"
  app:ev_empty_titleTextSize="14sp"
  />
```

代码中使用：

```
/**
 *设置按钮格式
 * */
//emptyView.getButton().setBorderWidth(4).setRadius(50).setSolidColor(ContextCompat.getColor(this,R.color.sgkit_fill_white)).setDefaultStateTextColor(ContextCompat.getColor(this,R.color.sgkit_text_dark_gray)).setStrokeColor(ContextCompat.getColor(this,R.color.sgkit_success_routine)).build();
/**
 *设置空布局代码
 * */
emptyView.error()
    .setEmptyDrawable(R.drawable.sgkit_no_data)
    .setImageHeight(dpToPx(120))
    .setImageWidth(dpToPx(120))
    .setEmptyTitle("暂无数据")
    .setTitleColor(ContextCompat.getColor(this, R.color.sgkit_text_dark_gray))
    .setButtonText("重新获取")
    .setButtonTextSize(spToPx(14))
    .setButtonTextColor(ContextCompat.getColor(this, R.color.sgkit_text_title))
    .setButtonBackColor(ContextCompat.getColor(this, R.color.sgkit_brand_routine))
    .setButtonRadius(50)
    .setButtonHeight(dpToPx(30))
    .setButtonTextPaddingTop(dpToPx(1))
    .setButtonTextPaddingBottom(dpToPx(1))
    .setButtonTextPaddingLeft(dpToPx(30))
    .setButtonTextPaddingRight(dpToPx(30))
    .setOnClickListener(v -> Log.e("emptyView===", "setOnClickListener"))
    .show();
```

```
/**
 *设置空布局实体类数据
 * */
EmptyDataBean bean = new EmptyDataBean();
bean.mTopBackDrawable = getResources().getDrawable(R.drawable.sgkit_no_data);
bean.mTitleContent = "默认的实体类";
bean.mTitleColor = 0xFFCC3032;
bean.hasTextView = false;
bean.hasButtonView = false;
emptyViewTwo.setEmptyData(bean);
```


# 空状态使用说明

## EmptyViewBuilder 方法

| 方法名                                                   | 描述                       | 返回类型         | 默认值 |
| -------------------------------------------------------- | -------------------------- | ---------------- | ------ |
| setState(int state)                                      | 设置空布局状态             | EmptyViewBuilder | -1     |
| setOnClickListener(View.OnClickListener onClickListener) | 设置点击事件               | EmptyViewBuilder | \-     |
| setEmptyTitle(CharSequence emptyTitle)                   | 设置一级文字内容           | EmptyViewBuilder | \-     |
| setTitleColor(int titleColor)                            | 设置一级文字颜色           | EmptyViewBuilder | \-     |
| setEmptyText(CharSequence emptyText)                     | 设置二级文字内容           | EmptyViewBuilder | \-     |
| setButtonText(CharSequence buttonText)                   | 设置按钮文字               | EmptyViewBuilder | \-     |
| setButtonTextColor(int buttonTextColor)                  | 设置按钮文字颜色           | EmptyViewBuilder | \-     |
| setButtonTextSize(int buttonTextSize)                    | 设置按钮文字大小           | EmptyViewBuilder | 16dp   |
| setButtonBackColor(int buttonBack)                       | 设置按钮背景色             | EmptyViewBuilder | \-     |
| setButtonBean(ButtonDataBean buttonDataBean)             | 设置按钮实体类             | EmptyViewBuilder | \-     |
| setEmptyDrawable(Drawable emptyDrawable)                 | 设置顶部图片               | EmptyViewBuilder | \-     |
| setButtonWidth(int width)                                | 设置按钮宽度               | EmptyViewBuilder | \-     |
| setButtonHeight(int height)                              | 设置按钮高度               | EmptyViewBuilder | \-     |
| setEmptyDrawableMarginTop(int marginTop)                 | 设置图片到顶部的距离       | EmptyViewBuilder | \-     |
| setTitleTextMarginTop(int marginTop)                     | 设置一级文字到顶部的距离   | EmptyViewBuilder | \-     |
| setTextMarginTop(int marginTop)                          | 设置二级文字到顶部的距离   | EmptyViewBuilder | \-     |
| setButtonMarginTop(int marginTop)                        | 设置按钮距离底部的距离     | EmptyViewBuilder | \-     |
| setButtonTextPaddingTop(int paddingTop)                  | 设置按钮文字距离顶部的距离 | EmptyViewBuilder | \-     |
| setButtonRadius(int buttonRadius)                        | 设置按钮圆角弧度           | EmptyViewBuilder | \-     |
| setButtonTextPaddingLeft(int paddingLeft)                | 设置按钮文字距离左侧的距离 | EmptyViewBuilder | \-     |
| setButtonTextPaddingRight(int paddingRight)              | 设置按钮文字距离右部的距离 | EmptyViewBuilder | \-     |
| setButtonTextPaddingBottom(int paddingBottom)            | 设置按钮文字距离底部的距离 | EmptyViewBuilder | \-     |
| setImageWidth(int width)                                 | 设置图片宽度值             | EmptyViewBuilder | \-     |
| setImageHeight(int height)                               | 设置图片高度值             | EmptyViewBuilder | \-     |
| setImageScaleType(ImageView.ScaleType scaleType)         | 设置图片格式               | EmptyViewBuilder | \-     |
| show()                                                   | 显示空布局                 | void             | \-     |
| setDefaultStyle(EmptyViewBuilder builder)                | 设置全局默认的数据         | EmptyViewBuilder | \-     |

## SGEmptyView 方法

| 方法名                             | 描述                 | 返回类型         | 默认值                         |
| ---------------------------------- | -------------------- | ---------------- | ------------------------------ |
| error()                            | 设置网络问题空布局   | void             | EmptyViewBuilder.ERROR         |
| empty_list()                       | 设置列表无数据空布局 | void             | EmptyViewBuilder.NODATE_LIST   |
| empty_search()                     | 设置搜索无数据空布局 | void             | EmptyViewBuilder.NODATE_SEARCH |
| builder()                          | 获取辅助类           | EmptyViewBuilder | builder                        |
| getButton()                        | 拿到按钮实例         | SGButton         | \-                             |
| setButtonData(ButtonDataBean data) | 设置按钮信息         | void             | \-                             |
| setEmptyData(EmptyDataBean data)   | 设置空布局实体类     | SGEmptyView      | \-                             |

## Attributes 属性

| 属性                           | 描述                         | 类型      | 默认值    |
| ------------------------------ | ---------------------------- | --------- | --------- |
| ev_buttonHeight                | 空布局底部按钮高度           | dimension | \-        |
| ev_buttonMarginTop             | 空布局底部按钮顶部外边距     | dimension | 10dip     |
| ev_buttonRadius                | 空布局底部按钮圆角弧度       | dimension | 30dip     |
| ev_buttonTextPaddingBottom     | 空布局底部按钮底部文字内边距 | dimension | \-        |
| ev_buttonTextPaddingLeft       | 空布局底部按钮左侧文字内边距 | dimension | \-        |
| ev_buttonTextPaddingRight      | 空布局底部按钮右侧文字内边距 | dimension | \-        |
| ev_buttonTextPaddingTop        | 空布局底部按钮顶部文字内边距 | dimension | \-        |
| ev_buttonTextSize              | 空布局底部按钮文字大小       | dimension | true      |
| ev_emptyDrawableMarginTop      | 图片距离顶部的距离           | dimension | 16dip     |
| ev_empty_backgroundColor       | 空布局背景颜色               | color     | \-        |
| ev_empty_button                | 空布局按钮文字内容           | string    | \-        |
| ev_empty_buttonBackgroundColor | 空布局按钮背景颜色           | color     | \-        |
| ev_empty_buttonTextColor       | 空布局按钮文字颜色           | color     | #ffffffff |
| ev_empty_drawable              | 空布局图片                   | reference | \-        |
| ev_empty_text                  | 空布局二级文字               | string    | \-        |
| ev_empty_textColor             | 空布局二级文字颜色           | color     | \-        |
| ev_empty_title                 | 空布局一级文字               | string    | \-        |
| ev_empty_titleTextColor        | 空布局一级文字颜色           | color     | \-        |
| ev_imageHeight                 | 空布局图片高度               | dimension | \-        |
| ev_imageWidth                  | 空布局图片宽度               | dimension | \-        |
| ev_image_scaleType             | 空布局图片格式               | enum      | \-        |
| ev_textButtonMarginTop         | 空布局底部按钮距离顶部的距离 | dimension | \-        |
| ev_empty_titleTextSize         | 空布局一级文字大小           | dimension | 17dip     |
| ev_empty_textSize              | 空布局二级文字大小           | dimension | 14dip     |
| ev_titleTextMarginTop          | 空布局一级文字距离顶部的距离 | dimension | 18dip     |

