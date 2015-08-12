package app.nms.graphics;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;

public class Drawable
{
    private Bitmap mImg;
    private Rect mPosition;
    protected boolean mHidden = false;
    public Drawable()
    {
        mPosition=new Rect(0,0,0,0);
    }
    public Drawable(int xPos,int yPos)
    {
        mPosition=new Rect(xPos,yPos,xPos,yPos);
    }
    public void reposition(int newX,int newY)
    {
        mPosition.offsetTo(newX,newY);
    }
    public boolean isTouching(int x,int y)
    {
        return mPosition.contains(x,y);
    }
    public void loadImage(Context context,int drawable)
    {
        mImg= BitmapFactory.decodeResource(context.getResources(),drawable);
        mPosition.set(mPosition.left,mPosition.top,mPosition.left + mImg.getWidth(),mPosition.top+mImg.getHeight());
    }
    public void draw(Canvas canvas)
    {
        if(mImg!=null)
          canvas.drawBitmap(mImg,null,mPosition,null);
    }

    public void drawInverted(Canvas canvas)
    {
        if(mImg==null || mHidden)
            return;

        //To generate negative image
        final float[] colorMatrix_Negative = {
                -1.0f, 0, 0, 0, 255, //red
                0, -1.0f, 0, 0, 255, //green
                0, 0, -1.0f, 0, 255, //blue
                0, 0, 0, 1.0f, 0 //alpha
        };

        final Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix_Negative));
        canvas.drawBitmap(mImg, null, mPosition, paint);
    }

    public final int centerX()
    {
        return mPosition.centerX();
    }

    public final int centerY()
    {
        return mPosition.centerY();
    }

    public final boolean isHidden()
    {
        return mHidden;
    }
}

