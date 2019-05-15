package pandora.kingja.com.nestedscrolling;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        Log.e(TAG, "屏幕高度: " + height);
        Log.e(TAG, "屏幕宽度: " + width);

        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;

        Log.e(TAG, "比例: " + density1);
    }

    public static void main(String[] args) {
        int number[]={1};
        change(number);
        System.out.println("number[0]:"+number[0]);
    }

    private static void change(int[] number) {
        number[0]=8;
    }
}
