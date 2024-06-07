package com.sungrowpower.kit.button

import androidx.annotation.ColorInt
import com.sungrowpower.kit.layout.ISGUILayout
import com.sungrowpower.kit.layout.ISGUILayout.HideRadiusSide
import com.sungrowpower.kit.layout.SGLayoutHelper

interface SGLayout : ISGUILayout {
  var mLayoutHelper: SGLayoutHelper
  override fun updateTopDivider(
    topInsetLeft: Int,
    topInsetRight: Int,
    topDividerHeight: Int,
    topDividerColor: Int,
  ) {
    mLayoutHelper.updateTopDivider(topInsetLeft, topInsetRight, topDividerHeight, topDividerColor)
  }

  override fun updateBottomDivider(
    bottomInsetLeft: Int,
    bottomInsetRight: Int,
    bottomDividerHeight: Int,
    bottomDividerColor: Int,
  ) {
    mLayoutHelper.updateBottomDivider(bottomInsetLeft, bottomInsetRight, bottomDividerHeight, bottomDividerColor)
  }

  override fun updateLeftDivider(
    leftInsetTop: Int,
    leftInsetBottom: Int,
    leftDividerWidth: Int,
    leftDividerColor: Int,
  ) {
    mLayoutHelper.updateLeftDivider(leftInsetTop, leftInsetBottom, leftDividerWidth, leftDividerColor)
  }

  override fun updateRightDivider(
    rightInsetTop: Int,
    rightInsetBottom: Int,
    rightDividerWidth: Int,
    rightDividerColor: Int,
  ) {
    mLayoutHelper.updateRightDivider(rightInsetTop, rightInsetBottom, rightDividerWidth, rightDividerColor)
  }

  override fun onlyShowTopDivider(
    topInsetLeft: Int,
    topInsetRight: Int,
    topDividerHeight: Int,
    topDividerColor: Int,
  ) {
    mLayoutHelper.onlyShowTopDivider(topInsetLeft, topInsetRight, topDividerHeight, topDividerColor)
  }

  override fun onlyShowBottomDivider(
    bottomInsetLeft: Int,
    bottomInsetRight: Int,
    bottomDividerHeight: Int,
    bottomDividerColor: Int,
  ) {
    mLayoutHelper.onlyShowBottomDivider(bottomInsetLeft, bottomInsetRight, bottomDividerHeight, bottomDividerColor)
  }

  override fun onlyShowLeftDivider(
    leftInsetTop: Int,
    leftInsetBottom: Int,
    leftDividerWidth: Int,
    leftDividerColor: Int,
  ) {
    mLayoutHelper.onlyShowLeftDivider(leftInsetTop, leftInsetBottom, leftDividerWidth, leftDividerColor)
  }

  override fun onlyShowRightDivider(
    rightInsetTop: Int,
    rightInsetBottom: Int,
    rightDividerWidth: Int,
    rightDividerColor: Int,
  ) {
    mLayoutHelper.onlyShowRightDivider(rightInsetTop, rightInsetBottom, rightDividerWidth, rightDividerColor)
  }

  override fun setTopDividerAlpha(dividerAlpha: Int) {
    mLayoutHelper.setTopDividerAlpha(dividerAlpha)
  }

  override fun setBottomDividerAlpha(dividerAlpha: Int) {
    mLayoutHelper.setBottomDividerAlpha(dividerAlpha)
  }

  override fun setLeftDividerAlpha(dividerAlpha: Int) {
    mLayoutHelper.setLeftDividerAlpha(dividerAlpha)
  }

  override fun setRightDividerAlpha(dividerAlpha: Int) {
    mLayoutHelper.setRightDividerAlpha(dividerAlpha)
  }

  override fun setHideRadiusSide(hideRadiusSide: Int) {
    mLayoutHelper.hideRadiusSide = hideRadiusSide
  }

  override fun getHideRadiusSide(): Int {
    return mLayoutHelper.hideRadiusSide
  }

  override fun setRadiusAndShadow(
    radius: Int,
    shadowElevation: Int,
    shadowAlpha: Float,
  ) {
    mLayoutHelper.setRadiusAndShadow(radius, shadowElevation, shadowAlpha)
  }

  override fun setRadiusAndShadow(
    radius: Int,
    @HideRadiusSide hideRadiusSide: Int,
    shadowElevation: Int,
    shadowAlpha: Float,
  ) {
    mLayoutHelper.setRadiusAndShadow(radius, hideRadiusSide, shadowElevation, shadowAlpha)
  }

  override fun setRadiusAndShadow(
    radius: Int,
    hideRadiusSide: Int,
    shadowElevation: Int,
    shadowColor: Int,
    shadowAlpha: Float,
  ) {
    mLayoutHelper.setRadiusAndShadow(radius, hideRadiusSide, shadowElevation, shadowColor, shadowAlpha)
  }

  override fun setRadius(radius: Int) {
    mLayoutHelper.radius = radius
  }

  override fun setRadius(
    radius: Int,
    @HideRadiusSide hideRadiusSide: Int,
  ) {
    mLayoutHelper.setRadius(radius, hideRadiusSide)
  }

  override fun getRadius(): Int {
    return mLayoutHelper.radius
  }

  override fun setOutlineInset(
    left: Int,
    top: Int,
    right: Int,
    bottom: Int,
  ) {
    mLayoutHelper.setOutlineInset(left, top, right, bottom)
  }

  override fun setBorderColor(@ColorInt borderColor: Int) {
    mLayoutHelper.setBorderColor(borderColor)
  }

  override fun setBorderWidth(borderWidth: Int) {
    mLayoutHelper.setBorderWidth(borderWidth)
  }

  override fun setShowBorderOnlyBeforeL(showBorderOnlyBeforeL: Boolean) {
    mLayoutHelper.setShowBorderOnlyBeforeL(showBorderOnlyBeforeL)
  }

  override fun setWidthLimit(widthLimit: Int): Boolean {
    if (mLayoutHelper.setWidthLimit(widthLimit)) {
      requestLayout()
    }
    return true
  }

  override fun setHeightLimit(heightLimit: Int): Boolean {
    if (mLayoutHelper.setHeightLimit(heightLimit)) {
      requestLayout()
    }
    return true
  }

  override fun setUseThemeGeneralShadowElevation() {
    mLayoutHelper.setUseThemeGeneralShadowElevation()
  }

  override fun setOutlineExcludePadding(outlineExcludePadding: Boolean) {
    mLayoutHelper.setOutlineExcludePadding(outlineExcludePadding)
  }

  override fun setShadowElevation(elevation: Int) {
    mLayoutHelper.shadowElevation = elevation
  }

  override fun getShadowElevation(): Int {
    return mLayoutHelper.shadowElevation
  }

  override fun setShadowAlpha(shadowAlpha: Float) {
    mLayoutHelper.shadowAlpha = shadowAlpha
  }

  override fun getShadowAlpha(): Float {
    return mLayoutHelper.shadowAlpha
  }

  override fun setShadowColor(shadowColor: Int) {
    mLayoutHelper.shadowColor = shadowColor
  }

  override fun getShadowColor(): Int {
    return mLayoutHelper.shadowColor
  }

  override fun setOuterNormalColor(color: Int) {
    mLayoutHelper.setOuterNormalColor(color)
  }

  override fun updateBottomSeparatorColor(color: Int) {
    mLayoutHelper.updateBottomSeparatorColor(color)
  }

  override fun updateLeftSeparatorColor(color: Int) {
    mLayoutHelper.updateLeftSeparatorColor(color)
  }

  override fun updateRightSeparatorColor(color: Int) {
    mLayoutHelper.updateRightSeparatorColor(color)
  }

  override fun updateTopSeparatorColor(color: Int) {
    mLayoutHelper.updateTopSeparatorColor(color)
  }

  override fun hasBorder(): Boolean {
    return mLayoutHelper.hasBorder()
  }

  override fun hasLeftSeparator(): Boolean {
    return mLayoutHelper.hasLeftSeparator()
  }

  override fun hasTopSeparator(): Boolean {
    return mLayoutHelper.hasTopSeparator()
  }

  override fun hasRightSeparator(): Boolean {
    return mLayoutHelper.hasRightSeparator()
  }

  override fun hasBottomSeparator(): Boolean {
    return mLayoutHelper.hasBottomSeparator()
  }

  fun requestLayout()
}