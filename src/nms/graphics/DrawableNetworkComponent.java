package nms.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.widget.ImageView;
import android.graphics.Rect;
import android.util.Log;

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



