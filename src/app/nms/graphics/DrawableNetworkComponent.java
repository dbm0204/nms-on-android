package app.nms.graphics;

import android.content.Context;
import android.graphics.Canvas;
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

    public void drawTmpLinkTo(Canvas canvas, int x, int y)
    {
        canvas.drawLine(centerX(),centerY(),x,y,StyleCache.mPaintTmpLink);
    }

    public void drawPermLinkTo(Canvas canvas, int x, int y)
    {
        canvas.drawLine(centerX(),centerY(),x,y,StyleCache.mPaintPermLink);
    }
}



