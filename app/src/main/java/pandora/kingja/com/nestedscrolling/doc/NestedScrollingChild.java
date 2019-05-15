package pandora.kingja.com.nestedscrolling.doc;

/**
 * Description:TODO
 * Create Time:2019/5/13 0013 下午 4:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface NestedScrollingChild {
    /**
     * 设置是否支持嵌套滑动
     *
     * @param enabled
     */
    public void setNestedScrollingEnabled(boolean enabled);

    /**
     * 判断是否支持嵌套滑动
     *
     * @return
     */
    public boolean isNestedScrollingEnabled();

    /**
     * 开始嵌套滑动
     *
     * @param axes 滑动的方向 ViewCompat.SCROLL_AXIS_HORIZONTAL 代表水平滑动，ViewCompat.SCROLL_AXIS_VERTICAL 代表垂直滑动
     * @return
     */

    public boolean startNestedScroll(int axes);

    /**
     * 停止嵌套滑动
     */

    public void stopNestedScroll();

    /**
     * 是否有支持嵌套滑动的父类
     *
     * @return
     */

    public boolean hasNestedScrollingParent();

    /**
     * 子view处理scroll后调用
     *
     * @param dxConsumed     x轴上被消费的距离（横向）
     * @param dyConsumed     y轴上被消费的距离（竖向）
     * @param dxUnconsumed   x轴上未被消费的距离
     * @param dyUnconsumed   y轴上未被消费的距离
     * @param offsetInWindow 子View的窗体偏移量
     * @return true if the event was dispatched, false if it could not be dispatched.
     */
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed,
                                        int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow);

    /**
     * 在子View的onInterceptTouchEvent或者onTouch中，调用该方法通知父View滑动的距离
     *
     * @param dx             x轴上滑动的距离
     * @param dy             y轴上滑动的距离
     * @param consumed       父view消费掉的scroll长度
     * @param offsetInWindow 子View的窗体偏移量
     * @return 支持的嵌套的父View 是否处理了 滑动事件
     */
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow);

    /**
     * 滑行时调用
     *
     * @param velocityX x 轴上的滑动速率
     * @param velocityY y 轴上的滑动速率
     * @param consumed  是否被消费
     * @return true if the nested scrolling parent consumed or otherwise reacted to the fling
     */

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed);

    /**
     * 进行滑行前调用
     *
     * @param velocityX x 轴上的滑动速率
     * @param velocityY y 轴上的滑动速率
     * @return true if a nested scrolling parent consumed the fling
     */
    public boolean dispatchNestedPreFling(float velocityX, float velocityY);
}
