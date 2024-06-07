package com.sungrowpower.kit.collapse;

import android.graphics.Color;

import com.sungrowpower.kit.R;
import com.sungrowpower.kit.SGKit;
import com.sungrowpower.kit.util.SGUILog;

import org.json.JSONException;
import org.json.JSONObject;

public class SGCollapseColorMap {
    /**
     * 默认标题颜色
     */
    public static int sgkit_text_title_color = SGKit.INSTANCE.getContext().getResources().getColor(R.color.sgkit_text_title);
    /**
     * 默认标题字体大小
     */
    public static int sgkit_text_title_size = SGKit.INSTANCE.getContext().getResources().getDimensionPixelSize(R.dimen.px48);
    /**
     * 默认内容颜色
     */
    public static int sgkit_text_content_color = SGKit.INSTANCE.getContext().getResources().getColor(R.color.sgkit_text_light_gray);
    /**
     * 默认内容字体大小
     */
    public static int sgkit_text_content_size = SGKit.INSTANCE.getContext().getResources().getDimensionPixelSize(R.dimen.px36);

    /**
     * 标题颜色
     */
    public static int titleColor = sgkit_text_title_color;
    /**
     * 标题字体大小
     */
    public static int titleSize = sgkit_text_title_size;
    /**
     * 内容颜色
     */
    public static int contentColor = sgkit_text_content_color;
    /**
     * 内容字体大小
     */
    public static int contentSize = sgkit_text_content_size;
    /**
     * 展开折叠图标颜色
     */
    public static int iconImageColor = sgkit_text_title_color;
    /**
     * 展开折叠图标字体大小
     */
    public static int iconImageSize = sgkit_text_title_size;
    /**
     * 标题左侧图标颜色
     */
    public static int iconLeftColor = sgkit_text_title_color;
    /**
     * 标题左侧图标字体大小
     */
    public static int iconLeftSize = sgkit_text_title_size;
    /**
     * 标题右侧图标颜色
     */
    public static int iconRightColor = sgkit_text_title_color;
    /**
     * 标题右侧图标字体大小
     */
    public static int iconRightSize = sgkit_text_title_size;

    /**
     * 解析json设置颜色字体大小
     * @param json json数据
     */
    public void parseJson(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            titleColor = Color.parseColor(jsonObject.getString("titleColor"));
            titleSize = jsonObject.getInt("titleSize");
            contentColor = Color.parseColor(jsonObject.getString("contentColor"));
            contentSize = jsonObject.getInt("contentSize");
            iconImageColor = Color.parseColor(jsonObject.getString("iconImageColor"));
            iconImageSize = jsonObject.getInt("iconImageSize");
            iconLeftColor = Color.parseColor(jsonObject.getString("iconLeftColor"));
            iconLeftSize = jsonObject.getInt("iconLeftSize");
            iconRightColor = Color.parseColor(jsonObject.getString("iconRightColor"));
            iconRightSize = jsonObject.getInt("iconRightSize");
        } catch (JSONException e) {
            SGUILog.e("SGCollapse","SGCollapse parseJson error!");
            e.printStackTrace();
        }
    }
}
