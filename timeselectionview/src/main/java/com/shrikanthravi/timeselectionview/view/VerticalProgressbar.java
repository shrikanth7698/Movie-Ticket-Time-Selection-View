package com.shrikanthravi.timeselectionview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

public class VerticalProgressbar extends ProgressBar {

    public int progress=0;
    public int max=100;

    public VerticalProgressbar(Context context) {
        super(context);
    }

    public VerticalProgressbar(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(-90);
        canvas.translate(-getHeight(), 0);
        super.onDraw(canvas);

    }
}
