package com.sungrowpower.kit.countdown;

/**
 * 倒计时属性数据
 */
public class SGCountdownDataBean {
    /**
     * 倒计时 默认类型4 单位秒 时间类型{@link SGCountDown.TimeDataType}
     */
    private int timeDataType = SGCountDown.TimeDataType.SECONDS;

    /**
     * 倒计时时长 根据类型计算{@link SGCountDown.TimeDataType}
     */
    private int timeDuration;

    /**
     * 自定义格式 可任意组合
     */
    private int timeFormat = SGCountDown.FormatType.HOUR | SGCountDown.FormatType.MINUTE | SGCountDown.FormatType.SECOND;

    /**
     * 是否自动开始倒计时 默认false
     */
    private boolean isAutoStart = false;

    /**
     * 是否开启毫秒级渲染 默认false
     */
    private boolean isMillisecond = false;
    /**
     * 颜色
     */
    private int textColor = SGCountdownColorMap.textColor;
    /**
     * 字体大小
     */
    private int textSize = SGCountdownColorMap.textSize;

    public int getTimeDataType() {
        return timeDataType;
    }

    public void setTimeDataType(int timeDataType) {
        this.timeDataType = timeDataType;
    }

    public int getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(int timeDuration) {
        this.timeDuration = timeDuration;
    }

    public int getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(int timeFormat) {
        this.timeFormat = timeFormat;
    }

    public boolean isAutoStart() {
        return isAutoStart;
    }

    public void setAutoStart(boolean autoStart) {
        isAutoStart = autoStart;
    }

    public boolean isMillisecond() {
        return isMillisecond;
    }

    public void setMillisecond(boolean millisecond) {
        isMillisecond = millisecond;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }
}
