# 倒计时使用说明

## 方法

| 方法名 | 描述 | 返回类型 | 默认值 |
| --- | --- | --- | --- |
| setCustomView(View customView) | 设置自定义View | void | \- |
| getCustomView() | 获取自定义View | View | \- |
| setCustomView(@LayoutRes int layoutCustomView) | 设置自定义布局 | void | \- |
| setTextColor(int textColor) | 设置字体颜色 | void | #222222 |
| setTextSize(int textSize) | 设置字体大小 单位(px) | void | 48px |
| setMillisecond(boolean isMillisecond) | 设置是否开启毫秒级渲染 需在开始倒计时{@link #start()}之前调用 | void | \- |
| setTimeDuration(int timeDuration) | 设置倒计时时长 需在开始倒计时{@link #start()}之前调用 | void | \- |
| setTimeDataType(int timeDataType) | 设置时间单位或类型 需在开始倒计时{@link #start()}之前调用 | void | \- |
| setFutureMillisecond(long futureMillisecond) | 设置未来某个时间点的时间戳 单位毫秒ms 需在开始倒计时{@link #start()}之前调用 | void | \- |
| start() | 开始倒计时 | void | \- |
| pause() | 暂停倒计时 | void | \- |
| reset() | 重置倒计时 | void | \- |
| cancel() | 取消倒计时 | void | \- |
| setOnCountDownTickListener(OnCountDownChangeListener onCountDownChangeListener) | 设置倒计时监听 | void | \- |

## Attributes属性

| 属性 | 描述 | 类型 | 默认值 |
| --- | --- | --- | --- |
| sgkit_scdTimeDataType | 倒计时时间单位 | enum | \- |
| sgkit_scdTime | 倒计时时长，单位根据timeDataType 默认秒 | integer | \- |
| sgkit_scdFormat | 需传入固定格式，逗号分割(天,时,分,秒)，如：(%02dDays,%02d时,%02d分,%02d秒) 或者 (,%02d:,,%02d)等 | string | ,%02d:,%02d:,%02d |
| sgkit_scdAutoStart | 是否自动开始倒计时 | boolean | false |
| sgkit_scdMillisecond | 是否开启毫秒级渲染(如果sgkit_scdFormat未设置秒，则不显示) |  boolean | false |
| sgkit_scdTextColor | 字体颜色 | color/reference | #2222222 |
| sgkit_scdTextSize | 字体大小单位(px) | dimension | 48px |
| sgkit_scExpand | 是否展开 | boolean | false |
| sgkit_scHasAnimation | 是否需要动画 | boolean | true |
| sgkit_scAnimationTimes | 动画时间单位(ms) | integer | 300ms |

## SGCountDownHelper

### 方法
| 方法名 | 描述 | 返回类型 | 默认值 |
| --- | --- | --- | --- |
| setOnCountDownTickListener(OnCountDownChangeListener onCountDownChangeListener) | 设置倒计时监听 | void | \- |
| generateTime(long time) | 将毫秒转天时分秒 | void | \- |