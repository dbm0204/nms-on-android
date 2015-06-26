package com.example.ben.nodemanager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.MotionEvent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class WorkSpace extends View {


    private Vector<DrawableNetworkComponent> mComponents;
    private void init(Context context)
    {
        setFocusable(true);
        mComponents = new Vector<DrawableNetworkComponent>();
    }

    public WorkSpace(Context context){
        super(context);
        init(context);
    }

    public WorkSpace(Context context, AttributeSet attrs )
    {
        super(context, attrs);
        init(context);
    }

    public WorkSpace(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        for( DrawableNetworkComponent entry  : mComponents){
            entry.draw(canvas);
        }
    }

    private int mSelectedComponent = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event){
        
        int eventaction=event.getAction();

        int x=(int)event.getX();
        int y=(int)event.getY();

        switch (eventaction){
            case MotionEvent.ACTION_DOWN:
                int location =0;
                for ( DrawableNetworkComponent entry : mComponents)
                {
                    boolean touchingElemnt = entry.isTouchingDrawing(x, y);
                    if (touchingElemnt) {
                        mSelectedComponent = location;
                        break;
                    }
                    ++location;
                }
                break;

            case MotionEvent.ACTION_MOVE:   // touch drag with the ball
                // move the balls the same as the finger
                if (mSelectedComponent >= 0 && mSelectedComponent < mComponents.size()) {
                    mComponents.get(mSelectedComponent).setXY(x-25, y-25);
                }

                break;

            case MotionEvent.ACTION_UP:
                break;
        }
        // redraw the canvas
        invalidate();
        return true;

    }

    public void addNetworkComponent(DrawableNetworkComponent.Type type)
    {
        mComponents.add(new DrawableNetworkComponent(getContext(), type));
        // TODO: Check if possible to redraw only this new node
        invalidate();
    }

        }



