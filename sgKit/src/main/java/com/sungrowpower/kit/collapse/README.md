# 折叠面板使用说明

## 方法

| 方法名 | 描述 | 返回类型 | 默认值 |
| --- | --- | --- | --- |
| setTitleView(View titleView) | 设置自定义标题View | void | \- |
| setTitleView(@LayoutRes int layoutTitleView) | 设置自定义标题布局 | void | \- |
| getTitleView() | 获取自定义标题View | View | \- |
| setContentView(View contentView) | 设置自定义面板View(面板即被展开折叠的部分，下同) | void | \- |
| setContentView(@LayoutRes int layoutContentView) | 设置自定义面板布局 | void | \- |
| getContentView() | 获取自定义面板View | View | \- |
| defaultTitleView() | 默认的titleView样式 | View | \- |
| setTitle(CharSequence title) | 设置标题内容 | void | \- |
| setTitleColor(int titleColor) | 设置标题字体颜色 | void | #222222 |
| setTitleSize(int titleSize) | 设置标题字体大小 单位(px) | void | 48px |
| setIconImageUp(String iconImageUp) | 设置展开图标 | void | \- |
| setIconImageDown(String iconImageDown) | 设置折叠图标 | void | \- |
| setIconImageColor(int iconImageColor) | 设置展开折叠图标字体颜色 | void | #222222 |
| setIconImageSize(int iconImageSize) | 设置展开折叠图标字体大小 单位(px) | void | 48px |
| setIconLeft(String iconLeft) | 设置标题左侧图标 为null不显示 | void | \- |
| setIconLeftColor(int iconLeftColor) | 设置标题左侧图标字体颜色 | void | #222222 |
| setIconLeftSize(int iconLeftSize) | 设置标题左侧图标字体大小 单位(px) | void | 48px |
| setIconLeftOnClickListener(OnTitleIconOnClickListener onIconLeftOnClickListener) | 设置标题左侧图标点击事件 | void | \- |
| setIconRight(String iconRight) | 设置标题右侧图标 | void | \- |
| setIconRightColor(int iconRightColor) | 设置标题右侧图标字体颜色 | void | #222222 |
| setIconRightSize(int iconRightSize) | 设置标题右侧图标字体大小 单位(px) | void | 48px |
| setIconRightOnClickListener(OnTitleIconOnClickListener onIconRightOnClickListener) | 设置标题右侧图标点击事件 | void | \- |
| setHiddenIcon(Boolean isHiddenIcon) | 设置折叠展开图标显示隐藏 是 隐藏 否 显示 | void | 显示 |
| setDisabled(Boolean isDisabled) | 设置标题区域是否可点击 是 不可点 否 可点 | void | 可用 |
| setExpanded(Boolean isExpanded) | 设置图标展开折叠 是 展开 否 折叠 | void | 折叠 |
| setHasAnimation(Boolean hasAnimation) | 设置图标展开折叠是否需要动画 是 需要动画 否 不需要动画 | void | 需要动画 |
| defaultContentView() | 默认的contentView样式 | TextView | \- |
| setContent(CharSequence content) | 设置面板内容 | void | \- |
| setContentColor(int contentColor) | 设置面板字体颜色 | void | #999999 |
| setTitleSize(int titleSize) | 设置面板字体大小 单位(px) | void | 36px |
| getDivider() | 获取分割线 | void | \- |
| setOnCollapseCallback(OnCollapseCallback collapseCallback) | 设置展开折叠监听 | void | \- |

## Attributes属性

| 属性 | 描述 | 类型 | 默认值 |
| --- | --- | --- | --- |
| sgkit_scTitle | 标题内容 | string | 必填 |
| sgkit_scTitleColor | 标题颜色 | color /reference | #222222 |
| sgkit_scTitleSize | 标题字体大小单位(px) | dimension | 48px |
| sgkit_scIconImageDown | 折叠的图标 | string | \- |
| sgkit_scIconImageUp | 展开的图标 | string | \- |
| sgkit_scIconImageColor | 展开折叠的图标颜色 | color /reference | #222222 |
| sgkit_scIconImageSize | 展开折叠的图标字体大小(px) | dimension | 48px |
| sgkit_scLeftIcon | 标题左边的图标 | string | \- |
| sgkit_scLeftIconColor | 标题左边的图标颜色 | color /reference | #222222 |
| sgkit_scLeftIconSize | 标题左边的图标字体大小(px) | dimension | 48px |
| sgkit_scRightIcon | 标题右边的图标 | string | \- |
| sgkit_scRightIconColor | 标题右边的图标颜色 | color /reference | #222222 |
| sgkit_scRightIconSize | 标题右边的图标字体大小(px) | dimension | 48px |
| sgkit_scHiddenIcon | 是否隐藏图标 | boolean | false |
| sgkit_scDisabled | 是否不可展开 |  boolean | false |
| sgkit_scContent | 面板内容（折叠展开view中的内容） | string | 必填 |
| sgkit_scContentColor | 面板颜色 | color/reference | #999999 |
| sgkit_scContentSize | 面板字体大小单位(px) | dimension | 36px |
| sgkit_scExpand | 是否展开 | boolean | false |
| sgkit_scHasAnimation | 是否需要动画 | boolean | true |
| sgkit_scAnimationTimes | 动画时间单位(ms) | integer | 300ms |

## SGCollapseGroupAdapter使用
数据源需实现SGCollapseViewSet

### 方法
| 方法名 | 描述 | 返回类型 | 默认值 |
| --- | --- | --- | --- |
| getData() | 获取数据源 | List<T> | \- |
| setUseAccordion(boolean useAccordion) | 设置是否使用手风琴样式 | void | false |
| getUseAccordion() | 获取是否使用手风琴样式 true 是 false 否 | boolean | false |
| setDisabled(boolean disabled) | 设置一键禁用 | void | false |
| getDisabled() | 获取是否一键禁用 true 是 false 否 | boolean | false |
| setHasAnimation(Boolean hasAnimation) | 设置图标展开折叠是否需要动画 | void | true |
| setOnCollapseGroupCallback(onCollapseGroupCallback onCollapseGroupCallback) | 设置展开折叠监听 | void | \- |