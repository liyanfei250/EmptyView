package com.sungrowpower.demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import com.sungrowpower.demo.databinding.MainActivityBinding;
import com.sungrowpower.kit.SGKit;
import com.sungrowpower.kit.util.SGUILog;
import com.sungrowpower.tabbar.TabBarActivity;

public class MainActivity extends AppCompatActivity {

  private MainActivityBinding viewDataBinding;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SGKit.INSTANCE.init(this.getApplication());
    viewDataBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
    viewDataBinding.setLifecycleOwner(this);
    initLog();
    initAction();
  }

  private void initLog() {
    SGUILog.setDelegete(new SGUILog.SGKitLogDelegate() {
      @Override
      public void e(String tag, String msg, Object... obj) {
        Log.e(tag, msg);
      }

      @Override
      public void w(String tag, String msg, Object... obj) {
        Log.w(tag, msg);
      }

      @Override
      public void i(String tag, String msg, Object... obj) {
        Log.i(tag, msg);
      }

      @Override
      public void d(String tag, String msg, Object... obj) {
        Log.d(tag, msg);
      }

      @Override
      public void printErrStackTrace(String tag, Throwable tr, String format, Object... obj) {

      }
    });
  }

  private void initAction() {
    //按钮的展示页面
    viewDataBinding.tvButton.setOnClickListener(v -> {
      startActivity(new Intent(this, SGButtonActivity.class));
    });
    //折叠面板的展示页面
    viewDataBinding.tvCollapse.setOnClickListener(v -> {
      startActivity(new Intent(this, SGCollapseActivity.class));
    });
    viewDataBinding.tvCheckbox.setOnClickListener(v -> {
      startActivity(new Intent(this, SGCheckBoxActivity.class));
    });
    viewDataBinding.tvDialog.setOnClickListener(v -> {
      startActivity(new Intent(this, SGDialogActivity.class));
    });
    //倒计时的展示页面
    viewDataBinding.tvCountdown.setOnClickListener(v -> {
      startActivity(new Intent(this, SGCountDownActivity.class));
    });

    //空状态的展示页面
    viewDataBinding.tvEmpty.setOnClickListener(v -> {
      startActivity(new Intent(this, SGEmptyActivity.class));
    });
    
    //文本缩略的展示页面
    viewDataBinding.tvEllipsis.setOnClickListener(v -> {
      startActivity(new Intent(this, SGEllipsisActivity.class));
    });

  //标签栏
    viewDataBinding.tvTabbar.setOnClickListener(v -> {
      startActivity(new Intent(this, TabBarActivity.class));
    });
  }
}
