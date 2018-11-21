package com.sepidehmiller.alphabetical;

import android.app.Application;
import android.graphics.Typeface;

public class MyApplication extends Application {

  public static Typeface chalkboardType;

  @Override
  public void onCreate() {
    super.onCreate();
    //Load the font only once when the application is created.
    chalkboardType = Typeface.createFromAsset(getAssets(), "fonts/ChalkboardSE.ttc");
  }
}
