package com.sungrowpower.kit.collapse;

/**
 * 使用SGCollapseGroupAdapter需继承的类，用于设置标题和内容及展开折叠状态
 */
public class SGCollapseBean{

    private boolean isExpand = false;

    private String title;
    private String content;

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
