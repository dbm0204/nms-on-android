package app.nms.canvas;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Ben on 7/15/2015.
 */
public class StyleCache {
    public static final Paint mPaintTmpLink;
    public static final Paint mPaintPermLink;

    static {
        mPaintPermLink = new Paint();
        mPaintPermLink.setColor(Color.RED);
        mPaintPermLink.setStrokeWidth(3);

        mPaintTmpLink = new Paint();
        mPaintTmpLink.setColor(Color.GREEN);
        mPaintTmpLink.setStrokeWidth(2);
    }
}
