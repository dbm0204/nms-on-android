package com.example.ben.nodemanager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.widget.ImageView;

public class DrawableNetworkComponent
{
    public enum Type{
        ROUTER,
        USER,
        UNKNOWN
    }

    private DrawElement mDrawing;
    private Type mType;
    public DrawableNetworkComponent(Context context, Type type)
    {
        Point p1=new Point();
        p1.x=50;
        p1.y=20;

        this.mType = type;
        switch (mType)
        {
            case ROUTER:
                this.mDrawing = new DrawElement(context, R.drawable.ic_router, p1 );
                break;
            case USER:
                this.mDrawing = new DrawElement(context, R.drawable.ic_node, p1);
                break;
            default:
                this.mDrawing = new DrawElement(context, R.drawable.ic_launcher, p1);
                break;
        }
    }

    public void draw(Canvas canvas)
    {
        if (mDrawing != null)
            canvas.drawBitmap(mDrawing.getBitmap(),mDrawing.getx(),mDrawing.gety(),null);
    }

    public boolean isTouchingDrawing(int x, int y)
    {
        // check if inside the bounds of the ball (circle)
        // get the center for the ball
        int centerX = mDrawing.getx() + 25;
        int centerY = mDrawing.gety() + 25;

        // calculate the radius from the touch to the center of the ball
        double radCircle  = Math.sqrt( (double) (((centerX-x)*(centerX-x)) + (centerY-y)*(centerY-y)));

        // if the radius is smaller then 23 (radius of a ball is 22), then it must be on the ball
        return (radCircle < 23);
    }

    public int getId()
    {
        return mDrawing.getId();
    }

    public void setXY(int x, int y)
    {
        mDrawing.setx(x);
        mDrawing.sety(y);
    }
}
