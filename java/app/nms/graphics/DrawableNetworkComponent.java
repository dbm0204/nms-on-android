package app.nms.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.location.Location;

import java.util.Locale;

import app.nms.R;
import app.nms.canvas.StyleCache;

public class DrawableNetworkComponent extends Drawable {
    public enum Type {
        ROUTER,
        USER,
        UNKNOWN
    }

    private Drawable mDrawing;
    private Type mType;

    public DrawableNetworkComponent(Context context, Type type) {
        super(100, 40);
        this.mType = type;
        switch (mType) {
            case ROUTER:
                loadImage(context, R.drawable.ic_router);
                break;
            case USER:
                loadImage(context, R.drawable.ic_node);
                break;
            default:
                loadImage(context, R.drawable.ic_launcher);
                break;
        }
    }

    public  void drawTmpLinkTo(Canvas canvas, int tmpX, int tmpY)
    {
        canvas.drawLine(
                centerX(),
                centerY(),
                tmpX,
                tmpY,
                StyleCache.mPaintTmpLink);
    }
    public static void drawHighlightedLinkTo(Canvas canvas, DrawableNetworkComponent a, DrawableNetworkComponent b)
    {
        canvas.drawLine(
                a.centerX(),
                a.centerY(),
                b.centerX(),
                b.centerY(),
                StyleCache.mPaintShortestPath);
    }

    public static void drawPermLinkTo(Canvas canvas, DrawableNetworkComponent a, DrawableNetworkComponent b)
    {
        canvas.drawLine(
                a.centerX(),
                a.centerY(),
                b.centerX(),
                b.centerY(),
                StyleCache.mPaintPermLink);
    }

    public void deleteComponent()
    {

        // TODO: Make it better
        mHidden = true;
        reposition(-100, -100); // Send it far far away
    }

    public static double getDistance(DrawableNetworkComponent a, DrawableNetworkComponent b)
    {
        //return Math.abs(a.centerX()-b.centerX()) + Math.abs(a.centerY()-b.centerY());
        return Math.sqrt(Math.pow(a.centerX()-b.centerX(), 2) + Math.pow(a.centerY() - b.centerY(), 2));
    }
}



