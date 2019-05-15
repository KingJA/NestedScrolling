package pandora.kingja.com.nestedscrolling.doc;

import android.view.View;

/**
 * Description:TODO
 * Create Time:2019/5/13 0013 下午 4:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface NestedScrollingParent {
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes);

    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes);

    public void onStopNestedScroll(View target);

    public void onNestedScroll(View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed);

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed);

    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed);

    public boolean onNestedPreFling(View target, float velocityX, float velocityY);

    public int getNestedScrollAxes();
}
