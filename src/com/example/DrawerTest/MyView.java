package com.example.DrawerTest;

import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Egor Suvorov on 04.04.2015.
 */
public class MyView extends View implements View.OnTouchListener {
    ArrayList<PointF> points;

    public MyView(Context context) {
        super(context);
        points = new ArrayList<PointF>();
        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        if (!points.isEmpty()) {
            Path path = new Path();
            path.moveTo(points.get(0).x, points.get(0).y);
            for (PointF p : points) {
                path.lineTo(p.x, p.y);
            }
            canvas.drawPath(path, paint);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
            points.add(new PointF(event.getX(), event.getY()));
            invalidate();
            return true;
        }
        return false;
    }
}
