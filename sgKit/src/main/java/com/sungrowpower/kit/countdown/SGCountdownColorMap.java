package com.sungrowpower.kit.countdown;

import android.graphics.Color;

import com.sungrowpower.kit.R;
import com.sungrowpower.kit.SGKit;
import com.sungrowpower.kit.util.SGUILog;

import org.json.JSONException;
import org.json.JSONObject;

public class SGCountdownColorMap {
    /**
     * 默认颜色
     */
    public static int sgkit_text_title_color = SGKit.INSTANCE.getContext().getResources().getColor(R.color.sgkit_text_title);
    /**
     * 默认字体大小
     */
    public static int sgkit_text_title_size = SGKit.INSTANCE.getContext().getResources().getDimensionPixelSize(R.dimen.px48);

    /**
     * 标题颜色
     */
    public static int textColor = sgkit_text_title_color;
    /**
     * 标题字体大小
     */
    public static int textSize = sgkit_text_title_size;

    /**
     * 解析json设置颜色字体大小
     * @param json json数据
     */
    public void parseJson(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            textColor = Color.parseColor(jsonObject.getString("textColor"));
            textSize = jsonObject.getInt("textSize");
        } catch (JSONException e) {
            SGUILog.e("SGCountdown","SGCountdown parseJson error!");
            e.printStackTrace();
        }
    }
}
