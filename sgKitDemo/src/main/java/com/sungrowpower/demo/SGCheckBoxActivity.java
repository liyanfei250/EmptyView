package com.sungrowpower.demo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sungrowpower.demo.databinding.CheckBoxActivityBinding;

/**
 * 复选框
 *
 * @author liuyuan1
 * @date 2023/7/15
 */
public class SGCheckBoxActivity extends AppCompatActivity {
    private CheckBoxActivityBinding viewDataBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.check_box_activity);
        viewDataBinding.setLifecycleOwner(this);
        //选中
        viewDataBinding.sgkitCbSelect.setSelected(true);
        //禁用选中
        viewDataBinding.sgkitCbDisableSelect.setSelected(true);
        viewDataBinding.sgkitCbDisableSelect.setEnabled(false);
        //禁用未选中
        viewDataBinding.sgkitCbDisableUnselect.setSelected(false);
        viewDataBinding.sgkitCbDisableUnselect.setEnabled(false);
        viewDataBinding.sgkitCbRightDisable.setEnabled(false);
    }
}
