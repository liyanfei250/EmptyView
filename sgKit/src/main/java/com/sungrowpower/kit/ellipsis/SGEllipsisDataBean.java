package com.sungrowpower.kit.ellipsis;

/**
 * 文本缩略属性数据
 */
public class SGEllipsisDataBean {
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
     * 字符过长裁剪分隔符 默认...
     */
    private CharSequence ellipsizeText = "...";

    /**
     * 过滤文本结尾处（缩略符之前）需要过滤掉的字符
     */
    private String endExcludes;

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
     * 裁剪行数 默认1行
     */
    private int maxLines = 1;

    public boolean isCollapsed() {
        return isCollapsed;
    }

    public void setCollapsed(boolean collapsed) {
        isCollapsed = collapsed;
    }

    public CharSequence getCollapsedText() {
        return collapsedText;
    }

    public void setCollapsedText(CharSequence collapsedText) {
        this.collapsedText = collapsedText;
    }

    public CharSequence getExpandedText() {
        return expandedText;
    }

    public void setExpandedText(CharSequence expandedText) {
        this.expandedText = expandedText;
    }

    public CharSequence getEllipsizeText() {
        return ellipsizeText;
    }

    public void setEllipsizeText(CharSequence ellipsizeText) {
        this.ellipsizeText = ellipsizeText;
    }

    public String getEndExcludes() {
        return endExcludes;
    }

    public void setEndExcludes(String endExcludes) {
        this.endExcludes = endExcludes;
    }

    public int getClickableTextColor() {
        return clickableTextColor;
    }

    public void setClickableTextColor(int clickableTextColor) {
        this.clickableTextColor = clickableTextColor;
    }

    public int getClickableTextSize() {
        return clickableTextSize;
    }

    public void setClickableTextSize(int clickableTextSize) {
        this.clickableTextSize = clickableTextSize;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxLines() {
        return maxLines;
    }

    public void setMaxLines(int maxLines) {
        this.maxLines = maxLines;
    }
}
