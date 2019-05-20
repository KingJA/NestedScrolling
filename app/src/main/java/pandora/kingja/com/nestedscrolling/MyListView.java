package pandora.kingja.com.nestedscrolling;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Description:TODO
 * Create Time:2019/5/20 0020 上午 9:02
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}