package com.sungrowpower.demo;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.sungrowpower.demo.databinding.DialogActivityBinding;
import com.sungrowpower.kit.dialog.SGDialogBuilder;

public class SGDialogActivity extends AppCompatActivity {
  private DialogActivityBinding viewDataBinding;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewDataBinding = DataBindingUtil.setContentView(this, R.layout.dialog_activity);
    viewDataBinding.setLifecycleOwner(this);
    viewDataBinding.dialogLongText.setOnClickListener(v -> {
      new SGDialogBuilder()
          .content("alsdflkj")
          .title("title")
          .singleAction(true)
          .contentMargin()
          .contentColor(Color.parseColor("#000000"))
          .dividerColor(getResources().getColor(R.color.sgkit_divider_color))
          .titleColor(Color.parseColor("#000000"))
          .singleActionButtonContent("确定")
          .singleActionColor(Color.parseColor("#ff7300"))
          .show(SGDialogActivity.this);
    });
  }
}
