package com.sungrowpower.kit.collapse;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.sungrowpower.kit.R;
import com.sungrowpower.kit.fonticon.SGFontIcon;
import com.sungrowpower.kit.util.SGUILog;

/**
 * 折叠面板
 */
public class SGCollapse extends LinearLayout {

    private static final String TAG = "SGCollapse";

    /**
     * 不可点击标题颜色
     */
    private final int DISABLED_COLOR = getResources().getColor(R.color.sgkit_text_disabled);

    /**
     * 标题View
     */
    private View titleView;

    /**
     * 面板View
     */
    private View contentView;

    /**
     * 标题内容
     */
    private CharSequence title;
    private int titleColor;
    private int titleSize;

    /**
     * 面板内容
     */
    private CharSequence content;
    private int contentColor;
    private int contentSize;

    /**
     * 折叠图标
     */
    private String iconImageDown;
    /**
     * 展开图标
     */
    private String iconImageUp;
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
    private boolean isExpanded;

    /**
     * 是 隐藏 否 显示 默认显示
     */
    private boolean isHiddenIcon;

    /**
     * 是 不可用 否 可用 默认可用
     */
    private boolean isDisabled;

    /**
     * 是 需要动画 否 不需要动画 默认需要动画
     */
    private boolean hasAnimation;

    /**
     * 动画时间 默认300ms
     */
    private int mAnimationDuration = 300;

    /**
     * 收起的高度
     */
    private int collapseHeight;

    /**
     * 默认样式内容textView
     */
    private AppCompatTextView tvContent;

    /**
     * 动画开始时回调 - 用于设置手风琴样式
     */
    private OnAnimationStartCallback onAnimationStartCallback;

    /**
     * 展开收起回调
     */
    private OnCollapseCallback onCollapseCallback;

    /**
     * 左侧图标点击回调
     */
    private OnTitleIconOnClickListener onIconLeftOnClickListener;

    /**
     * 右侧图标点击回调
     */
    private OnTitleIconOnClickListener onIconRightOnClickListener;
    private final FrameLayout flTitle;
    private final FrameLayout flContent;
    private final View viewDivider;
    private View defaultTitleView;
    private TextView tvTitle;
    private SGFontIcon expandIcon;
    private SGFontIcon titleLeftIcon;
    private SGFontIcon titleRightIcon;


    public SGCollapse(@NonNull Context context) {
        this(context, null);
    }

    public SGCollapse(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SGCollapse(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SGCollapse(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setOrientation(LinearLayout.VERTICAL);
        SGCollapseDataBean dataBean = new SGCollapseDataBean();
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SGCollapse, defStyleAttr, 0);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_sg_collapse, this, true);
        flTitle = view.findViewById(R.id.fl_title);
        flContent = view.findViewById(R.id.fl_content);
        viewDivider = view.findViewById(R.id.view_divider);

        title = a.getString(R.styleable.SGCollapse_sgkit_title);
        titleColor = a.getColor(R.styleable.SGCollapse_sgkit_titleColor, dataBean.getTitleColor());
        titleSize = a.getDimensionPixelSize(R.styleable.SGCollapse_sgkit_titleSize, dataBean.getTitleSize());

        content = a.getString(R.styleable.SGCollapse_sgkit_content);
        contentColor = a.getColor(R.styleable.SGCollapse_sgkit_contentColor, dataBean.getContentColor());
        contentSize = a.getDimensionPixelSize(R.styleable.SGCollapse_sgkit_contentSize, dataBean.getContentSize());

        iconImageDown = a.getString(R.styleable.SGCollapse_sgkit_iconImageDown);
        iconImageUp = a.getString(R.styleable.SGCollapse_sgkit_iconImageUp);

        iconLeft = a.getString(R.styleable.SGCollapse_sgkit_leftIcon);
        iconRight = a.getString(R.styleable.SGCollapse_sgkit_rightIcon);

        isHiddenIcon = a.getBoolean(R.styleable.SGCollapse_sgkit_hiddenIcon, false);
        isDisabled = a.getBoolean(R.styleable.SGCollapse_sgkit_disabled, false);
        isExpanded = a.getBoolean(R.styleable.SGCollapse_sgkit_expand, false);

        hasAnimation = a.getBoolean(R.styleable.SGCollapse_sgkit_hasAnimation, true);
        mAnimationDuration = a.getInt(R.styleable.SGCollapse_sgkit_animationTimes, mAnimationDuration);

        int iconImageColor = a.getColor(R.styleable.SGCollapse_sgkit_iconImageColor, dataBean.getIconImageColor());
        setIconImageColor(iconImageColor);
        int iconImageSize = a.getColor(R.styleable.SGCollapse_sgkit_iconImageSize, dataBean.getIconImageSize());
        setIconImageSize(iconImageSize);

        int iconLeftColor = a.getColor(R.styleable.SGCollapse_sgkit_leftIconColor, dataBean.getIconLeftColor());
        setIconLeftColor(iconLeftColor);
        int iconLeftSize = a.getColor(R.styleable.SGCollapse_sgkit_leftIconSize, dataBean.getIconLeftSize());
        setIconLeftSize(iconLeftSize);

        int iconRightColor = a.getColor(R.styleable.SGCollapse_sgkit_rightIconColor, dataBean.getIconRightColor());
        setIconRightColor(iconRightColor);
        int iconRightSize = a.getColor(R.styleable.SGCollapse_sgkit_rightIconSize, dataBean.getIconRightSize());
        setIconRightSize(iconRightSize);

        a.recycle();
        verifyDefaultValues();
        initView();
    }

    private void verifyDefaultValues() {
        if (title == null) {
            title = "";
        }
        if (content == null) {
            content = "";
        }
        if (iconImageDown == null) {
            iconImageDown = "&#xe9b7;";
        }
        if (iconImageUp == null) {
            iconImageUp = "&#xe9b6;";
        }
    }

    private void initView() {
        initTitleView();
        initDivider();
        initContentView();
    }

    private void initTitleView() {
        flTitle.removeAllViews();
        flTitle.addView(defaultTitleView());
        flTitle.setEnabled(!isDisabled);
        flTitle.setOnClickListener(v -> {
            isExpanded = !isExpanded;
            if (onAnimationStartCallback != null) {
                onAnimationStartCallback.onAnimationStart(isExpanded);
            }
            setContentExpandOrCollapse();
        });
    }

    private void initDivider() {
        int paddingSize = getResources().getDimensionPixelSize(R.dimen.px48);
        viewDivider.setPadding(paddingSize, 0, 0, 0);
    }

    private void initContentView() {
        flContent.removeAllViews();
        flContent.addView(defaultContentView());
        flContent.post(() -> {
            collapseHeight = flContent.getHeight();
            flContent.setVisibility(isExpanded ? VISIBLE : GONE);
        });
    }

    /**
     * 设置自定义标题View
     *
     * @param titleView 自定义标题View
     */
    public SGCollapse setTitleView(View titleView) {
        this.titleView = titleView;
        flTitle.removeAllViews();
        if (titleView == null) {
            flTitle.addView(defaultTitleView());
        } else {
            flTitle.addView(titleView);
        }
        return this;
    }

    /**
     * 设置自定义标题View
     *
     * @param layoutTitleView 自定义标题布局
     */
    public SGCollapse setTitleView(@LayoutRes int layoutTitleView) {
        setTitleView(LayoutInflater.from(getContext()).inflate(layoutTitleView, flTitle, false));
        return this;
    }

    /**
     * 获取自定义标题View
     *
     * @return 自定义标题View
     */
    public View getTitleView() {
        return titleView;
    }

    /**
     * 设置自定义面板View
     *
     * @param contentView 自定义面板View
     */
    public SGCollapse setContentView(View contentView) {
        this.contentView = contentView;
        flContent.removeAllViews();
        if (contentView == null) {
            flContent.addView(defaultContentView());
        } else {
            flContent.addView(contentView);
        }
        return this;
    }

    /**
     * 获取自定义面板View
     *
     * @return 自定义面板View
     */
    public View getContentView() {
        return contentView;
    }

    /**
     * 设置自定义面板View
     *
     * @param layoutContentView 自定义面板布局
     */
    public SGCollapse setContentView(@LayoutRes int layoutContentView) {
        setContentView(LayoutInflater.from(getContext()).inflate(layoutContentView, flContent, false));
        return this;
    }

    /**
     * 默认的titleView样式
     *
     * @return textView
     */
    public View defaultTitleView() {
        defaultTitleView = LayoutInflater.from(getContext()).inflate(R.layout.layout_sg_collapse_default_title, flTitle, false);
        tvTitle = defaultTitleView.findViewById(R.id.tv_title);
        expandIcon = defaultTitleView.findViewById(R.id.expand_icon);
        titleLeftIcon = defaultTitleView.findViewById(R.id.title_left_icon);
        titleRightIcon = defaultTitleView.findViewById(R.id.title_right_icon);
        tvTitle.setText(title);
        tvTitle.setTextColor(titleColor);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
        expandIcon.setText(iconImageDown);
        if (iconLeft != null) {
            titleLeftIcon.setVisibility(VISIBLE);
            titleLeftIcon.setText(iconLeft);
            titleLeftIcon.setOnClickListener(v -> {
                if (onIconLeftOnClickListener != null) {
                    onIconLeftOnClickListener.onClick(v);
                }
            });
        }
        if (iconRight != null) {
            titleRightIcon.setVisibility(VISIBLE);
            titleRightIcon.setText(iconRight);
            titleRightIcon.setOnClickListener(v -> {
                if (onIconRightOnClickListener != null) {
                    onIconRightOnClickListener.onClick(v);
                }
            });
        }
        expandIcon.setVisibility(isHiddenIcon ? GONE : VISIBLE);
        tvTitle.setTextColor(isDisabled ? DISABLED_COLOR : titleColor);
        return defaultTitleView;
    }

    /**
     * 设置标题内容
     *
     * @param title 标题内容
     */
    public SGCollapse setTitle(CharSequence title) {
        this.title = title;
        if (defaultTitleView != null) {
            tvTitle.setText(title);
        }
        return this;
    }

    /**
     * 设置标题字体颜色
     *
     * @param titleColor 标题字体颜色
     */
    public SGCollapse setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        if (defaultTitleView != null && titleColor != 0) {
            tvTitle.setTextColor(titleColor);
        }
        return this;
    }

    /**
     * 设置标题字体大小
     *
     * @param titleSize 标题字体大小 px
     */
    public SGCollapse setTitleSize(int titleSize) {
        this.titleSize = titleSize;
        if (defaultTitleView != null && titleSize != 0) {
            tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
        }
        return this;
    }

    /**
     * 设置展开图标
     *
     * @param iconImageUp 展开图标
     */
    public SGCollapse setIconImageUp(String iconImageUp) {
        this.iconImageUp = iconImageUp;
        if (defaultTitleView != null) {
            expandIcon.setText(iconImageUp);
        }
        return this;
    }

    /**
     * 设置折叠图标
     *
     * @param iconImageDown 折叠图标
     */
    public SGCollapse setIconImageDown(String iconImageDown) {
        this.iconImageDown = iconImageDown;
        if (defaultTitleView != null) {
            expandIcon.setText(iconImageDown);
        }
        return this;
    }

    /**
     * 设置展开折叠图标字体颜色
     *
     * @param iconImageColor 展开折叠图标字体颜色
     */
    public SGCollapse setIconImageColor(int iconImageColor) {
        if (defaultTitleView != null && iconImageColor != 0) {
            expandIcon.setTextColor(iconImageColor);
        }
        return this;
    }

    /**
     * 设置展开折叠图标字体大小
     *
     * @param iconImageSize 展开折叠图标字体大小 px
     */
    public SGCollapse setIconImageSize(int iconImageSize) {
        if (defaultTitleView != null && iconImageSize != 0) {
            expandIcon.setTextSize(TypedValue.COMPLEX_UNIT_PX, iconImageSize);
        }
        return this;
    }

    /**
     * 设置标题左侧图标
     *
     * @param iconLeft 标题左侧图标 为null不显示
     */
    public SGCollapse setIconLeft(String iconLeft) {
        this.iconLeft = iconLeft;
        if (defaultTitleView != null) {
            if (iconLeft != null) {
                titleLeftIcon.setVisibility(VISIBLE);
                titleLeftIcon.setText(iconLeft);
            } else {
                titleLeftIcon.setVisibility(GONE);
            }
        }
        return this;
    }

    /**
     * 设置标题左侧图标字体颜色
     *
     * @param iconLeftColor 标题左侧图标字体颜色
     */
    public SGCollapse setIconLeftColor(int iconLeftColor) {
        if (defaultTitleView != null && iconLeftColor != 0) {
            titleLeftIcon.setTextColor(iconLeftColor);
        }
        return this;
    }

    /**
     * 设置标题左侧图标字体大小
     *
     * @param iconLeftSize 标题左侧图标字体大小 px
     */
    public SGCollapse setIconLeftSize(int iconLeftSize) {
        if (defaultTitleView != null && iconLeftSize != 0) {
            titleLeftIcon.setTextSize(TypedValue.COMPLEX_UNIT_PX, iconLeftSize);
        }
        return this;
    }

    /**
     * 设置标题左侧图标点击事件
     *
     * @param onIconLeftOnClickListener 点击事件
     */
    public SGCollapse setIconLeftOnClickListener(OnTitleIconOnClickListener onIconLeftOnClickListener) {
        this.onIconLeftOnClickListener = onIconLeftOnClickListener;
        return this;
    }

    /**
     * 设置标题右侧图标
     *
     * @param iconRight 标题右侧图标 为null不显示
     */
    public SGCollapse setIconRight(String iconRight) {
        this.iconRight = iconRight;
        if (defaultTitleView != null) {
            if (iconRight != null) {
                titleRightIcon.setVisibility(VISIBLE);
                titleRightIcon.setText(iconRight);
            } else {
                titleRightIcon.setVisibility(GONE);
            }
        }
        return this;
    }

    /**
     * 设置标题右侧图标字体颜色
     *
     * @param iconRightColor 标题右侧图标字体颜色
     */
    public SGCollapse setIconRightColor(int iconRightColor) {
        if (defaultTitleView != null && iconRightColor != 0) {
            titleRightIcon.setTextColor(iconRightColor);
        }
        return this;
    }

    /**
     * 设置标题右侧图标字体大小
     *
     * @param iconRightSize 标题右侧图标字体大小 px
     */
    public SGCollapse setIconRightSize(int iconRightSize) {
        if (defaultTitleView != null && iconRightSize != 0) {
            titleRightIcon.setTextSize(TypedValue.COMPLEX_UNIT_PX, iconRightSize);
        }
        return this;
    }

    /**
     * 设置标题右侧图标点击事件
     *
     * @param onIconRightOnClickListener 点击事件
     */
    public SGCollapse setIconRightOnClickListener(OnTitleIconOnClickListener onIconRightOnClickListener) {
        this.onIconRightOnClickListener = onIconRightOnClickListener;
        return this;
    }

    /**
     * 设置折叠展开图标显示隐藏
     *
     * @param isHiddenIcon 是 隐藏 否 显示 默认显示
     */
    public SGCollapse setHiddenIcon(Boolean isHiddenIcon) {
        this.isHiddenIcon = isHiddenIcon;
        if (defaultTitleView != null) {
            expandIcon.setVisibility(isHiddenIcon ? GONE : VISIBLE);
        }
        return this;
    }

    /**
     * 设置标题区域是否可点击
     *
     * @param isDisabled 是 不可点 否 可点 默认可点击
     */
    public SGCollapse setDisabled(Boolean isDisabled) {
        this.isDisabled = isDisabled;
        if (defaultTitleView != null) {
            flTitle.setEnabled(!isDisabled);
            tvTitle.setTextColor(isDisabled ? DISABLED_COLOR : titleColor);
        }
        return this;
    }

    /**
     * 设置图标展开折叠
     *
     * @param isExpanded 是 展开 否 折叠 默认折叠
     */
    public SGCollapse setExpanded(Boolean isExpanded) {
        this.isExpanded = isExpanded;
        noAnimationExpandOrCollapse();
        return this;
    }

    /**
     * 设置图标展开折叠是否需要动画
     *
     * @param hasAnimation 是 需要动画 否 不需要动画 默认需要动画
     */
    public SGCollapse setHasAnimation(Boolean hasAnimation) {
        this.hasAnimation = hasAnimation;
        return this;
    }

    /**
     * 设置动画时间
     *
     * @param mAnimationDuration 动画时间 默认300ms
     */
    public SGCollapse setAnimationDuration(int mAnimationDuration) {
        this.mAnimationDuration = mAnimationDuration;
        return this;
    }

    /**
     * 默认的contentView样式
     *
     * @return textView
     */
    public AppCompatTextView defaultContentView() {
        int paddingSize = getResources().getDimensionPixelSize(R.dimen.px36);
        tvContent = new AppCompatTextView(getContext());
        tvContent.setText(content);
        tvContent.setTextColor(contentColor);
        tvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentSize);
        tvContent.setPadding(paddingSize, paddingSize, paddingSize, paddingSize);
        return tvContent;
    }

    /**
     * 设置面板内容
     *
     * @param content 面板内容
     */
    public SGCollapse setContent(CharSequence content) {
        this.content = content;
        if (tvContent != null) {
            tvContent.setText(content);
        }
        return this;
    }

    /**
     * 设置面板字体颜色
     *
     * @param contentColor 面板字体颜色
     */
    public SGCollapse setContentColor(int contentColor) {
        this.contentColor = contentColor;
        if (tvContent != null && contentColor != 0) {
            tvContent.setTextColor(contentColor);
        }
        return this;
    }

    /**
     * 设置面板字体大小
     *
     * @param contentSize 面板字体大小 px
     */
    public SGCollapse setContentSize(int contentSize) {
        this.contentSize = contentSize;
        if (tvContent != null && contentSize != 0) {
            tvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentSize);
        }
        return this;
    }

    /**
     * 获取分割线
     *
     * @return 分割线view
     */
    public View getDivider() {
        return viewDivider;
    }

    /**
     * 设置展开折叠监听
     *
     * @param collapseCallback 展开折叠监听
     */
    public SGCollapse setOnCollapseCallback(OnCollapseCallback collapseCallback) {
        this.onCollapseCallback = collapseCallback;
        return this;
    }

    /**
     * 设置动画开始时回调
     *
     * @param onAnimationStartCallback 动画开始时回调
     */
    public SGCollapse setOnAnimationStartCallback(OnAnimationStartCallback onAnimationStartCallback) {
        this.onAnimationStartCallback = onAnimationStartCallback;
        return this;
    }

    /**
     * 根据实体类数据设置
     *
     * @param dataBean 实体类数据
     */
    public void setDataBean(SGCollapseDataBean dataBean) {
        if (dataBean == null){
            return;
        }
        setTitle(dataBean.getTitle());
        setTitleColor(dataBean.getTitleColor());
        setTitleSize(dataBean.getTitleSize());
        setContent(dataBean.getContent());
        setContentColor(dataBean.getContentColor());
        setContentSize(dataBean.getContentSize());
        setIconImageDown(dataBean.getIconImageDown());
        setIconImageUp(dataBean.getIconImageUp());
        setIconImageColor(dataBean.getIconImageColor());
        setIconImageSize(dataBean.getIconImageSize());
        setIconLeft(dataBean.getIconLeft());
        setIconLeftColor(dataBean.getIconLeftColor());
        setIconLeftSize(dataBean.getIconLeftSize());
        setIconRight(dataBean.getIconRight());
        setIconRightColor(dataBean.getIconRightColor());
        setIconRightSize(dataBean.getIconRightSize());
        setExpanded(dataBean.isExpanded());
        setHiddenIcon(dataBean.isHiddenIcon());
        setDisabled(dataBean.isDisabled());
        setAnimationDuration(dataBean.getAnimationDuration());
    }

    /**
     * 设置展开收起
     */
    private void setContentExpandOrCollapse() {
        SGUILog.d(TAG, "setContentExpandOrCollapse: collapseHeight: " + collapseHeight);
        if (hasAnimation && collapseHeight > 0) {
            animationExpandOrCollapse();
        } else {
            noAnimationExpandOrCollapse();
        }
    }

    /**
     * 无动画展开收起
     */
    private void noAnimationExpandOrCollapse() {
        if (defaultTitleView != null) {
            expandIcon.setText(isExpanded ? iconImageUp : iconImageDown);
        }
        flContent.setVisibility(isExpanded ? VISIBLE : GONE);
        if (onCollapseCallback != null) {
            onCollapseCallback.onCollapse(isExpanded);
        }
    }

    /**
     * 使用动画展开收起
     */
    private void animationExpandOrCollapse() {
        ValueAnimator animation;
        if (!isExpanded) {
            animation = ValueAnimator.ofInt(collapseHeight, 0);
        } else {
            animation = ValueAnimator.ofInt(0, collapseHeight);
        }
        if (defaultTitleView != null) {
            expandIcon.setText(isExpanded ? iconImageUp : iconImageDown);
        }
        animation.addUpdateListener(animation1 -> {
            ViewGroup.LayoutParams params = flContent.getLayoutParams();
            params.height = (int) animation1.getAnimatedValue();
            flContent.setLayoutParams(params);
        });
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //防止展开时动画不显示
                if (flContent.getVisibility() != VISIBLE) {
                    flContent.setVisibility(VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (onCollapseCallback != null) {
                    onCollapseCallback.onCollapse(isExpanded);
                }
                flContent.setVisibility(isExpanded ? VISIBLE : GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        flContent.clearAnimation();
        animation.setDuration(mAnimationDuration).start();
    }

    public interface OnAnimationStartCallback {
        /**
         * 动画开始时回调
         *
         * @param isExpand 是 展开 否 折叠
         */
        void onAnimationStart(boolean isExpand);
    }

    public interface OnCollapseCallback {
        /**
         * 展开折叠监听
         *
         * @param isExpand 是 展开 否 折叠
         */
        void onCollapse(boolean isExpand);
    }

    public interface OnTitleIconOnClickListener {
        /**
         * 标题左右icon的点击事件
         *
         * @param view 标题左右icon
         */
        void onClick(View view);
    }
}
