package com.sepidehmiller.alphabetical;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by baghaii on 7/26/17.
 */

public class AlphabetPagerActivity extends AppCompatActivity {
  private ViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_alphabet_pager);

    mViewPager = (ViewPager) findViewById(R.id.activity_alphabet_pager_view_pager);
    final AlphabetPagerAdapter adapter = new AlphabetPagerAdapter(this);
    mViewPager.setAdapter(adapter);

    View previous = findViewById(R.id.previous);
    View next = findViewById(R.id.next);

    previous.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        int item = mViewPager.getCurrentItem();
        if (item > 0) {
          item = item - 1;
          mViewPager.setCurrentItem(item, true);
        }
      }
    });

    next.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        int item = mViewPager.getCurrentItem();
        if (item < adapter.getCount() - 1) {
          item = item + 1;
          mViewPager.setCurrentItem(item, true);
        }
      }
    });

  }
}
