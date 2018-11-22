package com.sepidehmiller.alphabetical;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by baghaii on 9/14/17.
 */

public class FinishBackgroundView extends View {

  Paint mRedRing, mGreenRing, mGrayRing;
  int mChangingRadius, mStrokeWidth;

  {
    mStrokeWidth = 50;

    mRedRing = new Paint();
    mRedRing.setColor(Color.RED);
    mRedRing.setStyle(Paint.Style.STROKE);
    mRedRing.setStrokeWidth(mStrokeWidth);

    mGreenRing = new Paint();
    mGreenRing.setColor(Color.GREEN);
    mGreenRing.setStyle(Paint.Style.STROKE);
    mGreenRing.setStrokeWidth(mStrokeWidth);

    mGrayRing = new Paint();
    mGrayRing.setColor(Color.LTGRAY);
    mGrayRing.setStyle(Paint.Style.STROKE);
    mGrayRing.setStrokeWidth(mStrokeWidth);

    mChangingRadius = 100;

  }

  public FinishBackgroundView(Context context) {
    super(context);
  }

  public FinishBackgroundView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public FinishBackgroundView(Context context, AttributeSet attrs, int style) {
    super(context, attrs, style);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    int i = 1;
    int xCenter;
    int yCenter;


    xCenter = getWidth()/2;
    yCenter = getHeight()/2;

    canvas.drawCircle(xCenter, yCenter, mChangingRadius, mRedRing);

    if (mChangingRadius >= 200) {
      canvas.drawCircle(xCenter, yCenter, mChangingRadius - 100, mGrayRing);

      if (mChangingRadius >= 250) {
        canvas.drawCircle(xCenter, yCenter, mChangingRadius - 200, mGreenRing);
      }

    }

    mChangingRadius = mChangingRadius + 5;

    if (mChangingRadius < xCenter - mStrokeWidth) {
      invalidate();
    }
  }

  public void resetChangingRadius() {
    mChangingRadius = 100;
    invalidate();
  }
}