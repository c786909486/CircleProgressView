package com.ckz.circleprogress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/3/2.
 */

public class CircleProgressView extends View {

    private Paint bgPaint;//底层圆弧画笔
    private Paint progressPaint;//进度圆弧画笔
    private Paint textPaint;//文字画笔
    private int circleSize = 15;//圆弧大小
    private float textSize = 18;//字体大小
    private int bgCircleColor = Color.parseColor("#bbbbbb");//圆弧背景色
    private int circleColor = Color.WHITE;//进度圆弧颜色
    private int textColor = Color.WHITE;//字体颜色
    private int textWidth;
    private int textHeight;
    private boolean needPersent = true;//是否需要百分号

    private float progress = 0;//进度
    private float startAngle = -225f;//开始角度
    private float drawAngle = 270f;//绘制角度

    public CircleProgressView(Context context) {
        this(context,null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint(){
        bgPaint = new Paint();
        bgPaint.setColor(bgCircleColor);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(circleSize);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);

        progressPaint = new Paint();
        progressPaint.setColor(circleColor);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(circleSize);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);

        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPaint();

        float x = (getWidth() - getHeight()*2 / 3) / 2;
        float y = getHeight() / 6;
        RectF oval = new RectF( x, y,
                getWidth() - x, getHeight() - y);
        //绘制底层圆环
        canvas.drawArc(oval, startAngle, drawAngle,false,bgPaint);
        //绘制进度圆环
        canvas.drawArc(oval, startAngle,(float)(progress/100*drawAngle),false,progressPaint);
        //绘制文字
        Rect rect = new Rect();
        String text;
        if (needPersent){
            text = progress+"%";
        }else {
            text = String.valueOf(progress);
        }
        textPaint.getTextBounds(text, 0, text.length(), rect);
        textWidth = rect.width();//文本的宽度
        textHeight = rect.height();//文本的高度
        canvas.drawText(text,(getWidth()-textWidth)/2,(getHeight()+textHeight)/2,textPaint);
    }

    public void setProgress(float progress){
        this.progress = progress;
        invalidate();
    }

}
