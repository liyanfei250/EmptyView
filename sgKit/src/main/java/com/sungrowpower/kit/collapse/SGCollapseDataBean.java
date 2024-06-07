package com.sungrowpower.kit.collapse;

/**
 * 折叠面板属性数据
 */
public class SGCollapseDataBean {
    /**
     * 标题
     */
    private CharSequence title = "";
    /**
     * 内容
     */
    private CharSequence content = "";
    /**
     * 折叠图标
     */
    private String iconImageDown = "&#xe9b7;";
    /**
     * 展开图标
     */
    private String iconImageUp = "&#xe9b6;";
    /**
     * 标题左方图标
     */
    private String iconLeft;
    /**
     * 标题右方图标
     */
    private String iconRight;

    /**
     * 是 展开 否 折叠 默认折叠
     */
    private boolean isExpanded = false;

    /**
     * 是 隐藏 否 显示 默认显示
     */
    private boolean isHiddenIcon = false;

    /**
     * 是 不可用 否 可用 默认可用
     */
    private boolean isDisabled = false;
    /**
     * 是 需要动画 否 不需要动画 默认需要动画
     */
    private boolean hasAnimation = true;
    /**
     * 动画时间 默认300ms
     */
    private int animationDuration = 300;
    /**
     * 标题颜色
     */
    private int titleColor = SGCollapseColorMap.titleColor;
    /**
     * 标题字体大小
     */
    private int titleSize = SGCollapseColorMap.titleSize;
    /**
     * 内容颜色
     */
    private int contentColor = SGCollapseColorMap.contentColor;
    /**
     * 内容字体大小
     */
    private int contentSize = SGCollapseColorMap.contentSize;
    /**
     * 展开折叠图标颜色
     */
    private int iconImageColor = SGCollapseColorMap.iconImageColor;
    /**
     * 展开折叠图标字体大小
     */
    private int iconImageSize = SGCollapseColorMap.iconImageSize;
    /**
     * 标题左侧图标颜色
     */
    private int iconLeftColor = SGCollapseColorMap.iconLeftColor;
    /**
     * 标题左侧图标字体大小
     */
    private int iconLeftSize = SGCollapseColorMap.iconLeftSize;
    /**
     * 标题右侧图标颜色
     */
    private int iconRightColor = SGCollapseColorMap.iconRightColor;
    /**
     * 标题右侧图标字体大小
     */
    private int iconRightSize = SGCollapseColorMap.iconRightSize;

    public String getIconLeft() {
        return iconLeft;
    }

    public void setIconLeft(String iconLeft) {
        this.iconLeft = iconLeft;
    }

    public String getIconRight() {
        return iconRight;
    }

    public void setIconRight(String iconRight) {
        this.iconRight = iconRight;
    }

    public CharSequence getTitle() {
        return title;
    }

    public void setTitle(CharSequence title) {
        this.title = title;
    }

    public CharSequence getContent() {
        return content;
    }

    public void setContent(CharSequence content) {
        this.content = content;
    }

    public String getIconImageDown() {
        return iconImageDown;
    }

    public void setIconImageDown(String iconImageDown) {
        this.iconImageDown = iconImageDown;
    }

    public String getIconImageUp() {
        return iconImageUp;
    }

    public void setIconImageUp(String iconImageUp) {
        this.iconImageUp = iconImageUp;
    }

    public boolean isHiddenIcon() {
        return isHiddenIcon;
    }

    public void setHiddenIcon(boolean hiddenIcon) {
        isHiddenIcon = hiddenIcon;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public boolean isHasAnimation() {
        return hasAnimation;
    }

    public void setHasAnimation(boolean hasAnimation) {
        this.hasAnimation = hasAnimation;
    }

    public int getAnimationDuration() {
        return animationDuration;
    }

    public void setAnimationDuration(int animationDuration) {
        this.animationDuration = animationDuration;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(int titleSize) {
        this.titleSize = titleSize;
    }

    public int getContentColor() {
        return contentColor;
    }

    public void setContentColor(int contentColor) {
        this.contentColor = contentColor;
    }

    public int getContentSize() {
        return contentSize;
    }

    public void setContentSize(int contentSize) {
        this.contentSize = contentSize;
    }

    public int getIconImageColor() {
        return iconImageColor;
    }

    public void setIconImageColor(int iconImageColor) {
        this.iconImageColor = iconImageColor;
    }

    public int getIconImageSize() {
        return iconImageSize;
    }

    public void setIconImageSize(int iconImageSize) {
        this.iconImageSize = iconImageSize;
    }

    public int getIconLeftColor() {
        return iconLeftColor;
    }

    public void setIconLeftColor(int iconLeftColor) {
        this.iconLeftColor = iconLeftColor;
    }

    public int getIconLeftSize() {
        return iconLeftSize;
    }

    public void setIconLeftSize(int iconLeftSize) {
        this.iconLeftSize = iconLeftSize;
    }

    public int getIconRightColor() {
        return iconRightColor;
    }

    public void setIconRightColor(int iconRightColor) {
        this.iconRightColor = iconRightColor;
    }

    public int getIconRightSize() {
        return iconRightSize;
    }

    public void setIconRightSize(int iconRightSize) {
        this.iconRightSize = iconRightSize;
    }
}
