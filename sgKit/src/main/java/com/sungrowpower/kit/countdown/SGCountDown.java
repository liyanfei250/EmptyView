package com.sungrowpower.kit.countdown;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import com.sungrowpower.kit.R;

/**
 * 倒计时
 */
public class SGCountDown extends LinearLayout {

    /**
     * 总时长
     */
    private long totalTime;

    /**
     * 剩余总时长
     */
    private long remainingTotalTime;

    /**
     * 文字颜色
     */
    private final int textColor;

    /**
     * 文字大小
     */
    private final int textSize;

    private TextView tvTimer;

    /**
     * 自定义View
     */
    private View customView;

    /**
     * 倒计时 默认类型4 单位秒 时间类型{@link TimeDataType}
     */
    private int timeDataType;

    /**
     * 倒计时时长 根据类型计算{@link TimeDataType}
     */
    private int timeDuration;

    /**
     * 自定义格式
     */
    private int timeFormat;

    /**
     * 是否自动开始倒计时 默认false
     */
    private boolean isAutoStart;

    /**
     * 是否开启毫秒级渲染 默认false
     */
    private boolean isMillisecond;

    /**
     * 是否在倒计时中 默认false
     */
    private boolean isTicking = false;
    private SGCountDownHelper countDownHelper;

    private OnCountDownChangeListener onCountDownChangeListener;

    public SGCountDown(Context context) {
        this(context, null);
    }

    public SGCountDown(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SGCountDown(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SGCountDown(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setOrientation(LinearLayout.HORIZONTAL);
        SGCountdownDataBean dataBean = new SGCountdownDataBean();
        int padding = getResources().getDimensionPixelSize(R.dimen.px48);
        setPadding(padding, padding, padding, padding);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SGCountDown, defStyleAttr, 0);

        timeDataType = a.getInt(R.styleable.SGCountDown_sgkit_timeDataType, dataBean.getTimeDataType());
        timeDuration = a.getInt(R.styleable.SGCountDown_sgkit_time, dataBean.getTimeDuration());
        timeFormat = a.getInt(R.styleable.SGCountDown_sgkit_format,dataBean.getTimeFormat());
        isAutoStart = a.getBoolean(R.styleable.SGCountDown_sgkit_autoStart, dataBean.isAutoStart());
        isMillisecond = a.getBoolean(R.styleable.SGCountDown_sgkit_millisecond, dataBean.isMillisecond());

        textColor = a.getColor(R.styleable.SGCountDown_sgkit_textColor, dataBean.getTextColor());
        textSize = a.getDimensionPixelSize(R.styleable.SGCountDown_sgkit_textSize, dataBean.getTextSize());

        initDefaultValue();
        initView();
        a.recycle();
    }

    private void initDefaultValue() {
        addView(defaultTimeView());
    }

    private void initView() {
        initTotalTime();
        initCountDownHelper();
        if (isAutoStart) {
            start();
        }
    }

    private void initCountDownHelper() {
        long interval = 1000;
        if (isMillisecond) {
            interval = 30;
        }
        countDownHelper = new SGCountDownHelper(remainingTotalTime, interval, timeFormat, isMillisecond);
        countDownHelper.setOnCountDownListener(new SGCountDownHelper.OnCountDownListener() {
            @Override
            public void onTick(long remainingTime, String days, String hours, String minutes, String seconds) {
                remainingTotalTime = remainingTime;
                if (tvTimer != null) {
                    StringBuilder s = new StringBuilder();
                    if (!TextUtils.isEmpty(days)){
                        s.append(days).append("days");
                    }
                    if (!TextUtils.isEmpty(hours)){
                        s.append(hours).append(":");
                    }
                    if (!TextUtils.isEmpty(minutes)){
                        s.append(minutes).append(":");
                    }
                    if (!TextUtils.isEmpty(seconds)){
                        s.append(seconds);
                    }
                    tvTimer.setText(s);
                }
                if (onCountDownChangeListener != null) {
                    String text = onCountDownChangeListener.onTick(days, hours, minutes, seconds);
                    tvTimer.setText(text);
                }
            }

            @Override
            public void onFinish() {
                if (onCountDownChangeListener != null) {
                    onCountDownChangeListener.onFinish();
                }
            }
        });

        countDownHelper.formatTime(remainingTotalTime);
    }

    private void initTotalTime() {
        switch (timeDataType) {
            case TimeDataType.DAYS:
                totalTime = SGCountDownHelper.oneDay * timeDuration;
                break;
            case TimeDataType.HOURS:
                totalTime = SGCountDownHelper.oneHour * timeDuration;
                break;
            case TimeDataType.MINUTES:
                totalTime = SGCountDownHelper.oneMinute * timeDuration;
                break;
            case TimeDataType.MILLISECONDS:
                totalTime = timeDuration;
                break;
            default:
                totalTime = SGCountDownHelper.oneSecond * timeDuration;
                break;
        }
        remainingTotalTime = totalTime;
    }

    private TextView defaultTimeView(){
        tvTimer = new TextView(getContext());
        tvTimer.setTextColor(textColor);
        tvTimer.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        return tvTimer;
    }

    /**
     * 设置自定义View
     *
     * @param customView 自定义View 为null展示默认的
     */
    public void setCustomView(View customView) {
        isAutoStart = false;
        this.customView = customView;
        if (customView == null){
            return;
        }
        removeAllViews();
        addView(customView);
    }

    /**
     * 获取自定义View
     *
     * @return 自定义View
     */
    public View getCustomView() {
        return customView;
    }

    /**
     * 设置自定义View
     *
     * @param layoutCustomView 自定义布局
     */
    public void setCustomView(@LayoutRes int layoutCustomView) {
        setCustomView(LayoutInflater.from(getContext()).inflate(layoutCustomView, this, false));
    }

    /**
     * 设置字体颜色
     *
     * @param textColor 字体颜色
     */
    public void setTextColor(int textColor) {
        if (tvTimer != null && textColor != 0) {
            tvTimer.setTextColor(textColor);
        }
    }

    /**
     * 设置字体大小
     *
     * @param textSize 字体大小
     */
    public void setTextSize(int textSize) {
        if (tvTimer != null && textSize != 0) {
            tvTimer.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
    }

    /**
     * 设置是否开启毫秒级渲染 需在开始倒计时{@link #start()}之前调用
     *
     * @param isMillisecond 是否开启毫秒级渲染
     */
    public void setMillisecond(boolean isMillisecond) {
        if (isAutoStart) {
            return;
        }
        this.isMillisecond = isMillisecond;
        initCountDownHelper();
    }

    /**
     * 设置倒计时时长 需在开始倒计时{@link #start()}之前调用
     *
     * @param timeDuration 倒计时时长
     */
    public void setTimeDuration(int timeDuration) {
        if (isAutoStart) {
            return;
        }
        this.timeDuration = timeDuration;
        initTotalTime();
        initCountDownHelper();
    }

    /**
     * 设置时间类型 需在开始倒计时{@link #start()}之前调用
     *
     * @param timeDataType 时间类型
     */
    public void setTimeDataType(int timeDataType) {
        if (isAutoStart) {
            return;
        }
        this.timeDataType = timeDataType;
        initTotalTime();
        initCountDownHelper();
    }

    /**
     * 设置需要展示的时间类型 {@link FormatType}
     *
     * @param timeFormat 需要展示的时间类型
     */
    public void setTimeFormat(int timeFormat) {
        if (isAutoStart) {
            return;
        }
        this.timeFormat = timeFormat;
        initTotalTime();
        initCountDownHelper();
    }

    /**
     * 设置未来某个时间点的时间戳 单位毫秒ms 需在开始倒计时{@link #start()}之前调用
     *
     * @param futureMillisecond 未来某个时间点的时间戳
     */
    public void setFutureMillisecond(long futureMillisecond) {
        if (isAutoStart) {
            return;
        }
        long timeMillis = futureMillisecond - System.currentTimeMillis();
        if (timeMillis <= 0) {
            return;
        }
        totalTime = timeMillis;
        timeDuration = (int) timeMillis;
        remainingTotalTime = totalTime;
        initCountDownHelper();
    }

    /**
     * 根据实体类数据设置
     *
     * @param dataBean 实体类数据
     */
    public void setDataBean(SGCountdownDataBean dataBean){
        setTextColor(dataBean.getTextColor());
        setTextSize(dataBean.getTextSize());
        setMillisecond(dataBean.isMillisecond());
        setTimeDataType(dataBean.getTimeDataType());
        setTimeDuration(dataBean.getTimeDuration());
        setTimeFormat(dataBean.getTimeFormat());
    }

    /**
     * 开始倒计时
     */
    public void start() {
        if (isTicking || timeDuration == 0) {
            return;
        }
        isTicking = true;
        if (countDownHelper != null) {
            countDownHelper.start();
        }
    }

    /**
     * 暂停倒计时
     */
    public void pause() {
        isAutoStart = false;
        cancel();
        initCountDownHelper();
    }

    /**
     * 重置倒计时
     */
    public void reset() {
        cancel();
        remainingTotalTime = totalTime;
        initCountDownHelper();
    }

    /**
     * 取消倒计时
     */
    public void cancel() {
        if (countDownHelper != null) {
            isTicking = false;
            countDownHelper.cancel();
            countDownHelper = null;
        }
    }

    /**
     * 设置倒计时监听
     *
     * @param onCountDownChangeListener 倒计时监听
     */
    public void setOnCountDownListener(OnCountDownChangeListener onCountDownChangeListener) {
        this.onCountDownChangeListener = onCountDownChangeListener;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancel();
    }

    public static class TimeDataType {
        public static final int DAYS = 1;
        public static final int HOURS = 2;
        public static final int MINUTES = 3;
        public static final int SECONDS = 4;
        public static final int MILLISECONDS = 5;
    }

    public static class FormatType {
        public static final int DAY = 1;
        public static final int HOUR = 2;
        public static final int MINUTE = 4;
        public static final int SECOND = 8;
//        public static final int MILLISECOND = 16;
    }

    public interface OnCountDownChangeListener {
        /**
         * 倒计时变化时回调
         *
         * @param days    剩余天数
         * @param hours   剩余小时数
         * @param minutes 剩余分钟数
         * @param seconds 剩余秒数 开启毫秒级渲染时包含毫秒
         */
        String onTick(String days, String hours, String minutes, String seconds);

        /**
         * 倒计时结束时回调
         */
        void onFinish();
    }
}
