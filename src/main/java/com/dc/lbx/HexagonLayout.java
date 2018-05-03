package com.dc.lbx;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class HexagonLayout extends ViewGroup {
    public HexagonLayout(Context context) {
        this(context, null);
    }

    public HexagonLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HexagonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int mCount = 5;
    int centerX;
    int centerY;

    int childRadius ;
    int childWidth;
    int childHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);

        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        setMeasuredDimension(wSize, hSize);

        centerX = wSize / 2;
        centerY = hSize / 2;
        childRadius = wSize / mCount;
        computeCenterData(centerX, centerY, childRadius);

        childWidth = 2* childRadius;
        double a = Math.PI / 6;
        childHeight = (int) (2* childRadius * Math.cos(a));

        int childCount = getChildCount();
        for(int i = 0 ; i < childCount; i ++){
            View view = getChildAt(i);
            view.measure(childWidth, childHeight);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void computeCenterData(int x, int y, int radius) {
        double a = Math.PI / 6;
        double x1 = x ;
        double y1 = y - 2* radius * Math.cos(a);

        double x2 = x + radius + radius * Math.sin(a);
        double y2 = y - radius * Math.cos(a);

        double x3 = x2;
        double y3 = y + radius * Math.cos(a);

        double x4 = x;
        double y4= y + 2* radius* Math.cos(a);

        double x5 = x - radius - radius * Math.sin(a);
        double y5 = y + radius * Math.cos(a);

        double x6 = x5;
        double y6 = y - radius * Math.cos(a);
        centers.clear();
        centers.add(0, new CenterPoint(x, y));
        centers.add(1, new CenterPoint(x1, y1));
        centers.add(2, new CenterPoint(x2, y2));
        centers.add(3, new CenterPoint(x3, y3));
        centers.add(4, new CenterPoint(x4, y4));
        centers.add(5, new CenterPoint(x5, y5));
        centers.add(6, new CenterPoint(x6, y6));


    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for(int i = 0 ; i < childCount; i ++){
            View view = getChildAt(i);
            int left = (int) (centers.get(i).x - childRadius);
            int top = (int) (centers.get(i).y - childHeight/2);
            view.layout(left, top, left + childWidth, top + childHeight);
        }
    }

    ArrayList<CenterPoint> centers = new ArrayList<CenterPoint>();
    class CenterPoint{
        double x;
        double y;
        public CenterPoint(double x, double y){
            this.x = x;
            this.y = y;
        }
    }


}
