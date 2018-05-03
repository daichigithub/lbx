package com.dc.lbx;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/5/2 0002.
 * 袁超的六边形设计思路
 * 六边形作为背景放置,每个六边形点击之后通过重置背景图片达到改变背景颜色的效果
 * 六边形的位置信息根据自定义属性index获取，最内圈index=0,外圈index从1到6
 */

public class HexagonViewYC extends android.support.v7.widget.AppCompatTextView  implements OnClickListener {
    public HexagonViewYC(Context context) {
        this(context, null);
    }

    public HexagonViewYC(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HexagonViewYC(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        TypedArray ta = getContext().obtainStyledAttributes(R.styleable.HexagonViewYC);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.HexagonViewYC);
        index = ta.getInteger(R.styleable.HexagonViewYC_index, 0);
        setIndex(index);
        init();
    }

    Paint mPaint;//绘制六边形内标题的画笔
    double x;//中心点x坐标值
    double y;//中心点y坐标值

    int index;//自定义属性
    String text;//title
    String drawText;
    int viewWidth;//控件宽
    int viewHeight;//控件高

    public void init(){
        text = getText().toString();
        drawText = text;
        setText("");

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setTextSize(getTextSize());

        setOnClickListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        viewWidth = right - left;
        viewHeight = bottom - top;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        setTextLocation();

        //画标题
        float textLen = mPaint.measureText(drawText);
        float x7 = (float) textX - textLen/2;
        float y7 = (float) (textY + viewHeight/4);

        canvas.drawText(drawText, x7, y7, mPaint);
    }

    int textX;
    int textY;
    private void setTextLocation() {
        textX = viewWidth/2;
        textY = viewHeight/2;
    }

    MySelectListener mySelectListener;

    public void setMySelectListener(MySelectListener mySelectListener) {
        this.mySelectListener = mySelectListener;
    }

    @Override
    public void onClick(View v) {
        HexagonViewYC view = (HexagonViewYC) v;
        drawBackground(view);

        if(mySelectListener != null){
            int index = view.getIndex();
            mySelectListener.onSelected(view, index);
        }
    }

    private void drawBackground(HexagonViewYC view){
        ViewParent p = view.getParent();
        if (p != null && p instanceof HexagonLayout){
            for(int i = 0; i < ((HexagonLayout) p).getChildCount(); i ++){
                HexagonViewYC child = (HexagonViewYC) ((HexagonLayout) p).getChildAt(i);
                if (child == view){
                    ((HexagonViewYC)view).setBackgroundResource(R.drawable.bg_pressed);
                    Log.e("eee","----i==="+ i + "-----");
                } else {
                    ((HexagonViewYC)child).setBackgroundResource(R.drawable.bg_normal);
                }
            }
        }
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public interface MySelectListener{
        void onSelected(HexagonViewYC view, int index);
    }
}
