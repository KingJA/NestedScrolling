package pandora.kingja.com.nestedscrolling;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Description:TODO
 * Create Time:2019/5/14 0014 上午 11:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NestedScrollChildView extends TextView implements NestedScrollingChild {
    private static final String TAG = "NestedScrollTextView";
    private NestedScrollingChildHelper nestedScrollingChildHelper;
    private int showHeight;
    private final int[] offset = new int[2]; //偏移量
    private final int[] consumed = new int[2]; //消费
    private int lastY;

    public NestedScrollChildView(Context context) {
        super(context);
    }

    public NestedScrollChildView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollChildView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //第一次测量，因为布局文件中高度是wrap_content，因此测量模式为atmost，即高度不超过父控件的剩余空间
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        showHeight = getMeasuredHeight();
        Log.e(TAG, "第一次测量 限制高度: " + showHeight);

        //第二次测量，对稿哦度没有任何限制，那么测量出来的就是完全展示内容所需要的高度
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "第二次测量 无限制高度: " + getMeasuredHeight());
        Log.e(TAG, "测量 maxY: " + (getMeasuredHeight() - showHeight));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            //按下
            case MotionEvent.ACTION_DOWN:
                lastY = (int) event.getRawY();
                //第一步：开始启动嵌套滑动
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
                break;
            //移动
            case MotionEvent.ACTION_MOVE:
                int y = (int) (event.getRawY());
                int dy = y - lastY;
                lastY = y;
                //调用开始嵌套循环
                //第二步：如果有关联父类，则想父类分发父类
                if (dispatchNestedPreScroll(0, dy, consumed, offset)) {
//                    int remain = dy - consumed[1];
//                    if (remain != 0) {
//                        scrollBy(0, -remain);
//                    }
                    Log.e(TAG, "垂直滑动: ");

                }else{
                    Log.e(TAG, "子View滑动: ");
                    scrollBy(0, -dy);
                }
                break;
        }

        return true;
    }



    //限制滚动范围
    @Override
    public void scrollTo(int x, int y) {
        Log.e(TAG, "真实移动x: "+x+" y:"+ y);
        int maxY = getMeasuredHeight() - showHeight;
        Log.e(TAG, "maxY: "+maxY);

        if (y > maxY) {
            y = maxY;
        }
        if (y < 0) {
            y = 0;
        }

        super.scrollTo(x, y);
    }

    public NestedScrollingChildHelper getNestedScrollingChildHelper() {
        if (nestedScrollingChildHelper == null) {
            nestedScrollingChildHelper = new NestedScrollingChildHelper(this);
            nestedScrollingChildHelper.setNestedScrollingEnabled(true);
        }
        return nestedScrollingChildHelper;
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        getNestedScrollingChildHelper().setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return getNestedScrollingChildHelper().isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return getNestedScrollingChildHelper().startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        getNestedScrollingChildHelper().stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return getNestedScrollingChildHelper().hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable
            int[] offsetInWindow) {
        return getNestedScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed,
                dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {
        return getNestedScrollingChildHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return getNestedScrollingChildHelper().dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return getNestedScrollingChildHelper().dispatchNestedPreFling(velocityX, velocityY);
    }
}
