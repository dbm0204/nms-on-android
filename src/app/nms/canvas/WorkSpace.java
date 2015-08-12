package app.nms.canvas;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;

import app.nms.graphics.DrawableNetworkComponent;
import app.nms.utils.ComputePath;
import app.nms.utils.Link;

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

    public Vector<Integer> findShortestPath(int src, int dst)
    {
        // Since we don't actually delete nodes do this dance with the indexes
        HashMap<Integer,Integer> algoIdToActualId = new HashMap<>();
        HashMap<Integer,Integer> actualIdToAlgoId = new HashMap<>();

        int count =0;
        for(int i=0;i<mComponents.size();++i)
            if (!mComponents.get(i).isHidden()) {
                algoIdToActualId.put(count, i);
                actualIdToAlgoId.put(i, count);
                ++count;
            }

        int N = actualIdToAlgoId.size();

        Double adjacencyMatrix[][] = new Double[N][N];
        for(int i=0;i<N;++i)
            for(int j=0;j<N;++j)
                adjacencyMatrix[i][j] = Double.MAX_VALUE;

        for(Link link: mLinks)
        {
            final int srcActualId = link.src();
            final int dstActualId = link.dst();

            final int srcAlgoId = actualIdToAlgoId.get(srcActualId);
            final int dstAlgoId = actualIdToAlgoId.get(dstActualId);

            if (srcAlgoId==dstAlgoId) {
                adjacencyMatrix[srcAlgoId][dstAlgoId] = 0.0;
                continue;
            }

            double dist = DrawableNetworkComponent.getDistance(
                    mComponents.get(srcActualId),
                    mComponents.get(dstActualId));

            adjacencyMatrix[srcAlgoId][dstAlgoId] = adjacencyMatrix[dstAlgoId][srcAlgoId] = dist;
        }

        for(int i=0;i<N;++i)
            adjacencyMatrix[i][i] = 0.0;

        int algoPaths[] = ComputePath.computePaths(src, N, adjacencyMatrix);
        Vector<Integer> shortestPath = new Vector<>();

        final int algoSrc = actualIdToAlgoId.get(src);
        final int algoDst = actualIdToAlgoId.get(dst);

        int currentNode = algoDst;
        while (currentNode!=-1 && currentNode!=algoSrc && currentNode < algoPaths.length)
        {
            shortestPath.add(algoIdToActualId.get(currentNode));
            currentNode = algoPaths[currentNode];
        }

        if (currentNode==algoSrc)
            shortestPath.add(algoIdToActualId.get(algoSrc));
        else
            shortestPath.clear(); // Path does not exist;
        return shortestPath;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if ( mLongClick && mSelectedComponent >= 0 )
            mComponents.get(mSelectedComponent).drawTmpLinkTo(canvas, mTmpLinkX, mTmpLinkY);

        for (Link link : mLinks)
        {
            DrawableNetworkComponent.drawPermLinkTo(
                    canvas,
                    mComponents.get(link.src()),
                    mComponents.get(link.dst())
            );
        }

        for (DrawableNetworkComponent entry : mComponents) {
            entry.draw(canvas);
        }

        if (mSelectionMode && mChosenComponent >= 0 && mChosenComponent < mComponents.size())
        {
            Log.d("SelectionMode","Drawing chosen one");
            mComponents.get(mChosenComponent).drawInverted(canvas);
        }

        if (mHighlightedPath == null || mHighlightedPath.isEmpty())
            return;

        {
            int prev=mHighlightedPath.firstElement();
            for(int i : mHighlightedPath)
            {
                DrawableNetworkComponent.drawHighlightedLinkTo(canvas,
                        mComponents.get(prev),
                        mComponents.get(i));
                prev = i;
            }
        }
    }

    private Vector<Integer> mHighlightedPath;
    private int mChosenComponent = -1; // Chosen by user for delete/source/destination
    private int mSelectedComponent = -1; // For movement
    private boolean mLongClick = false;
    private int mTmpLinkX = 0;
    private int mTmpLinkY = 0;
    private boolean mSelectionMode =false;


    public int getChosenComponent()
    {
        return mChosenComponent;
    }

    public void setSelectionMode(boolean value)
    {
        mSelectionMode= value;
        if(mChosenComponent!=-1) {
            mChosenComponent = -1;
            invalidate();
        }
    }

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

    public void deleteNode(int nodeId) {
        if (nodeId < 0 || nodeId > mComponents.size())
            return;

        // Delete the links
        for (Iterator<Link> iterator = mLinks.iterator(); iterator.hasNext(); )
        {
            Link link = iterator.next();
            if (link.dst()==nodeId || link.src()==nodeId)
                iterator.remove();
        }

        // Delete the node
        mComponents.get(nodeId).deleteComponent();

        mChosenComponent = -1;
    }
    private static final int MIN_TIME_FORLONG_CLICK = 2000;
    private long mClickStartTime = 0;
    private boolean mFirstTouchMove = true;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final int eventaction = event.getAction();
        final int x = (int) event.getX();
        final int y = (int) event.getY();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                mSelectedComponent = getFirstTouchingComponent(x, y);
                if (mSelectedComponent == -1)
                    return false;

                if (mSelectionMode)
                {
                    if (mChosenComponent == mSelectedComponent)
                        mChosenComponent =-1; // If we clicked the same one again un chose it.
                    else
                        mChosenComponent = mSelectedComponent;
                }
                //Record time when click started
                mClickStartTime = Calendar.getInstance().getTimeInMillis();
                mFirstTouchMove = true;
                break;

            case MotionEvent.ACTION_MOVE:
                if (mSelectionMode)
                    return false;

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
                if (mSelectionMode)
                    return false;

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

    public void highlightPath(Vector<Integer> path)
    {
        if (path.isEmpty())
            return;

        mHighlightedPath = path;
        invalidate();
    }

    public void clearHighlightedPath()
    {
        if (mHighlightedPath==null)
            return;

        mHighlightedPath.clear();
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


