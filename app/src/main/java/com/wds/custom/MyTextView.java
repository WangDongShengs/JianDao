package com.wds.custom;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 跑马灯效果的TextView, 使用方式：
 * 启动/关闭：{@link #//setMarqueeEnable(boolean)}
 * xml文件中记得设置：android:focusable="true", android:singleLine="true"
 *
 * Created by dasu on 2017/3/21.
 * http://www.jianshu.com/u/bb52a2918096
 */
public class MyTextView extends android.support.v7.widget.AppCompatTextView {
    public MyTextView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs,int
            defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

}
