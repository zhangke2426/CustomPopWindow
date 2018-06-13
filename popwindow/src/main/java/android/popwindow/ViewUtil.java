package android.popwindow;


import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;



/**
 *
 * 取名ViewUtil而非ViewUtils是为了区分android.support.v7.widget.ViewUtils
 */
public class ViewUtil {

    private static final String TAG = ViewUtil.class.getSimpleName();
    private static int screenWidth = -1;
    private static int screenHeight = -1;
    private static int windowWidth = -1;
    private static int windowHeight = -1;
    private static int statusBarHeight = -1;
    private static float density;
    private static int densityDpi;
    public static void init(Activity activity) {
        if (screenWidth != -1) {
            Log.d(TAG, "already inited");
            return;
        }
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        density = dm.density;
        densityDpi = dm.densityDpi;
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        View content = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        windowWidth = content.getWidth();
        windowHeight = content.getHeight();
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
        } else {
            statusBarHeight = 0;
        }
    }

    public static int getScreenWidth() {
        if(screenWidth == -1){
            throw new IllegalStateException("You can't invoke this before init() invoked!");
        }
        return screenWidth;
    }
    public static int dip2px(float dipValue) {
        final float scale = density;
        return (int) (dipValue * scale + 0.5f);
    }
    public static int getScreenHeight() {
        if(screenHeight == -1){
            throw new IllegalStateException("You can't invoke this before init() invoked!");
        }
        return screenHeight;
    }

    public static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
         // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenHeight = getScreenHeight();
        final int screenWidth = getScreenWidth();
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
    }


}

