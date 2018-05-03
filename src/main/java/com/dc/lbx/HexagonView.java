package com.dc.lbx;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2018/5/2 0002.
 * 自定义控件：六边形
 * onDraw方法中画六边形、画bitmap和text
 */

public class HexagonView extends View{
    Context mContext;
    public HexagonView(Context context) {
        this(context, null);
    }

    public HexagonView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HexagonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    double centerX;//中心点x坐标
    double centerY;//中心点y坐标
    double radius;//半径
    int mDeviceWidth;
    int mDeviceHeight;
    String text = "我是标题";


    Paint mPaint;
    double a;

    void init(){
        mPaint = new Paint();
        mPaint.setColor(0x8834556C);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setTextSize(28);

        mDeviceWidth = Utils.getDeviceWidth(mContext);
        mDeviceHeight = Utils.getDeviceHeight(mContext);

        centerX = mDeviceWidth/2;
        centerY = mDeviceWidth/2;
        radius = mDeviceWidth/6;

        a = Math.PI / 6;

    }

    public void setData(double x, double y, double radius){
        this.centerX = x;
        this.centerY = y;
        this.radius = radius;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x1 = (float) (centerX - radius);
        float y1 = (float) centerY;
        float x2 = (float) (centerX - radius * Math.sin(a));
        float y2 = (float) (centerY - radius * Math.cos(a));
        float x3 = (float) (centerX + radius * Math.sin(a));
        float y3 = y2;
        float x4 = (float) (centerX + radius);
        float y4 = (float) centerY;
        float x5 = x3;
        float y5 = (float) (centerY + radius * Math.cos(a));
        float x6 = x2;
        float y6 = y5;

        float [] pts = {x1, y1, x2, y2,
                     x2, y2, x3, y3,
                     x3, y3, x4, y4,
                     x4, y4, x5, y5,
                     x5, y5, x6, y6,
                     x6, y6, x1, y1};

        //画六边形
        canvas.drawLines(pts, mPaint);

        //画标题
        float textLen = mPaint.measureText(text);
        float x7 = (float) centerX - textLen/2;
        float y7 = (float) (centerY + radius * Math.cos(a)/2);

        canvas.drawText(text, x7, y7, mPaint);

        //画图标
        float x8 = (float) centerX;
        float y8 = (float) (centerY- radius * Math.cos(a)/2* (4/3));

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Rect rect = new Rect();

        rect.left = (int) (x8-bitmap.getWidth()/2);
        rect.top = (int) (y8-radius/4);
//        rect.right = (int) (x8 + radius/5);
//        rect.bottom = (int) (y8 + radius);
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setDither(true);

        canvas.drawBitmap(bitmap, rect.left, rect.top, p);


    }
}
