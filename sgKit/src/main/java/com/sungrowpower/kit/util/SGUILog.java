package com.sungrowpower.kit.util;

/**
 * @author cginechen
 * @date 2016-08-11
 */
public class SGUILog {
  public interface SGKitLogDelegate {
    void e(final String tag, final String msg, final Object... obj);

    void w(final String tag, final String msg, final Object... obj);

    void i(final String tag, final String msg, final Object... obj);

    void d(final String tag, final String msg, final Object... obj);

    void printErrStackTrace(String tag, Throwable tr, final String format, final Object... obj);
  }

  private static SGKitLogDelegate sDelegete = null;

  public static void setDelegete(SGKitLogDelegate delegete) {
    sDelegete = delegete;
  }

  public static void e(final String tag, final String msg, final Object... obj) {
    if (sDelegete != null) {
      sDelegete.e(tag, msg, obj);
    }
  }

  public static void w(final String tag, final String msg, final Object... obj) {
    if (sDelegete != null) {
      sDelegete.w(tag, msg, obj);
    }
  }

  public static void i(final String tag, final String msg, final Object... obj) {
    if (sDelegete != null) {
      sDelegete.i(tag, msg, obj);
    }
  }

  public static void d(final String tag, final String msg, final Object... obj) {
    if (sDelegete != null) {
      sDelegete.d(tag, msg, obj);
    }
  }

  public static void printErrStackTrace(String tag, Throwable tr, final String format, final Object... obj) {
    if (sDelegete != null) {
      sDelegete.printErrStackTrace(tag, tr, format, obj);
    }
  }
}
