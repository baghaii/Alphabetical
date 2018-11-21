package com.sepidehmiller.alphabetical;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by baghaii on 8/15/17.
 */

public class AlphabetPagerAdapter extends PagerAdapter {

  private final Context mContext;
  private AlphabetSoundPlayer mSoundPlayer;
  private String[] mAlphabet;

  public AlphabetPagerAdapter(Context context, AlphabetSoundPlayer soundPlayer) {
    mContext = context;
    mSoundPlayer = soundPlayer;
    mAlphabet = new String[]{"A","B","C","D","E","F","G",
        "H","I","J","K","L","M","N","O","P","Q","R","S","T",
        "U","V","W","X","Y","Z"};
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    String alphabet = mAlphabet[position];

    LayoutInflater inflater = LayoutInflater.from(mContext);
    TextView layout = (TextView) inflater.inflate(R.layout.textview_alphabet, container, false);
    layout.setText(alphabet);
    Typeface face = Typeface.createFromAsset(mContext.getAssets(), "fonts/ChalkboardSE.ttc");
    layout.setTypeface(face);

    final int i = position;
    layout.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {

        mSoundPlayer.play(mSoundPlayer.getSounds().get(i));

      }
    });

    container.addView(layout);

    return layout;
  }

  @Override
  public int getCount() {
    return mAlphabet.length;
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return view == object;
  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position,@NonNull Object object) {
    container.removeView((View) object);
  }
}
