package com.sungrowpower.kit

import android.app.Application
import android.content.Context
import android.content.res.Resources

object SGKit {
  private lateinit var context: Application

  fun init(context: Application) {
    this.context = context
  }

  fun getResource(): Resources {
    return context.resources
  }

  fun getContext(): Context {
    return context.applicationContext
  }
}