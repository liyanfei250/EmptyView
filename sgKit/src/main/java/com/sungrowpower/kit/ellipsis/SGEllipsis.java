package com.sungrowpower.kit.ellipsis;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.sungrowpower.kit.R;
import com.sungrowpower.kit.util.SGUILog;

/**
 * 文本缩略 Ellipsis
 */
public class SGEllipsis extends AppCompatTextView {
    private static final String TAG = "SGEllipsis";
    private CharSequence text;
    private BufferType bufferType;

    /**
     * 折叠状态 默认true
     */
    private boolean isCollapsed = true;

    /**
     * 折叠文字
     */
    private CharSequence collapsedText;

    /**
     * 展开文字
     */
    private CharSequence expandedText;

    /**
     * 字符过长裁剪分隔符
     */
    private CharSequence ellipsizeText;

    /**
     * 过滤文本结尾处（缩略符之前）需要过滤掉的字符
     */
    private String endExcludes;

    /**
     * 点击事件span
     */
    private EllipsisClickableSpan clickableSpan;

    /**
     * 展开折叠文字颜色
     */
    private int clickableTextColor;

    /**
     * 展开折叠文字大小
     */
    private int clickableTextSize;

    /**
     * 最大高度
     */
    private int maxHeight;

    /**
     * 裁剪行数
     */
    private int maxLines;

    /**
     * 折叠后的字符长度
     */
    private int lineEndIndex;

    private OnEllipsisClickListener onEllipsisClickListener;
    private OnReflowListener onReflowListener;

    public SGEllipsis(Context context) {
        this(context, null);
    }

    public SGEllipsis(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SGEllipsis(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, defStyleAttr);
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        SGEllipsisDataBean dataBean = new SGEllipsisDataBean();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SGEllipsis, defStyleAttr, 0);
        collapsedText = a.getString(R.styleable.SGEllipsis_sgkit_collapsedText);
        if (TextUtils.isEmpty(collapsedText)) {
            collapsedText = dataBean.getCollapsedText() != null ? dataBean.getCollapsedText() : "";
        }
        expandedText = a.getString(R.styleable.SGEllipsis_sgkit_expandedText);
        if (TextUtils.isEmpty(expandedText)) {
            expandedText = dataBean.getExpandedText() != null ? dataBean.getExpandedText() : "";
        }
        ellipsizeText = a.getString(R.styleable.SGEllipsis_sgkit_ellipsisText);
        if (TextUtils.isEmpty(ellipsizeText)) {
            ellipsizeText = dataBean.getEllipsizeText() != null ? dataBean.getEllipsizeText() : "";
        }

        isCollapsed = a.getBoolean(R.styleable.SGEllipsis_sgkit_ellipsis, dataBean.isCollapsed());

        endExcludes = a.getString(R.styleable.SGEllipsis_sgkit_endExcludes);

        maxHeight = a.getDimensionPixelSize(R.styleable.SGEllipsis_sgkit_maxHeight, dataBean.getMaxHeight());
        maxLines = a.getInt(R.styleable.SGEllipsis_sgkit_maxLine, dataBean.getMaxLines());
        clickableTextColor = a.getColor(R.styleable.SGEllipsis_sgkit_textColor, dataBean.getClickableTextColor());
        //这里不需要默认值，默认跟随text的字体大小
        clickableTextSize = a.getDimensionPixelSize(R.styleable.SGEllipsis_sgkit_textSize, dataBean.getClickableTextSize());
        a.recycle();

        clickableSpan = new EllipsisClickableSpan();
        onGlobalLayoutLineEndIndex();
        setText();
    }

    private void setText() {
        super.setText(getTrimmedText(text), bufferType);
        setMovementMethod(LinkMovementMethod.getInstance());
        //解决文字选中背景色问题
        setHighlightColor(Color.TRANSPARENT);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        this.text = text;
        bufferType = type;
        setText();
    }

    private CharSequence getTrimmedText(CharSequence text) {
        if (text != null && lineEndIndex > 0) {
            if (isCollapsed) {
                if (getLayout().getLineCount() > maxLines) {
                    return updateCollapsedText();
                }
            } else {
                return updateExpandedText();
            }
        }
        if (onReflowListener != null) {
            onReflowListener.onReflow();
        }
        return text;
    }

    /**
     * 获取收起时的文字数据
     *
     * @return 收起时的数据
     */
    private CharSequence updateCollapsedText() {
        SpannableStringBuilder s = new SpannableStringBuilder(text, 0, getTrimEndIndex());
        SpannableStringBuilder excludes = removeEndExcludes(s);
        //拼接... 和 收起文本
        excludes.append(ellipsizeText)
                .append(collapsedText);
        if (TextUtils.isEmpty(collapsedText)) {
            return excludes;
        }
        return addClickableAndSizeSpan(excludes, collapsedText);
    }

    /**
     * 设置展开时的数据
     *
     * @return 展开时的数据
     */
    private CharSequence updateExpandedText() {
        if (!TextUtils.isEmpty(expandedText)) {
            SpannableStringBuilder s = new SpannableStringBuilder(text, 0, text.length()).append(expandedText);
            return addClickableAndSizeSpan(s, expandedText);
        }
        return text;
    }

    /**
     * 添加点击事件和字体大小
     *
     * @param s        显示文本
     * @param trimText 展开/折叠文本
     * @return 添加点击事件和字体大小的富文本
     */
    private CharSequence addClickableAndSizeSpan(SpannableStringBuilder s, CharSequence trimText) {
        //添加点击事件
        s.setSpan(clickableSpan, s.length() - trimText.length(), s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (clickableTextSize > 0) {
            //添加字体大小
            s.setSpan(new AbsoluteSizeSpan(clickableTextSize), s.length() - trimText.length(), s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }

    /**
     * 设置展开折叠文字颜色
     *
     * @param clickableTextColor 展开折叠文字颜色
     */
    public SGEllipsis setClickableTextColor(int clickableTextColor) {
        this.clickableTextColor = clickableTextColor;
        setText();
        return this;
    }

    /**
     * 设置展开折叠文字大小
     *
     * @param clickableTextSize 展开折叠文字大小 单位px
     */
    public SGEllipsis setClickableTextSize(int clickableTextSize) {
        this.clickableTextSize = clickableTextSize;
        setText();
        return this;
    }

    /**
     * 设置折叠文字
     *
     * @param collapsedText 折叠文字
     */
    public SGEllipsis setCollapsedText(CharSequence collapsedText) {
        if (TextUtils.isEmpty(collapsedText)) {
            return this;
        }
        this.collapsedText = collapsedText;
        setText();
        return this;
    }

    /**
     * 设置展开文字
     *
     * @param expandedText 展开文字
     */
    public SGEllipsis setExpandedText(CharSequence expandedText) {
        if (TextUtils.isEmpty(expandedText)) {
            return this;
        }
        this.expandedText = expandedText;
        setText();
        return this;
    }

    /**
     * 设置字符过长裁剪分隔符
     *
     * @param ellipsizeText 字符过长裁剪分隔符
     */
    public SGEllipsis setEllipsizeText(CharSequence ellipsizeText) {
        if (TextUtils.isEmpty(ellipsizeText)) {
            return this;
        }
        this.ellipsizeText = ellipsizeText;
        setText();
        return this;
    }

    /**
     * 设置最大行数
     *
     * @param maxLines 最大行数
     */
    public SGEllipsis setEllipsisMaxLines(int maxLines) {
        this.maxLines = maxLines;
        setText();
        return this;
    }

    /**
     * 设置最大高度
     *
     * @param maxHeight 最大高度
     */
    public SGEllipsis setEllipsisMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
        setText();
        return this;
    }

    /**
     * 设置展开折叠状态
     *
     * @param isCollapsed 展开折叠状态 true折叠 false展开 默认true
     */
    public SGEllipsis setCollapsed(boolean isCollapsed) {
        this.isCollapsed = isCollapsed;
        setText();
        return this;
    }

    /**
     * 设置过滤文本结尾处（缩略符之前）需要过滤掉的字符
     *
     * @param endExcludes 过滤文本结尾处（缩略符之前）需要过滤掉的字符
     */
    public SGEllipsis setEndExcludes(String endExcludes) {
        this.endExcludes = endExcludes;
        setText();
        return this;
    }

    /**
     * 设置展开折叠监听
     *
     * @param onEllipsisClickListener 展开折叠监听
     */
    public SGEllipsis setOnEllipsisClickListener(OnEllipsisClickListener onEllipsisClickListener) {
        this.onEllipsisClickListener = onEllipsisClickListener;
        return this;
    }

    /**
     * 设置文本缩略处理的完成回调
     *
     * @param onReflowListener 文本缩略处理的完成回调
     */
    public SGEllipsis setOnEllipsisClickListener(OnReflowListener onReflowListener) {
        this.onReflowListener = onReflowListener;
        return this;
    }

    /**
     * 根据实体类数据设置
     *
     * @param dataBean 实体类数据
     */
    public void setDataBean(SGEllipsisDataBean dataBean) {
        setClickableTextColor(dataBean.getClickableTextColor());
        setClickableTextSize(dataBean.getClickableTextSize());
        setCollapsedText(dataBean.getCollapsedText());
        setExpandedText(dataBean.getExpandedText());
        setEllipsizeText(dataBean.getEllipsizeText());
        setEllipsisMaxLines(dataBean.getMaxLines());
        setEllipsisMaxHeight(dataBean.getMaxHeight());
        setCollapsed(dataBean.isCollapsed());
        setEndExcludes(dataBean.getEndExcludes());
    }

    public interface OnEllipsisClickListener {
        /**
         * @param isCollapsed true 收起状态 false 展开状态
         */
        void onEllipsisClick(boolean isCollapsed);
    }

    /**
     * 文本缩略处理的完成回调
     */
    public interface OnReflowListener {

        /**
         * 文本缩略处理的完成回调
         */
        void onReflow();
    }

    private class EllipsisClickableSpan extends ClickableSpan {
        @Override
        public void onClick(@NonNull View widget) {
            isCollapsed = !isCollapsed;
            setText();
            if (onEllipsisClickListener != null) {
                onEllipsisClickListener.onEllipsisClick(isCollapsed);
            }
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            if (clickableTextColor == 0) {
                clickableTextColor = getCurrentTextColor();
            }
            ds.setColor(clickableTextColor);
        }
    }

    /**
     * onDraw方法中调用setText()会陷入死循环 一直绘制
     */
    private void onGlobalLayoutLineEndIndex() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = getViewTreeObserver();
                obs.removeOnGlobalLayoutListener(this);
                SGUILog.d(TAG, "onGlobalLayoutLineEndIndex: ");
                refreshLineEndIndex();
                setText();
            }
        });
    }

    /**
     * 获取最后一个字符的位置
     * 折叠收起按钮行高问题:getLineHeight()默认取第一行行高，对下面的行高没影响
     */
    private void refreshLineEndIndex() {
        try {
            //最大高度优先级高于maxLine 根据高度计算最大行数 最少显示一行
            if (maxHeight > 0) {
                maxLines = (maxHeight - getPaddingTop() - getPaddingBottom()) / getLineHeight();
                SGUILog.d(TAG, "maxHeight :" + maxHeight + " lineHeight: " + getLineHeight() + " getPaddingTop: " + getPaddingTop());
            }
            //maxLine等于0/1时，展示第一行 maxLine大于getLineCount()时，展示全部
            if (maxLines == 0 || maxLines == 1) {
                lineEndIndex = getLayout().getLineEnd(0);
            } else if (maxLines > 0 && getLineCount() >= maxLines) {
                lineEndIndex = getLayout().getLineEnd(maxLines - 1);
            } else {
                lineEndIndex = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 过滤文本结尾处（缩略符之前）需要过滤掉的字符
     *
     * @param text 折叠后的文本内容
     * @return 过滤后的文本内容
     */
    private SpannableStringBuilder removeEndExcludes(SpannableStringBuilder text) {
        if (TextUtils.isEmpty(endExcludes)) {
            return text;
        }
        String[] split = endExcludes.split(",");
        for (String s : split) {
            if (text.toString().endsWith(s)) {
                text.replace(text.length() - s.length(), text.length(), "");
                removeEndExcludes(text);
            }
        }
        return text;
    }

    /**
     * 获取需被替换的文字个数
     * 被替换的文字宽度可能会小于收起的文字和缩略文字宽度
     *
     * @return 需被替换的文字个数
     */
    private int getTrimEndIndex() {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(ellipsizeText).append(collapsedText);
        int textWidth = getTextWidth(true, builder.toString());
        int trimEndIndex = lineEndIndex - (ellipsizeText.length() + collapsedText.length());
        if (trimEndIndex < 0) {
            trimEndIndex = 1;
        }
        SpannableStringBuilder replaceText = new SpannableStringBuilder(text, trimEndIndex, lineEndIndex);
        int replaceTextWidth = getTextWidth(false, replaceText.toString());
        SGUILog.d(TAG, "textWidth: " + textWidth + " replaceTextWidth :" + replaceTextWidth);
        while (textWidth > replaceTextWidth) {
            trimEndIndex = trimEndIndex - 1;
            SpannableStringBuilder replaceTextLoop = new SpannableStringBuilder(text, trimEndIndex, lineEndIndex);
            replaceTextWidth = getTextWidth(false, replaceTextLoop.toString());
            SGUILog.d(TAG, "trimEndIndex: " + trimEndIndex);
        }
        if (trimEndIndex < 0) {
            trimEndIndex = 1;
        }
        return trimEndIndex;
    }

    /**
     * 计算文字宽度
     * 需设置字体大小!!!!!
     *
     * @param isClickableText 是不是收起展开的文字
     * @param str             文字
     * @return 文字宽度
     */
    public int getTextWidth(boolean isClickableText, String str) {
        //根据是不是收起展开的文字 来设置字体大小计算文字宽度
        Paint paint = new Paint();
        if (isClickableText && clickableTextSize > 0) {
            paint.setTextSize(clickableTextSize);
        } else {
            paint.setTextSize(getPaint().getTextSize());
        }
        float textWidth = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                textWidth += widths[j];
            }
        }
        SGUILog.d(TAG, "floatTextWidth: " + textWidth);
        return (int) textWidth;
    }
}
