package com.sungrowpower.demo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.sungrowpower.kit.button.ButtonDataBean;
import com.sungrowpower.kit.empty.EmptyDataBean;
import com.sungrowpower.kit.empty.SGEmptyView;
import com.sungrowpower.util.common.LogUtil;
import com.sungrowpower.util.common.ToastUtil;

public class SGEmptyActivity extends AppCompatActivity {

  private SGEmptyView emptyView;
  private SGEmptyView emptyViewTwo;
  private SGEmptyView emptyViewThree;
  private SGEmptyView emptyViewFour;
  private SGEmptyView emptyViewFive;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.empty_activity);
    emptyView = findViewById(R.id.empty_view);
    emptyViewTwo = findViewById(R.id.empty_view_two);
    emptyViewThree = findViewById(R.id.empty_view_three);
    emptyViewFour = findViewById(R.id.empty_view_four);
    emptyViewFive = findViewById(R.id.empty_view_five);
    initAction();
  }

  private void initAction() {
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
        //.setButtonText("重新获取")
        //.setButtonTextSize(spToPx(14))
        //.setButtonTextColor(ContextCompat.getColor(this, R.color.sgkit_text_title))
        //.setButtonBackColor(ContextCompat.getColor(this, R.color.sgkit_brand_routine))
        //.setButtonRadius(50)
        //.setButtonHeight(dpToPx(30))
        //.setButtonTextPaddingTop(dpToPx(1))
        //.setButtonTextPaddingBottom(dpToPx(1))
        //.setButtonTextPaddingLeft(dpToPx(30))
        //.setButtonTextPaddingRight(dpToPx(30))
        //.setOnClickListener(v -> Log.e("emptyView===", "setOnClickListener"))
        .show();

    /**
     *设置空布局实体类数据
     * */
    //EmptyDataBean bean = new EmptyDataBean();
    //bean.mTopBackDrawable = getResources().getDrawable(R.drawable.sgkit_no_data);
    //bean.mTitleContent = "默认的实体类";
    //bean.mTitleColor = 0xFFCC3032;
    //bean.hasTextView = false;
    //bean.hasButtonView = false;
    //emptyViewTwo.setEmptyData(bean);

    emptyViewFive.getButton().setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
            Log.e("","空布局按钮点击");
      }
    });
  }

  public int spToPx(float sp) {
    float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
    return (int) (sp * scaledDensity + 0.5f);
  }

  public int dpToPx(float dp) {
    float density = getResources().getDisplayMetrics().density;
    return (int) (dp * density + 0.5f);
  }
}