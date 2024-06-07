package com.sungrowpower.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.sungrowpower.demo.databinding.ActivitySgcountdownBinding;
import com.sungrowpower.kit.countdown.SGCountDown;

public class SGCountDownActivity extends AppCompatActivity {

    private static final String TAG = "SGCountDownActivity";
    private ActivitySgcountdownBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_sgcountdown);
        dataBinding.setLifecycleOwner(this);

        initListener();
    }

    private void initListener() {
        //自定义格式
        dataBinding.sgcCountDownCustom.setOnCountDownListener(new SGCountDown.OnCountDownChangeListener() {
            @Override
            public String onTick(String days, String hours, String minutes, String seconds) {
                return days + "days," + hours + "小时" + minutes + "分钟" + seconds + "秒";
            }

            @Override
            public void onFinish() {

            }
        });
        //自定义样式
        dataBinding.sgcCountDownCustomStyle.setCustomView(R.layout.layout_countdown_custom_style);
        TextView tvDay = dataBinding.sgcCountDownCustomStyle.getCustomView().findViewById(R.id.tv_day);
        TextView tvHour = dataBinding.sgcCountDownCustomStyle.getCustomView().findViewById(R.id.tv_hour);
        TextView tvMinute = dataBinding.sgcCountDownCustomStyle.getCustomView().findViewById(R.id.tv_minute);
        TextView tvSecond = dataBinding.sgcCountDownCustomStyle.getCustomView().findViewById(R.id.tv_second);
        dataBinding.sgcCountDownCustomStyle.setOnCountDownListener(new SGCountDown.OnCountDownChangeListener() {
            @Override
            public String onTick(String days, String hours, String minutes, String seconds) {
                tvDay.setText(days);
                tvHour.setText(hours);
                tvMinute.setText(minutes);
                tvSecond.setText(seconds);
                return "";
            }

            @Override
            public void onFinish() {
                tvDay.setText("已");
                tvHour.setText("经");
                tvMinute.setText("结");
                tvSecond.setText("束");
            }
        });

        Log.d(TAG, "initListener: " + System.currentTimeMillis());

        //手动控制
        dataBinding.tvStart.setOnClickListener(v -> {
            dataBinding.tvStart.setVisibility(View.GONE);
            dataBinding.tvPause.setVisibility(View.VISIBLE);
            dataBinding.sgcCountDown.start();
        });

        dataBinding.tvPause.setOnClickListener(v -> {
            dataBinding.tvStart.setVisibility(View.VISIBLE);
            dataBinding.tvPause.setVisibility(View.GONE);
            dataBinding.sgcCountDown.pause();
        });

        dataBinding.tvReset.setOnClickListener(v -> {
            dataBinding.tvStart.setVisibility(View.VISIBLE);
            dataBinding.tvPause.setVisibility(View.GONE);
            dataBinding.sgcCountDown.reset();
        });

        //未来时间点
        long future = System.currentTimeMillis() + 200000;
        dataBinding.sgcCountDownFuture.setFutureMillisecond(future);
        dataBinding.sgcCountDownFuture.start();
    }
}