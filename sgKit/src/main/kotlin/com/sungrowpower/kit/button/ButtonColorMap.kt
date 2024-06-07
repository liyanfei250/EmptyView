package com.sungrowpower.kit.button

import android.graphics.Color
import com.sungrowpower.kit.R
import com.sungrowpower.kit.SGKit
import org.json.JSONObject

/**
 * 处理默认的颜色类，以及解析本地的json类
 * */
object ButtonColorMap {
  var pressColor: Int = SGKit.getResource().getColor(R.color.sgkit_fill_white)
  var unPressColor: Int = SGKit.getResource().getColor(R.color.sgkit_fill_white)
  var defaultStateColor: Int = SGKit.getResource().getColor(R.color.sgkit_fill_white)
  var disableStateColor: Int = SGKit.getResource().getColor(R.color.sgkit_fill_white)

  var pressBorderColor: Int = SGKit.getResource().getColor(R.color.sgkit_fill_white)
  var unPressBorderColor: Int = SGKit.getResource().getColor(R.color.sgkit_fill_white)
  var disableStateBorderColor: Int = SGKit.getResource().getColor(R.color.sgkit_fill_white)

  var solidColor: Int = SGKit.getResource().getColor(R.color.sgkit_fill_white)
  var strokeColor: Int = SGKit.getResource().getColor(R.color.sgkit_fill_white)

  fun getColorByName(colorKey: String) {
  }

  fun initJson(content: String) {
    try {
      val jsonObject = JSONObject(content)
      pressColor = Color.parseColor(jsonObject.getString("pressColor"))
      unPressColor = Color.parseColor(jsonObject.getString("unPressColor"))
      defaultStateColor = Color.parseColor(jsonObject.getString("defaultStateColor"))
      disableStateColor = Color.parseColor(jsonObject.getString("disableStateColor"))
      pressBorderColor = Color.parseColor(jsonObject.getString("pressBorderColor"))
      unPressBorderColor = Color.parseColor(jsonObject.getString("unPressBorderColor"))
      disableStateBorderColor = Color.parseColor(jsonObject.getString("disableStateBorderColor"))
      solidColor = Color.parseColor(jsonObject.getString("solidColor"))
      strokeColor = Color.parseColor(jsonObject.getString("strokeColor"))
    } catch (e: Exception) {
    }
  }
}