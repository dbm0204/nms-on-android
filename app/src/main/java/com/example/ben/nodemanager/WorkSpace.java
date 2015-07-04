package com.example.ben.nodemanager;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.Vector;
import android.util.Log;

public class WorkSpace extends View {


    private Vector<DrawableNetworkComponent> mComponents;
    private void init(Context context)
    {
        setFocusable(true);
        setWillNotDraw(false);
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
    public boolean onTouchEvent(MotionEvent event)
    {
        
        final int eventaction =event.getAction();
        final int x=(int)event.getX();
        final int y=(int)event.getY();

        switch (eventaction)
        {
            case MotionEvent.ACTION_DOWN:   int componentId =0;
                                            for ( DrawableNetworkComponent entry : mComponents)
                                            {
                                               if(entry.isTouching(x,y))
                                               {
                                                   mSelectedComponent =componentId;
                                                   Log.d("TouchComp","Touching Component");
                                                   break;

                                               }
                                                ++componentId;
                                            }
                                            break;

            case MotionEvent.ACTION_MOVE:
            // touch drag with the ball
            // move the balls the same as the finger
                                            if (mSelectedComponent >= 0 && mSelectedComponent < mComponents.size())
                                            {
                                                mComponents.get(mSelectedComponent).reposition(x-25,y-25);
                                                //redraw the canvas
                                                invalidate();
                                            }
                                            break;

            case MotionEvent.ACTION_UP:     //reset
                                            mSelectedComponent=-1;
                                            break;
        }
        // redraw the canvas
                invalidate();
                return true;

    }

    public void addNetworkComponent(DrawableNetworkComponent.Type type)
    {
       Log.d("AddNode","Adding a node");
        mComponents.add(new DrawableNetworkComponent(getContext(),type));
        //TODO: Check if possible to redraw only this new node
        invalidate();

    }

}



