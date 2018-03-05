package com.ckz.circleprogress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.text.DecimalFormat;


public class CircleProgressView extends View {

    private Paint bgPaint;//底层圆弧画笔
    private Paint progressPaint;//进度圆弧画笔
    private Paint textPaint;//文字画笔
    private int circleSize = 15;//圆弧大小
    private int textSize = 64;//字体大小
    private int bgCircleColor = Color.parseColor("#bbbbbb");//圆弧背景色
    private int progressColor = Color.WHITE;//进度圆弧颜色
    private int textColor = Color.WHITE;//字体颜色
    private int textWidth;
    private int textHeight;
    private boolean needPersent = true;//是否需要百分号
    private float progress = 0;//进度
    private float startAngle = -225f;//开始角度
    private float drawAngle = 270f;//绘制角度

    private boolean needAni = false;

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
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(bgCircleColor);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(circleSize);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);

        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setColor(progressColor);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(circleSize);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
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
        DecimalFormat df = new DecimalFormat("0.0");
        if (needPersent){
            text = df.format(progress)+"%";
        }else {
            text = String.valueOf(df.format(progress));
        }
        textPaint.getTextBounds(text, 0, text.length(), rect);
        textWidth = rect.width();//文本的宽度
        textHeight = rect.height();//文本的高度
        canvas.drawText(text,(getWidth()-textWidth)/2,(getHeight()+textHeight)/2,textPaint);
    }

    /**
     * 是否允许动画
     * @param enableAni
     */
    public void setEnableAni(boolean enableAni){
        this.needAni = enableAni;
    }

    /**
     * 设置进度
     * @param progress
     */
    public void setProgress(float progress){
        if (!needAni){
            this.progress = progress;
            invalidate();
        }else {
            startAni(progress);
        }
    }

    /**
     * 设置圆弧宽度
     * @param circleWidth
     */
    public void setCircleWidth(int circleWidth){
        this.circleSize = circleWidth;
        invalidate();
    }

    /**
     * 设置字体大小
     * @param textSize
     */
    public void setTextSize(int textSize){
        this.textSize = textSize;
        invalidate();
    }

    /**
     * 设置进度条颜色
     * @param color
     */
    public void setProgressColor(int color){
        this.progressColor = color;
        invalidate();
    }

    /**
     * 设置进度条背景色
     * @param color
     */
    public void setProgressBgColor(int color){
        this.bgCircleColor = color;
        invalidate();
    }

    /**
     * 设置字体颜色
     * @param color
     */
    public void setTextColor(int color){
        this.textColor = color;
        invalidate();
    }



    private void startAni(float persent){
        ValueAnimator animator = ValueAnimator.ofFloat(0,persent);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(1000);
        animator.start();
    }

}
