package me.looorielovbb.boom.utils;

import android.content.Context;
import android.widget.Toast;

import me.looorielovbb.boom.app.BoomApp;


/**
 * ToastUtils
 *
 * @author looorie
 */
public class ToastUtils {
    private static Toast mToast = null;

    private ToastUtils() {
        throw new AssertionError();
    }

    public static void show(CharSequence text) {
        show(BoomApp.appCtx, text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, CharSequence text, int duration) {
        showToast(context, text, duration);
    }

    /**
     * 封装toast，再次不会依次显示，直接更换文字
     */
    private static void showToast(Context context, CharSequence text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        }
        else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }

        mToast.show();
    }

    public static void show(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration) {
        show(context, context.getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, Object... args) {
        show(context,
             String.format(context.getResources().getString(resId), args),
             Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), duration);
    }

    public static void show(Context context, String format, int duration, Object... args) {
        show(context, String.format(format, args), duration);
    }
}
