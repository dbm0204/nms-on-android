package com.example.ben.nodemanager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.v7.internal.widget.TintImageView;
import android.widget.ImageView;
import android.graphics.Rect;
import android.util.Log;

public class DrawableNetworkComponent extends Drawable
{
    public enum Type
    {
        ROUTER,
        USER,
        UNKNOWN
    }

    private Drawable mDrawing;
    private  Type mType;

    public DrawableNetworkComponent(Context context, Type type)
    {
        super(100, 40);
        this.mType = type;
        switch (mType)
        {
            case ROUTER:    loadImage(context, R.drawable.ic_router);
                            break;
            case USER:      loadImage(context, R.drawable.ic_node);
                            break;
            default:        loadImage(context, R.drawable.ic_launcher);
                            break;
        }
    }
}



