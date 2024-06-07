package com.sungrowpower.demo;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sungrowpower.demo.databinding.ActivitySgellipsisBinding;

public class SGEllipsisActivity extends AppCompatActivity {

    private static final String TAG = "SGEllipsisActivity";
    private ActivitySgellipsisBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_sgellipsis);
        dataBinding.setLifecycleOwner(this);

        initListener();
    }

    private void initListener() {
        String str = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        dataBinding.seEllipsis2.setText(str);
        dataBinding.seEllipsis2.setOnEllipsisClickListener(isCollapsed -> Log.d(TAG, "onEllipsisClick: " + isCollapsed));

        String text = getString(R.string.hello_world_times);
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.sgkit_brand_routine));
        builder.setSpan(colorSpan,10,text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        dataBinding.seEllipsisRich.setText(builder);
        dataBinding.seEllipsisRich.setEllipsisMaxLines(2);
    }
}