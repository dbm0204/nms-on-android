package com.example.ben.nodemanager;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;
import java.util.Vector;
import android.widget.ImageView;

public class WorkSpace extends View
{

    private Vector<DrawableNetworkComponent> mComponents;
    private Context context;
    private void init(Context context)
    {
        this.context = context;
        setFocusable(true);
        setWillNotDraw(false);
        mComponents = new Vector<DrawableNetworkComponent>();
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v)
            {
                processClickEvent(v);
                return true;
            }
        });
    }

    public WorkSpace(Context context)
    {
        super(context);
        init(context);
    }

    public WorkSpace(Context context, AttributeSet attrs )
    {
        super(context, attrs);
        init(context);
    }

    public WorkSpace(Context context, AttributeSet attrs, int defStyle)
    {
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

    private DrawableNetworkComponent mSelectedComponent = null;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        
        final int eventaction =event.getAction();
        final int x=(int)event.getX();
        final int y=(int)event.getY();

        switch (eventaction)
        {
            case MotionEvent.ACTION_DOWN:
                                    for ( DrawableNetworkComponent entry : mComponents)
                                            {
                                               if(entry.isTouching(x,y))
                                               {
                                                   mSelectedComponent =entry;
                                                   Log.d("TouchComp","Touching Component");
                                                   break;
                                               }
                                            }
                                            break;

            case MotionEvent.ACTION_MOVE:
            // touch drag with the ball
            // move the balls the same as the finger
                                            if (mSelectedComponent != null)
                                            {
                                                mSelectedComponent.reposition(x-25,y-25);
                                                //redraw the canvas
                                                invalidate();
                                            }
                                            break;

            case MotionEvent.ACTION_UP:     //reset
                                            mSelectedComponent= null;
                                            break;
        }
        // redraw the canvas
                invalidate();
                return true;

    }

    public void processClickEvent(View v) {
        Rect rect  = getLocationOnScreen(v);
        for ( DrawableNetworkComponent entry : mComponents)
        {
            if(entry.isTouching(rect.left, rect.top))
            {
                mSelectedComponent = entry;
                break;
            }
        }
        showPopupMenu_router(v);
    }
    private void showPopupMenu_router(View v)
    {
        PopupMenu popupMenu = new PopupMenu(context, v);
        popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(
                new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId())
                        {
                            case R.id.start:    Log.d("Start_Connection","cClicked");
                                if (mSelectedComponent != null) {
                                Log.d("Start_Connection","component found" + mSelectedComponent.toString());
                            }
                                break;
                            case R.id.end:      Log.d("End_Connection","cClicked");
                                            break;
                        }
                        return true;
                    }
                });
        popupMenu.show();
    }


    public void addNetworkComponent(DrawableNetworkComponent.Type type)
    {
       Log.d("AddNode","Adding a node");
        mComponents.add(new DrawableNetworkComponent(getContext(),type));
        //TODO: Check if possible to redraw only this new node
        invalidate();

    }
    private Rect getLocationOnScreen(View mView)
    {
        Rect mRect = new Rect();
        int[] location = new int[2];

        mView.getLocationOnScreen(location);

        mRect.left = location[0];
        mRect.top = location[1];
        mRect.right = location[0] + mView.getWidth();
        mRect.bottom = location[1] + mView.getHeight();

        return mRect;
    }

}



