package com.sungrowpower.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.sungrowpower.demo.databinding.ActivitySgcollapseBinding;
import com.sungrowpower.kit.collapse.SGCollapse;
import com.sungrowpower.kit.collapse.SGCollapseGroupAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class SGCollapseActivity extends AppCompatActivity {

    private ActivitySgcollapseBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_sgcollapse);
        dataBinding.setLifecycleOwner(this);

        initListener();
    }

    private void initListener() {
        dataBinding.scCollapseSingle1.setOnCollapseCallback(isExpand -> {
            String toast = isExpand ? "展开了" : "收起了";
            Toast.makeText(SGCollapseActivity.this, toast, Toast.LENGTH_SHORT).show();
        });

        SGCollapse collapse = new SGCollapse(this);
        int padding = getResources().getDimensionPixelSize(R.dimen.px36);
        collapse.setPadding(padding, 0, padding, 0);
        collapse.setTitle("Subtitle")
                .setExpanded(true)
                .setContent("Subtitle Content Subtitle Content Subtitle Content Subtitle Content Subtitle Content Subtitle Content Subtitle Content Subtitle Content")
                .setOnCollapseCallback(isExpand -> {
                    String toast = isExpand ? "Subtitle展开了" : "Subtitle收起了";
                    Toast.makeText(SGCollapseActivity.this, toast, Toast.LENGTH_SHORT).show();
                });
        dataBinding.scCollapseMultiple1.setHasAnimation(false)
                .setExpanded(true)
                .setContentView(collapse);

        dataBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<SGCollapseBeanItem> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SGCollapseBeanItem bean = new SGCollapseBeanItem();
            bean.setTitle(getString(R.string.hello_world) + i);
            bean.setContent(getString(R.string.hello_world_times) + i);
            list.add(bean);
        }
        SGCollapseGroupAdapter<SGCollapseBeanItem> adapter = new SGCollapseGroupAdapter<SGCollapseBeanItem>(this, list) {
            @Override
            public void onBindCollapseViewHolder(SGCollapseGroupViewHolder holder, int position, SGCollapseBeanItem item) {
                if (holder.itemView instanceof SGCollapse) {
                    SGCollapse sgCollapse = (SGCollapse) holder.itemView;
                    sgCollapse.setExpanded(item.isExpand());
                    sgCollapse.setTitle(item.getTitle());
                    sgCollapse.setContent(item.getContent());
                    sgCollapse.setContentColor(getResources().getColor(R.color.sgkit_brand_routine));
                }
            }
        };
        adapter.setUseAccordion(true)
                .setDisabled(false)
                .setOnCollapseGroupCallback(isExpands -> Toast.makeText(SGCollapseActivity.this, "列表内各展开折叠状态：" + Arrays.toString(isExpands), Toast.LENGTH_SHORT).show());
        dataBinding.rvList.setAdapter(adapter);
        dataBinding.rvList.setAnimation(null);

        dataBinding.scCollapseCustom1.setTitle("自定义字体大小颜色")
                .setTitleSize(60)
                .setTitleColor(getResources().getColor(R.color.sgkit_brand_routine))
                .setIconLeft("&#xe9a8;")
                .setIconLeftSize(60)
                .setIconLeftColor(getResources().getColor(R.color.sgkit_brand_routine))
                .setContentSize(40)
                .setContentColor(getResources().getColor(R.color.sgkit_brand_routine))
                .setIconLeftOnClickListener(view -> Toast.makeText(SGCollapseActivity.this, "点击了标题左侧按钮！", Toast.LENGTH_SHORT).show());

        dataBinding.scCollapseCustom2.setHiddenIcon(true)
                .setIconRight("&#xe9a5;")
                .setIconRightSize(40)
                .setIconRightColor(getResources().getColor(R.color.sgkit_error_click))
                .setIconRightOnClickListener(view -> Toast.makeText(SGCollapseActivity.this, "点击了标题右侧按钮！", Toast.LENGTH_SHORT).show());
    }
}