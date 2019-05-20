package pandora.kingja.com.nestedscrolling;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2019/5/20 0020 上午 9:03
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CoolActivity extends AppCompatActivity {
    private MyListView lv;
    private List<String> list;


//    实现步骤：
//    将需要悬浮的layout放到CollapsingToolbarLayout之外，AppBarLayout之内
//    将CollapsingToolbarLayout的app:layout_scrollFlags设置为scroll
//            给滚动的NestedScroolView设置
//    app:layout_behavior="@String/appbar_scrolling_view_behavior"
//    就大功告成了(记得根布局要是CoordinatorLayout)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cool);
        lv = (MyListView) findViewById(R.id.lv);
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
    }
}
