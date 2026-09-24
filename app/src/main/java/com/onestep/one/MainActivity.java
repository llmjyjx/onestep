package com.onestep.one;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.*;

public class MainActivity extends Activity {
    private int dp(float v) { return (int)(v * getResources().getDisplayMetrics().density + .5f); }

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(24), dp(40), dp(24), dp(24));
        root.setBackgroundColor(0xff121316);

        TextView title = new TextView(this);
        title.setText("OneStep");
        title.setTextColor(0xffffffff);
        title.setTextSize(30);
        title.setGravity(Gravity.CENTER);
        root.addView(title, new LinearLayout.LayoutParams(-1, dp(60)));

        TextView info = new TextView(this);
        info.setText("Android 16 / ColorOS 16\n\n普通 APK 版本的 OneStep。\n\n普通第三方 APK 无法把任意第三方 Activity 真正嵌入为 3 个独立实时窗口。");
        info.setTextColor(0xffdddddd);
        info.setTextSize(16);
        info.setGravity(Gravity.CENTER);
        root.addView(info, new LinearLayout.LayoutParams(-1, 0, 1));

        Button overlay = new Button(this);
        overlay.setText("开启悬浮窗权限");
        overlay.setOnClickListener(v -> {
            try { startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()))); }
            catch (Exception e) { startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)); }
        });
        root.addView(overlay, new LinearLayout.LayoutParams(-1, dp(52)));

        Button access = new Button(this);
        access.setText("打开辅助功能设置");
        access.setOnClickListener(v -> startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)));
        root.addView(access, new LinearLayout.LayoutParams(-1, dp(52)));

        Button demo = new Button(this);
        demo.setText("打开 OneStep 演示面板");
        demo.setOnClickListener(v -> startActivity(new Intent(this, OneStepDemoActivity.class)));
        root.addView(demo, new LinearLayout.LayoutParams(-1, dp(52)));

        setContentView(root);
    }
}
