package com.example.DrawerTest;

import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Egor Suvorov on 04.04.2015.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable, View.OnTouchListener {
    public MySurfaceView(Context context) {
        super(context);
        points = new ArrayList<PointF>();
        setOnTouchListener(this);
        getHolder().addCallback(this);
    }

    private SurfaceHolder holder;
    private Thread thread;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
        thread = new Thread(this);
        thread.start();
        System.out.println("start");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.holder = null;
        System.out.println("end");
    }

    @Override
    public void run() {
        while (true) {
            Canvas canvas = holder.lockCanvas();
            if (canvas == null) {
                break;
            }
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
            holder.unlockCanvasAndPost(canvas);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    ArrayList<PointF> points;
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
