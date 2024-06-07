package com.sungrowpower.kit.fonticon;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Html;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.sungrowpower.kit.R;

public class SGFontIcon extends AppCompatTextView {
  private boolean isHtml = true;

  public SGFontIcon(Context context) {
    super(context);
    init(context, null, 0);
  }

  public SGFontIcon(Context context, AttributeSet attrs) {
    super(context, attrs);

    init(context, attrs, 0);
  }

  public SGFontIcon(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs, defStyleAttr);
  }

  private void init(Context context, AttributeSet attrs, int defStyleAttr) {
    TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SGFontIcon, defStyleAttr, 0);
    isHtml = typedArray.getBoolean(R.styleable.SGFontIcon_sgkit_isHtml, true);
    typedArray.recycle();
    if (isInEditMode()) {

    } else {
      this.setTypeface(Typeface.createFromAsset(context.getAssets(), "icon/sgkit_iconfont.ttf"));
    }
  }

  @Override
  public void setText(CharSequence text, BufferType type) {
    if (isHtml && text != null && text.toString().contains("&#x")) {
      super.setText(Html.fromHtml(text.toString()));
    } else {
      super.setText(text, type);
    }
  }
}