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

  Paint mRedFill, mBlueFill;
  int mBallX, mBallY, mBallY2, mBallRadius;

  {
    mRedFill = new Paint();
    mRedFill.setColor(Color.RED);
    mRedFill.setStyle(Paint.Style.FILL);
    mRedFill.setStrokeWidth(10);

    mBlueFill = new Paint();
    mBlueFill.setColor(Color.BLUE);
    mBlueFill.setStyle(Paint.Style.FILL);
    mBlueFill.setStrokeWidth(10);

    mBallX = 200;
    mBallY = 200;
    mBallY2 = mBallY;

    mBallRadius = 100;
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
    int ballThreshhold = canvas.getHeight()/3;

    while (mBallX * (i + 1) < canvas.getWidth() - mBallRadius) {
      canvas.drawCircle(mBallX * i, mBallY, mBallRadius, mRedFill);
      canvas.drawCircle(mBallX * (i + 1), mBallY2, mBallRadius, mBlueFill);
      i = i + 2;
    }

    if (mBallY2 < canvas.getHeight() - mBallRadius) {
      if (mBallY < canvas.getHeight() - mBallRadius) {
        mBallY = mBallY + 5;
      }
      if (mBallY > ballThreshhold) {
        mBallY2 = mBallY2 + 5;
      }
      invalidate();
    }

  }
}