package com.example.ben.nodemanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

public class DrawElement
{
        private Bitmap img;
        private int coordx=0;
        private int coordy=0;
        private int id;
        private static int count=1;
        private boolean goRight=true;
        private boolean goDown=true;

        public DrawElement(Context context,int drawable)
        {
            BitmapFactory.Options opts =new BitmapFactory.Options();
            opts.inJustDecodeBounds=true;
            img=BitmapFactory.decodeResource(context.getResources(),drawable);
            id=count;
            count++;
        }
        public DrawElement(Context context,int drawable,Point point)
        {
            BitmapFactory.Options opts=new BitmapFactory.Options();
            opts.inJustDecodeBounds=true;
            img=BitmapFactory.decodeResource(context.getResources(),drawable);
            id=count;
                    count++;
                    coordx=point.x;
                    coordy=point.y;
        }
        public static int getCount()
        {
            return count;
        }
        public void setx(int newValue)
        {
            coordx=newValue;
        }
        public void sety(int newValue)
        {
            coordy=newValue;
        }
        public int getx()
        {
            return coordx;
        }

        public int gety()
        {
            return coordy;
        }
        public  int getId()
        {
            return id;
        }
        public Bitmap getBitmap()
        {
            return img;
        }
        public void moveball(int goX,int goY)
        {
            if (coordx>270)
            {
                goRight=false;

            }
            if (coordx < 0)
            {
                goRight = true;
            }
            if (coordy > 400)
            {
                goDown = false;
            }
            if (coordy < 0)
            {
                goDown = true;
            }
            // move the x and y
            if (goRight)
            {
                coordx += goX;
            }
            else
            {
                coordx -= goX;
            }
            if (goDown)
            {
                coordy += goY;
            }
            else
            {
                coordy -= goY;
            }
    }

}
