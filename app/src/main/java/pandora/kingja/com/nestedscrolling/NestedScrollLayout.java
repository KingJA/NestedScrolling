package pandora.kingja.com.nestedscrolling;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

/**
 * Description:TODO
 * Create Time:2019/5/14 0014 上午 11:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NestedScrollLayout extends LinearLayout implements NestedScrollingParent {
    private static final String TAG = "NestedScrollLayout";
    private NestedScrollingParentHelper mNestedScrollingParentHelper;
    private View tv_des;
    private View tv_title;
    private View tv_content;

    public NestedScrollLayout(Context context) {
        this(context, null);
    }

    public NestedScrollLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedScrollLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
    }

    private int desHeight;
    private int titleHeight;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tv_des = findViewById(R.id.tv_des);
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
        tv_des.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (desHeight <= 0) {
                    desHeight = tv_des.getMeasuredHeight();
                    Log.e(TAG, "测量 desHeight: " + desHeight);
                }
            }
        });
        tv_title.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (titleHeight <= 0) {
                    titleHeight = tv_title.getMeasuredHeight();
                    Log.e(TAG, "测量 titleHeight: " + titleHeight);
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,  heightMeasureSpec);
        //不限制顶部的高度
//        tv_content.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
//        Log.e(TAG, "测量 内容高度: "+tv_content.getMeasuredHeight());
//        setMeasuredDimension(getMeasuredWidth(), tv_des.getMeasuredHeight() + tv_title.getMeasuredHeight() +
// tv_content
//                .getMeasuredHeight());
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Log.e(TAG, "测量 总高度: " + getMeasuredHeight());
    }

    /**
     * 返回 true才能执行onNestedScrollAccepted
     *
     * @param child
     * @param target           同子View
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.e(TAG, "onStartNestedScroll: ");
        //只监听NestedScrollTextView的垂直滑动
        if (target instanceof NestedScrollChildView && nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL) {
            return true;
        }
        return false;
    }


    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.e(TAG, "onNestedPreFling: ");
        return false;
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.e(TAG, "onNestedFling: ");
        return false;
    }


    /*============以上三个比较重要============*/

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        Log.e(TAG, "onNestedScrollAccepted: ");
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
    }

    /**
     * 滑动结束后自动调用
     *
     * @param child
     */
    @Override
    public void onStopNestedScroll(View child) {
        Log.e(TAG, "onStopNestedScroll: ");
        mNestedScrollingParentHelper.onStopNestedScroll(child);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.e(TAG, "onNestedScroll: ");
//        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }


    @Override
    public int getNestedScrollAxes() {
        Log.e(TAG, "getNestedScrollAxes: ");
        return mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    /**
     * 滑动时候调用，先于子View
     *
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if (showDes(dy) || hideDes(dy)) {//如果需要显示或隐藏图片，即需要自己(parent)滚动
            scrollBy(0, -dy);//滚动
            consumed[1] = dy;//告诉child我消费了多少
            Log.e(TAG, "父类滑动onNestedPreScroll dy: " + dy);
        }
    }

    //下拉的时候是否要向下滚动以显示图片
    public boolean showDes(int dy) {
        if (dy > 0) {
            if (getScrollY() > 0 && tv_content.getScrollY() == 0) {
                return true;
            }
        }
        return false;
    }

    //上拉的时候，是否要向上滚动，隐藏图片
    public boolean hideDes(int dy) {
        if (dy < 0) {
            if (getScrollY() < desHeight) {
                return true;
            }
        }
        return false;
    }

    //scrollBy内部会调用scrollTo
    //限制滚动范围
    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > desHeight) {
            y = desHeight;
        }

        super.scrollTo(x, y);
    }
}
