package com.example.ben.nodemanager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class DrawLink extends Activity
{
   // @Override
    public void OnCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
    public class MyView extends View
    {

        class mPoint
        {
            float x;
            float y;
            public mPoint(){}
            public  mPoint(float _x,float _y)
            {
                x=_x;
                y=_y;
            }

        }
        mPoint [] mPath;
        public MyView(Context context)
        {
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas)
        {
          super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            paint.setStrokeWidth(4);
            Path path =new Path();

            path.moveTo(mPath[0].x,mPath[0].y);
            for(int i=0;i<mPath.length;i++)
            {
                path.lineTo(mPath[i].x,mPath[i].y);

            }
            canvas.drawPath(path,paint);

        }
    }
}
