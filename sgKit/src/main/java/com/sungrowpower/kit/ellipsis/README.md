# 文本缩略使用说明

## 方法

| 方法名 | 描述 | 返回类型 | 默认值 |
| --- | --- | --- | --- |
| setClickableTextColor(int clickableTextColor) | 设置展开折叠文字颜色 | void | #222222 |
| setClickableTextSize(int clickableTextSize) | 设置展开折叠文字大小 | void | 跟随文字大小 |
| setCollapsedText(CharSequence collapsedText) | 设置折叠文字 | void | \- |
| setExpandedText(CharSequence expandedText) | 设置展开文字 | void | \- |
| setMaxLines(int maxLines) | 设置最大行数 | void | 1 |
| setEllipsisMaxHeight(int maxHeight) | 设置最大高度 最大高度优先级高于maxLine 根据高度计算最大行数 最少显示一行 | void | \- |
| setCollapsed(boolean isCollapsed) | 设置展开折叠状态 true折叠 false展开 | void | true |
| setOnEllipsisClickListener(OnEllipsisClickListener onEllipsisClickListener) | 设置展开折叠监听 | void | \- |
| setOnEllipsisClickListener(OnReflowListener onReflowListener) | 设置文本缩略处理的完成回调 | void | \- |

## Attributes属性

| 属性 | 描述 | 类型 | 默认值 |
| --- | --- | --- | --- |
| sgkit_seMaxHeight | 最大显示高度，单位 px，优先级高于 maxLine | dimension | \- |
| sgkit_seMaxLine | 最大显示行数 默认1 | integer | 1 |
| sgkit_seEndExcludes | 文本结尾处（缩略符之前）需要过滤掉的字符 多个需传入英文逗号分割 | string | \- |
| sgkit_seEllipsis | 是否开启缩略 | boolean | true |
| sgkit_seEllipsisText | 自定义缩略符文本，文本缩略时插入文本尾部 |  string | ... |
| sgkit_seCollapsedText | 收起时的可点击文本 为空不展示 | string | \- |
| sgkit_seExpandedText | 展开时的可点击文本 为空不展示 | string | \- |
| sgkit_seTextColor | 可点击文本字体颜色 默认跟随text字体颜色 | color/reference | \- |
| sgkit_seTextSize | 可点击文本字体大小 单位 px 默认跟随text字体大小 | dimension | \- |