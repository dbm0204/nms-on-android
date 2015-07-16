package nms.graphics;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Drawable
{
    private Bitmap mImg;
    private Rect mPosition;
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
        if(mImg==null)
        {
            return;
        }
        else
            canvas.drawBitmap(mImg,null,mPosition,null);
    }

    public final int centerX()
    {
        return mPosition.centerX();
    }

    public final int centerY()
    {
        return mPosition.centerY();
    }

}

