package com.sungrowpower.kit.util;

import androidx.annotation.Nullable;
import java.io.Closeable;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

/**
 * @author zhaoyunhai
 */
public class SGLangHelper {

  /**
   * 获取数值的位数，例如9返回1，99返回2，999返回3
   *
   * @param number 要计算位数的数值，必须>0
   * @return 数值的位数，若传的参数小于等于0，则返回0
   */
  public static int getNumberDigits(int number) {
    if (number <= 0) return 0;
    return (int) (Math.log10(number) + 1);
  }

  public static int getNumberDigits(long number) {
    if (number <= 0) return 0;
    return (int) (Math.log10(number) + 1);
  }

  public static String formatNumberToLimitedDigits(int number, int maxDigits) {
    if (getNumberDigits(number) > maxDigits) {
      StringBuilder result = new StringBuilder();
      for (int digit = 1; digit <= maxDigits; digit++) {
        result.append("9");
      }
      result.append("+");
      return result.toString();
    } else {
      return String.valueOf(number);
    }
  }

  /**
   * 规范化价格字符串显示的工具类
   *
   * @param price 价格
   * @return 保留两位小数的价格字符串
   */
  public static String regularizePrice(float price) {
    return String.format(Locale.CHINESE, "%.2f", price);
  }

  /**
   * 规范化价格字符串显示的工具类
   *
   * @param price 价格
   * @return 保留两位小数的价格字符串
   */
  public static String regularizePrice(double price) {
    return String.format(Locale.CHINESE, "%.2f", price);
  }

  public static boolean isNullOrEmpty(@Nullable CharSequence string) {
    return string == null || string.length() == 0;
  }

  public static void close(Closeable c) {
    if (c != null) {
      try {
        c.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Deprecated
  public static boolean objectEquals(Object a, Object b) {
    return Objects.equals(a, b);
  }

  public static int constrain(int amount, int low, int high) {
    return amount < low ? low : (amount > high ? high : amount);
  }

  public static float constrain(float amount, float low, float high) {
    return amount < low ? low : (amount > high ? high : amount);
  }
}
