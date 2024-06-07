package com.sungrowpower.kit.countdown;

import android.os.CountDownTimer;
import java.util.Locale;

/**
 * 倒计时工具类
 * 根据提供的format类型返回对应值
 */
public class SGCountDownHelper extends CountDownTimer {

    /**
     * 自定义格式化时间 需传入固定格式，逗号分割(天,时,分,秒)，如：(%02dDays,%02d时,%02d分,%02d秒) 或者 (,%02d:,,%02d)等
     */
    private final int timeFormat;

    /**
     * 是否开启毫秒级渲染 默认false
     */
    private final boolean isMillisecond;

    /**
     * 1秒的毫秒值
     */
    public static final long oneSecond = 1000;

    /**
     * 1分钟的毫秒值
     */
    public static final long oneMinute = oneSecond * 60;

    /**
     * 1小时的毫秒值
     */
    public static final long oneHour = oneMinute * 60;

    /**
     * 1天的毫秒值
     */
    public static final long oneDay = oneHour * 24;

    private OnCountDownListener onCountDownListener;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public SGCountDownHelper(long millisInFuture, long countDownInterval, int timeFormat, boolean isMillisecond) {
        super(millisInFuture, countDownInterval);
        this.timeFormat = timeFormat;
        this.isMillisecond = isMillisecond;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        formatTime(millisUntilFinished);
    }

    @Override
    public void onFinish() {
        if (onCountDownListener != null) {
            onCountDownListener.onFinish();
        }
    }

    /**
     * 设置倒计时监听
     *
     * @param onCountDownListener 倒计时监听
     */
    public void setOnCountDownListener(OnCountDownListener onCountDownListener) {
        this.onCountDownListener = onCountDownListener;
    }

    /**
     * 毫秒转天时分秒
     */
    public void formatTime(long time) {
        //剩余时长
        long remainingTime = time;

        //计算天数
        String dayFormat = "";
        String format = "%02d";
        if ((timeFormat & SGCountDown.FormatType.DAY) == SGCountDown.FormatType.DAY) {
            long days = remainingTime / oneDay;
            remainingTime = remainingTime - (days * oneDay);
            dayFormat = String.format(Locale.getDefault(), format, days);
        }

        //计算小时
        String hourFormat = "";
        if ((timeFormat & SGCountDown.FormatType.HOUR) == SGCountDown.FormatType.HOUR) {
            long hours = remainingTime / oneHour;
            remainingTime = remainingTime - (hours * oneHour);
            hourFormat = String.format(Locale.getDefault(), format, hours);
        }

        //计算分钟
        String minutesFormat = "";
        if ((timeFormat & SGCountDown.FormatType.MINUTE) == SGCountDown.FormatType.MINUTE) {
            long minutes = remainingTime / oneMinute;
            remainingTime = remainingTime - (minutes * oneMinute);
            minutesFormat = String.format(Locale.getDefault(), format, minutes);
        }

        //计算秒
        StringBuilder second = new StringBuilder();
        if ((timeFormat & SGCountDown.FormatType.SECOND) == SGCountDown.FormatType.SECOND) {
            long seconds = remainingTime / oneSecond;
            second.append(String.format(Locale.getDefault(), format, seconds));

            if (isMillisecond) {
                //如果开启毫秒值,截取最后三位，直接减去秒数乘以1000，有误差，会走不到0。
                String str = String.valueOf(time);
                second.append(".");
                String milliseconds = "000";
                if (str.length() > 3) {
                    milliseconds = str.substring(str.length() - 3);
                }
                second.append(milliseconds);
            }
        }

        if (onCountDownListener != null) {
            onCountDownListener.onTick(time, dayFormat, hourFormat, minutesFormat, second.toString());
        }
    }

    public interface OnCountDownListener {
        /**
         * 倒计时变化时回调
         *
         * @param remainingTotalTime 剩余总时长单位毫秒
         * @param days               剩余天数
         * @param hours              剩余小时数
         * @param minutes            剩余分钟数
         * @param seconds            剩余秒数 开启毫秒级渲染时包含毫秒
         */
        void onTick(long remainingTotalTime, String days, String hours, String minutes, String seconds);

        /**
         * 倒计时结束时回调
         */
        void onFinish();
    }
}
