package com.sepidehmiller.alphabetical;

import android.content.Context;
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
  private String[] mAlphabet;

  public AlphabetPagerAdapter(Context context) {
    mContext = context;
    mAlphabet = new String[]{"A","B","C","D","E"};
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    String alphabet = mAlphabet[position];
    LayoutInflater inflater = LayoutInflater.from(mContext);
    TextView layout = (TextView) inflater.inflate(R.layout.alphabet, container, false);
    layout.setText(alphabet);
    container.addView(layout);
    return layout;
  }

  @Override
  public int getCount() {
    return mAlphabet.length;
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }
}
