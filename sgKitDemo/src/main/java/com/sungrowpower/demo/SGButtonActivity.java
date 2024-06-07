package com.sungrowpower.demo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.sungrowpower.demo.databinding.ButtonActivityBinding;

public class SGButtonActivity extends AppCompatActivity {

  private ButtonActivityBinding viewDataBinding;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewDataBinding = DataBindingUtil.setContentView(this, R.layout.button_activity);
    viewDataBinding.setLifecycleOwner(this);

    initAction();
  }

  private void initAction() {
    viewDataBinding.sgPressed.setPressed(true);
    viewDataBinding.sgAuxiliaryPress.setPressed(true);
    viewDataBinding.sgWireframe.setPressed(true);
  }
}
