package nms.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.graphics.Rect;
import java.util.Calendar;
import java.util.Vector;

public class WorkSpace extends View implements OnLongClickListener
{


    private Vector<DrawableNetworkComponent> mComponents;
    private Vector<Link> mLinks;
    private void init(Context context){
        setFocusable(true);
        setWillNotDraw(false);
        mComponents = new Vector<DrawableNetworkComponent>();
        mLinks = new Vector<Link>();
        setOnLongClickListener(this);
    }

    public WorkSpace(Context context) {
        super(context);
        init(context);
    }

    public WorkSpace(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkSpace(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if ( mLongClick && mSelectedComponent >= 0 )
            mComponents.get(mSelectedComponent).drawTmpLinkTo(canvas, mTmpLinkX, mTmpLinkY);

        for (Link link : mLinks)
        {
            mComponents.get(link.src()).drawPermLinkTo(canvas,
                    mComponents.get(link.dst()).centerX(),
                    mComponents.get(link.dst()).centerY());
        }

        for (DrawableNetworkComponent entry : mComponents) {
            entry.draw(canvas);
        }

    }

    private int mSelectedComponent = -1;
    private boolean mLongClick = false;
    private int mTmpLinkX = 0;
    private int mTmpLinkY = 0;

    @Override
    public boolean onLongClick(View v)
    {
        Log.d("LongClick", "Handling a long click");
        if (mSelectedComponent >=0)
            mLongClick = true;
        return true;
    }

    public final int getFirstTouchingComponent(int x, int y)
    {
        int componentId = 0;
        for (DrawableNetworkComponent entry : mComponents) {
            if (entry.isTouching(x, y)) {
                Log.d("TouchComp", "Touching Component");
                return componentId;
            }
            ++componentId;
        }
        return -1;
    }

    private static final int MIN_TIME_FORLONG_CLICK = 4000;
    private long mClickStartTime = 0;
    private boolean mFirstTouchMove = true;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final int eventaction = event.getAction();
        final int x = (int) event.getX();
        final int y = (int) event.getY();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                mSelectedComponent = getFirstTouchingComponent(x,y);
                if (mSelectedComponent == -1)
                    return false;

                //Record time when click started
                mClickStartTime = Calendar.getInstance().getTimeInMillis();
                mFirstTouchMove = true;
                break;

            case MotionEvent.ACTION_MOVE:
                if (mSelectedComponent < 0 || mSelectedComponent >= mComponents.size())
                    return false;

                if (mFirstTouchMove) {
                    final long clickDuration = Calendar.getInstance().getTimeInMillis() - mClickStartTime;
                    mLongClick = (clickDuration >= MIN_TIME_FORLONG_CLICK);
                    Log.d("LongClick", "Click duration"+clickDuration);
                    mFirstTouchMove = false;
                }

                // touch drag with the ball
                // move the balls the same as the finger
                if ( mLongClick ) {
                    mTmpLinkX = x;
                    mTmpLinkY = y;
                }
                else {
                    mComponents.get(mSelectedComponent).reposition(x - 25, y - 25);
                }

                //redraw the canvas
                invalidate();
                break;

            case MotionEvent.ACTION_UP:     //reset

                if (mLongClick && mSelectedComponent > 0)
                {
                    final int linkEnd = getFirstTouchingComponent(x,y);
                    if (linkEnd >= 0 && linkEnd < mComponents.size())
                    {
                        mLinks.add(new Link(mSelectedComponent, linkEnd));
                    }
                }

                mTmpLinkX=0;
                mTmpLinkY=0;
                mLongClick = false;
                mSelectedComponent = -1;
                mClickStartTime = 0;
                break;
        }
        // redraw the canvas
        invalidate();
        return true;

    }

    public void addNetworkComponent(DrawableNetworkComponent.Type type)
    {
        Log.d("AddNode", "Adding a node");
        mComponents.add(new DrawableNetworkComponent(getContext(), type));
        //TODO: Check if possible to redraw only this new node
        invalidate();
    }

    /*
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.start:
                if (start_connection()) {
                    return true;
                    Log.d("Connection Established", "cClicked");
                } else
                    Log.d("Failed", "cClicked");
                return false;

            case R.id.end:
                if (end_connection()) {
                    return true;
                    Log.d("Connection Terminated", "cClicked");
                } else
                    Log.d("Operation Failed", "cClicked");
                return false;

            case R.id.prop:
                options_prop();
                return true;

            return false;
        }
    }
    public boolean start_connection(){}
    public boolean end_connection(){}
    public void options_prop(){}

  */

}


