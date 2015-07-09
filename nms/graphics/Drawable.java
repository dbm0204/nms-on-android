package com.example.ben.nodemanager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import  android.widget.*;

public class Drawable
{
    private Bitmap mImg;
    private Rect mPosition;
    private ImageView imgView;

    public Drawable()

    {
        mPosition=new Rect(0,0,0,0);
    }
    public Drawable(int xPos,int yPos)
    {
        mPosition=new Rect(xPos,yPos,xPos,yPos);
    }
    public void reposition(int newX,int newY)
    {
        mPosition.offsetTo(newX,newY);
    }
    public boolean isTouching(int x,int y)
    {
        return mPosition.contains(x,y);
    }
    public void loadImage(Context context,int drawable)
    {
        mImg= BitmapFactory.decodeResource(context.getResources(),drawable);
        mPosition.set(mPosition.left,mPosition.top,mPosition.left + mImg.getWidth(),mPosition.top+mImg.getHeight());


    }
    public void draw(Canvas canvas)
    {
        if(mImg==null)
        {
            return;
        }
        else
            //canvas.drawBitmap(mImg,null,mPosition,null);
            canvas.drawRect(mPosition, new Paint());
    }

}

