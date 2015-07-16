package app.nms.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class Link
{

    private final int mSrcCompId;
    private final int mDstCompId;

    public Link(int src, int dst)
    {
        mSrcCompId = src;
        mDstCompId = dst;
    }

    public final int src() {
        return mSrcCompId;
    }

    public final int dst() {
        return mDstCompId;
    }
}


