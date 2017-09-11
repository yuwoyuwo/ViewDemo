package com.example.lenovo.viewdemo;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by lenovo on 2017/9/11.
 */

public class RectView extends View {
    private Paint mPaint;

    //设置条形图的 间距和宽度
    private int rectSpace = 50;
    private int rectWith = 50;
    //必须是所有的柱状图高度的最大公约数以下的约数 否则余数高度无法画出
    private int times = 50;//设置走多少次
    private int current = 0;//当前走的次数

    //数据   条状态图的高度  颜色
    private int[][]rectArray ={{500,Color.YELLOW},{200,Color.RED},{350,Color.BLACK},{250,Color.GREEN}};

    public RectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化画笔对象
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(3);
    }

    public RectView(Context context) {
        this(context,null);
    }


    /**
     * 绘制  条形统计图
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画坐标点
        canvas.drawCircle(50,getHeight()-50,2,mPaint);

        //画x轴
        canvas.drawLine(50,getHeight()-50,getWidth()-50,getHeight()-50,mPaint);

        //画Y轴
        canvas.drawLine(50,getHeight()-50,50,50,mPaint);

        //画Y轴箭头
        canvas.drawLine(50,50,25,75,mPaint);
        canvas.drawLine(50,50,75,75,mPaint);

        //绘制条形图   n 条
        for(int i=0;i<rectArray.length;i++){

            //条形图 左边
            int left = rectSpace +(rectSpace+rectWith)*i;
            //获取顶部位置
            int top = rectArray[i][0]/times*current;
            //重新设置颜色
            mPaint.setColor(rectArray[i][1]);
            //绘制条形图
            canvas.drawRect(left+50,getHeight()-top-50,left+50+rectWith,getHeight()-50,mPaint);


        }
//        效果让其柱状图慢慢上升
       current++;
        if(current<times){
            invalidate();//刷新
        }


    }
}
