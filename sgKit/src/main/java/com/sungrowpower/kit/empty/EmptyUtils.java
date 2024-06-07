package com.sungrowpower.kit.empty;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

@SuppressLint("ResourceType") final class EmptyUtils {
  static Drawable getDrawable(Context context, @DrawableRes int id) {
    return id <= 0 ? null : ContextCompat.getDrawable(context, id);
  }

  static String getString(Context context, @StringRes int id) {
    return id <= 0 ? null : context.getString(id);
  }

  static void setTextSize(TextView textView, float textSize) {
    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
  }
}
