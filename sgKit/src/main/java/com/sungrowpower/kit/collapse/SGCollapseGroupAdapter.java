package com.sungrowpower.kit.collapse;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link SGCollapse}组 手风琴样式
 * @param <T> 需继承{@link SGCollapseBean}
 */
public abstract class SGCollapseGroupAdapter<T extends SGCollapseBean> extends RecyclerView.Adapter<SGCollapseGroupAdapter.SGCollapseGroupViewHolder> {

    private final Context context;

    /**
     * 数据源
     */
    private final List<T> list;

    /**
     * 是否需要手风琴样式 默认否
     */
    private boolean useAccordion = false;

    /**
     * 一键禁用 默认否
     */
    private boolean disabled = false;

    /**
     * 是 需要动画 否 不需要动画 默认需要动画
     */
    private boolean hasAnimation;

    /**
     * 展开折叠监听
     */
    private OnCollapseGroupCallback onCollapseGroupCallback;

    public SGCollapseGroupAdapter(Context context , List<T> list) {
        if (context == null){
            throw new IllegalArgumentException("context cannot be null");
        }
        this.list = new ArrayList<>(list);
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull SGCollapseGroupViewHolder holder, int position) {
        T item = list.get(position);
        onBindCollapseViewHolder(holder,position,item);
        if (holder.itemView instanceof SGCollapse) {
            SGCollapse sgCollapse = (SGCollapse) holder.itemView;
            if (disabled){
                sgCollapse.setDisabled(true);
            }
            sgCollapse.setHasAnimation(hasAnimation);
            sgCollapse.setOnAnimationStartCallback(isExpand -> {
                if (useAccordion) {
                    for (int i = 0; i < getData().size(); i++) {
                        if (getData().get(i).isExpand()) {
                            getData().get(i).setExpand(false);
                            notifyItemChanged(i);
                        }
                    }
                }
                if (item.isExpand() != isExpand) {
                    item.setExpand(isExpand);
                }
            });

            sgCollapse.setOnCollapseCallback(isExpand -> {
                Boolean[] isExpands = new Boolean[getData().size()];
                for (int i = 0; i < getData().size(); i++) {
                    isExpands[i] = getData().get(i).isExpand();
                }
                onCollapseGroupCallback.onCollapse(isExpands);
            });
        }
    }

    @NonNull
    @Override
    public final SGCollapseGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SGCollapseGroupViewHolder(new SGCollapse(context));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 获取数据源
     * @return 数据源
     */
    public List<T> getData(){
        return list;
    }

    /**
     * 设置是否使用手风琴样式
     * @param useAccordion true 是 false 否  默认否
     */
    public SGCollapseGroupAdapter<T> setUseAccordion(boolean useAccordion){
        this.useAccordion = useAccordion;
        return this;
    }

    /**
     * 获取是否使用手风琴样式
     * @return true 是 false 否
     */
    public boolean getUseAccordion(){
        return useAccordion;
    }

    /**
     * 设置一键禁用
     * @param disabled true 是 false 否  默认否
     */
    public SGCollapseGroupAdapter<T> setDisabled(boolean disabled){
        this.disabled = disabled;
        return this;
    }

    /**
     * 获取是否一键禁用
     * @return true 是 false 否
     */
    public boolean getDisabled(){
        return disabled;
    }

    /**
     * 设置图标展开折叠是否需要动画
     *
     * @param hasAnimation 是 需要动画 否 不需要动画 默认需要动画
     */
    public SGCollapseGroupAdapter<T> setHasAnimation(Boolean hasAnimation) {
        this.hasAnimation = hasAnimation;
        return this;
    }

    /**
     * 设置展开折叠监听
     */
    public SGCollapseGroupAdapter<T> setOnCollapseGroupCallback(OnCollapseGroupCallback onCollapseGroupCallback){
        this.onCollapseGroupCallback = onCollapseGroupCallback;
        return this;
    }


    public interface OnCollapseGroupCallback {
        void onCollapse(Boolean[] isExpands);
    }
    abstract public void onBindCollapseViewHolder(SGCollapseGroupViewHolder holder, int position,T item);

    public static class SGCollapseGroupViewHolder extends RecyclerView.ViewHolder {

        public SGCollapseGroupViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
