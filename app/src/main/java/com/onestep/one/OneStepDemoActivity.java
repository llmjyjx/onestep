package com.onestep.one;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.util.*;

public class OneStepDemoActivity extends Activity {
    private LinearLayout secondary;
    private TextView main;
    private final List<String> slots = new ArrayList<>(Arrays.asList("应用 1","应用 2","应用 3"));

    private int dp(float v) { return (int)(v * getResources().getDisplayMetrics().density + .5f); }

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(10), dp(10), dp(10), dp(10));
        root.setBackgroundColor(0xff0a0b0e);

        main = card("主窗口", 22);
        root.addView(main, new LinearLayout.LayoutParams(-1, 0, .60f));

        secondary = new LinearLayout(this);
        secondary.setOrientation(LinearLayout.HORIZONTAL);
        root.addView(secondary, new LinearLayout.LayoutParams(-1, 0, .40f));
        rebuild();

        setContentView(root);
    }

    private TextView card(String text, float size) {
        TextView v = new TextView(this);
        v.setText(text);
        v.setTextColor(Color.WHITE);
        v.setTextSize(size);
        v.setGravity(Gravity.CENTER);
        v.setBackgroundColor(0xff23252b);
        return v;
    }

    private void rebuild() {
        secondary.removeAllViews();
        for (int i=0; i<slots.size(); i++) {
            final int index=i;
            TextView v=card(slots.get(i),13);
            v.setOnClickListener(x -> {
                String old=main.getText().toString();
                main.setText(slots.get(index));
                slots.set(index, old);
                rebuild();
            });
            v.setOnTouchListener(new View.OnTouchListener() {
                float down;
                public boolean onTouch(View v, MotionEvent e) {
                    if(e.getAction()==MotionEvent.ACTION_DOWN){down=e.getRawX(); return true;}
                    if(e.getAction()==MotionEvent.ACTION_UP){
                        if(Math.abs(e.getRawX()-down)>dp(90)){
                            slots.set(index,"");
                            rebuild();
                        } else v.performClick();
                        return true;
                    }
                    return true;
                }
            });
            if(slots.get(i).isEmpty()){
                v.setText("");
                v.setBackgroundColor(Color.TRANSPARENT);
                v.setOnTouchListener(null);
            }
            // 无 margin / 无 padding，三个应用窗口完全相邻。
            secondary.addView(v, new LinearLayout.LayoutParams(0,-1,1));
        }
    }
}
