package com.sungrowpower.kit.dialog

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.sungrowpower.kit.R
import com.sungrowpower.kit.button.ButtonDataBean
import com.sungrowpower.kit.button.SGButton

class DialogButtonListAdapter(
  val context: Context,
  val arrayList: ArrayList<String>,
  val styleList: ArrayList<ButtonDataBean>?,
) : BaseAdapter() {

  override fun getCount(): Int {
    return arrayList.size
  }

  override fun getItem(position: Int): Any {
    return arrayList[position]
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getView(
    position: Int,
    convertView: View?,
    parent: ViewGroup?,
  ): View {
    val get = arrayList.get(position)

    var view: View
    var holder: Holder
    if (convertView == null) {
      holder = Holder()
      view = View.inflate(context, R.layout.sgkit_sgbutton, null)
      val findViewById = view.findViewById<SGButton>(R.id.sgkit_button)
      holder.button = findViewById
      view.setTag(holder)
    } else {
      view = convertView
      holder = view.getTag() as Holder
    }
    holder.button?.text = get
    if (styleList != null && styleList.size > position) {
      val get1 = styleList.get(position)
      holder.button?.setButtonData(get1)?.build()
    } else {
      holder.button?.setDefaultStateTextColor(context.resources.getColor(R.color.sgkit_text_title))
        ?.setDefaultStateColor(context.resources.getColor(R.color.sgkit_fill_white))
    }
    return view
  }

  class Holder {
    var button: SGButton? = null
  }
}